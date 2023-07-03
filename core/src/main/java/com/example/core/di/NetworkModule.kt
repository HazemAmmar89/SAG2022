package com.example.core.di

import com.example.core.BuildConfig
import com.example.data.remote.remote.service.RemoteServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val localHttpLoggingInterceptor = HttpLoggingInterceptor()
        localHttpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return localHttpLoggingInterceptor
    }

    @Provides
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor { chain ->
                val original: Request = chain.request()
                val builder: Request.Builder = original.newBuilder()
                return@addInterceptor chain.proceed(builder.build())
            }
            .build()

    @Provides
    fun providesApiService(okHttpClient: OkHttpClient): RemoteServices =
        Retrofit.Builder()
            .run {
                baseUrl(BuildConfig.BASE_URL)
                client(okHttpClient)
                addConverterFactory(GsonConverterFactory.create())
                build()
            }.create(RemoteServices::class.java)
}