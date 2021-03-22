package com.becooni.rxexam

import okhttp3.OkHttpClient
import okhttp3.Request

object OkHttpHelper {

    private val client = OkHttpClient()

    internal fun fetch(url: String): String? {
        val request = Request.Builder()
            .url(url)
            .build()

        val response = client.newCall(request).execute()
        return response.body?.string()
    }
}