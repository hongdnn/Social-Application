package com.fpter.wetalk.application.injection

import com.fpter.wetalk.AppConfig
import com.fpter.wetalk.application.helper.GsonHelper
import com.fpter.wetalk.application.prefs.AppPreference
import com.fpter.wetalk.application.prefs.AppPreferenceImpl
import com.fpter.wetalk.features.login.injection.loginModule
import com.fpter.wetalk.features.refreshtoken.repository.RefreshTokenServiceApi
import com.fpter.wetalk.features.register.injection.registerModule
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val applicationModule = module {
    //scope<MyApplication> {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(AppConfig.HOST_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }).build()
            )
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<RefreshTokenServiceApi> {
        get<Retrofit>().create(RefreshTokenServiceApi::class.java)
    }

    single<Gson> {
        GsonBuilder().serializeNulls().create()
    }

    single {
        GsonHelper(get())
    }

    single<AppPreference> {
        AppPreferenceImpl(androidContext(), get())
    }
    //}
}

val allModules = listOf(
    applicationModule,
    registerModule,
    loginModule
)