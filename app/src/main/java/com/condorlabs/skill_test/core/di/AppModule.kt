package com.condorlabs.skill_test.core.di

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.condorlabs.skill_test.feature_search_product.data.remote.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesPreferencesHelper(application: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }


    @Singleton
    @Provides
    fun provideApiService(
        authInterceptor: OAuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
        tokenAuthenticator: TokenAuthenticator,
    ): ArtistApi {
        val interceptors = arrayOf(authInterceptor, loggingInterceptor)
        return ApiServiceBuilder.builder(
            ArtistApi.BASE_URL,
            ArtistApi::class.java,
            interceptors,
            tokenAuthenticator
        )
    }

    @Singleton
    @Provides
    fun provideApiNoneAuthService(
        noneAuthInterceptor: NoneAuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
    ): ApiNoneAuthService {
        val interceptors = arrayOf(noneAuthInterceptor, loggingInterceptor)
        return ApiServiceBuilder.builder(
            "https://accounts.spotify.com/api/token/",
            ApiNoneAuthService::class.java,
            interceptors,
            null
        )
    }


    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
}