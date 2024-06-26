package sagi.shchori.testapp

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {

    private const val BASE_URL = ""

    private val logger = object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Log.d("APIs", message)
        }

    }
    private val interceptor =
        HttpLoggingInterceptor(logger).setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client: OkHttpClient =
        OkHttpClient().newBuilder().addInterceptor(interceptor).build()

    private val retrofit: Retrofit? = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    fun <T> createService(service: Class<T>): T? {
        return retrofit?.create(service)
    }
}