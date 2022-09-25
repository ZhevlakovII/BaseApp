package com.izhxx.baseapp.data.api

import com.izhxx.baseapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RstApi {
    @GET("main")
    suspend fun getMainPageData(
        @Query("id") id: String
    ): Response<RstApiResponse>

    @GET("blog")
    suspend fun getMultipleBlogData(
        @Query("id") id: String
    ): Response<MultipleBlogResponse>

    @GET("blog-info")
    suspend fun getBlogData(
        @Query("id") id: String,
        @Query("blog_id") blogId: String
    ): Response<BlogResponse>

    companion object {
        fun create(): RstApi {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RstApi::class.java)
        }
    }
}