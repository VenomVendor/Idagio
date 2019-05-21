/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 20-May-2019.
 */

package com.venomvendor.idagio.debug.di

import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Interceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Debug module that tracks all API calls.
 */
val networkTrackerModule = module {

    /**
     * Provides Chuck Interceptor which attaches with [okhttp3] interceptors.
     */
    single<Interceptor> {
        ChuckInterceptor(androidContext())
    }
}
