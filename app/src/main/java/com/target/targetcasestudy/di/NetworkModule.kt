package com.target.targetcasestudy.di

import com.target.targetcasestudy.BuildConfig
import com.target.targetcasestudy.data.DealAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit.Builder
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Provides
  @Singleton
  fun providePokemonApiService(okHttpClient: OkHttpClient): DealAPIService {
    return Builder()
      .baseUrl(BuildConfig.API_BASE_URL)
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build()
      .create(DealAPIService::class.java)
  }

  @Provides
  @Singleton
  fun getOkHTTPClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return OkHttpClient.Builder().addInterceptor(interceptor).build();
  }
}