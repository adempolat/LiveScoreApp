package com.adempolat.livescoreapp.di

import com.adempolat.livescoreapp.service.LiveScoreApi
import com.adempolat.livescoreapp.utils.Environment.Companion.PROD
import com.adempolat.livescoreapp.utils.Utility
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    // OkHttpClient sağlayıcı
    @Singleton
    @Provides
    fun apiCreator(): LiveScoreApi {
        return Retrofit.Builder()
            .baseUrl(PROD)
            .addConverterFactory(GsonConverterFactory.create())
            //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(makeOkHttpClient())
            .build()
            .create(LiveScoreApi::class.java)
    }
    // Retrofit sağlayıcı
    private fun makeOkHttpClient(): OkHttpClient {
        return  OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val original: Request = chain.request()
                val request: Request = original.newBuilder()
                    .header(
                        "Content-Type",
                        Utility.CONTENT_TYPE
                    )
                    .header("name", Utility.NAME)
                    .header("version", Utility.VERSION)
                    .header("Lang", Locale.getDefault().language)
                    .build()
                chain.proceed(request)
            })
            .retryOnConnectionFailure(true)
            .connectTimeout(70, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(70, TimeUnit.SECONDS)
            .build()
    }

    // ApiService sağlayıcı
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): LiveScoreApi {
        return retrofit.create(LiveScoreApi::class.java)
    }

}