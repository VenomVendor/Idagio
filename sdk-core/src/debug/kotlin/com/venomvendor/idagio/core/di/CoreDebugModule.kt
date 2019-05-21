/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 21-May-2019.
 */

package com.venomvendor.idagio.core.di

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

/**
 * Provides all dependencies in current core module.
 */
val coreDebugModule = module(override = true) {
    factory {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        interceptor
    }

    /**
     * Provides Single Okhttp3 Client for http layer
     */
    single(override = true) {
        OkHttpClient().newBuilder()
            .addInterceptor(get() as Interceptor)
            .addInterceptor(get() as HttpLoggingInterceptor)
            .build()
    }
}
