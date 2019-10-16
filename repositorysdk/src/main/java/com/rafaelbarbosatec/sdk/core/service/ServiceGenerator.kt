package com.rafaelbarbosatec.sdk.core.service

import com.google.gson.GsonBuilder
import com.rafaelbarbosatec.sdk.core.factory.livedata.LiveDataCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.Exception

class ServiceGenerator(baseUrl: String, private val debugable: Boolean) {

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(generateOauthInterceptor())
        .addInterceptor(generateLoggingInterceptor())
        .build()

    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .build()

    inline fun <reified T> generate(): T {
        try {
            return retrofit.create(T::class.java)
        }catch (e:Exception){
            throw Exception("Not found service ${T::class.java}")
        }
    }

    private fun generateOauthInterceptor(): Interceptor {

        return Interceptor { chain ->
            var request = chain.request()

            // EXEMPLO PARA ADICIONAR TOKEN NO READER
//            val headers = request.headers().newBuilder().add("Authorization", "token fsdufhksdgf").build()
//            request = request.newBuilder().headers(headers).build()

            chain.proceed(request)
        }
    }

    private fun generateLoggingInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        if(debugable){
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        }else{
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return interceptor
    }

}