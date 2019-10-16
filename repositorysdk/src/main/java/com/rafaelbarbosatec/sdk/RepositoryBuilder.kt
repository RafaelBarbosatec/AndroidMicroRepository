package com.rafaelbarbosatec.sdk

import android.content.Context
import java.lang.Exception

class RepositoryBuilder(private val context: Context) {
    private var baseUrl = ""
    private var debugable = false

    fun baseUrl(baseUrl: String): RepositoryBuilder {
        this.baseUrl = baseUrl
        return this
    }

    fun debugable(debugable: Boolean): RepositoryBuilder {
        this.debugable = debugable
        return this
    }

    fun build(): RepositorySDK {
        if (baseUrl.isEmpty()) {
            throw Exception("Url must not be empty")
        }
        return RepositorySDKImpl(
            context,
            baseUrl,
            debugable
        )
    }
}