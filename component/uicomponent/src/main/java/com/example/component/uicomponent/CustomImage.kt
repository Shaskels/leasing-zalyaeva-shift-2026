package com.example.component.uicomponent

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import okhttp3.OkHttpClient
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Composable
fun CustomImage(url: String, modifier: Modifier = Modifier) {
    AsyncImage(
        imageLoader = initUntrustImageLoader(LocalContext.current),
        model = "https://shift-intensive.ru/api$url",
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
    )
}

@SuppressLint("CustomX509TrustManager")
private fun initUntrustImageLoader(context: Context): ImageLoader {

    val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
        @SuppressLint("TrustAllX509TrustManager")
        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}

        @SuppressLint("TrustAllX509TrustManager")
        override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}

        override fun getAcceptedIssuers(): Array<X509Certificate> {
            return arrayOf()
        }
    })

    val sslContext = SSLContext.getInstance("SSL")
    sslContext.init(null, trustAllCerts, java.security.SecureRandom())
    val sslSocketFactory = sslContext.socketFactory

    val client = OkHttpClient.Builder()
        .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        .hostnameVerifier { _, _ -> true }.build()


    return ImageLoader.Builder(context)
        .components {
            add(
                OkHttpNetworkFetcherFactory(
                    callFactory = {
                        client
                    }
                )
            )
        }
        .build()
}