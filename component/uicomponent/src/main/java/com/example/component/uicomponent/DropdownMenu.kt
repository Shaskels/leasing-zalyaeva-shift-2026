package com.example.component.uicomponent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.component.uicomponent.theme.LeasingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenu(
    placeholder: String,
    options: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {},
        modifier = modifier,
    ) {
        CustomTextField(
            value = options.getOrNull(selectedIndex) ?: "",
            onValueChange = { },
            readOnly = true,
            placeholder = placeholder,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        painterResource(R.drawable.chevron_down),
                        contentDescription = null
                    )
                }
            }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { },
            containerColor = LeasingTheme.colors.backgroundPrimary
        ) {
            options.forEachIndexed { index, it ->
                DropdownMenuItem(
                    text = {
                        Text(text = it)
                    },
                    onClick = {
                        onSelect(index)
                        expanded = false
                    }
                )
            }
        }
    }
}