/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 20-May-2019.
 */

package com.venomvendor.idagio.app.core

import android.app.Application
import com.venomvendor.idagio.core.di.coreModule
import com.venomvendor.idagio.search.di.searchModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Application class for core functionality.
 */
open class IdagioApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // start Koin
        startKoin {
            // Declare used Android context
            androidContext(this@IdagioApplication)
            // Declare modules
            modules(coreModule, searchModule)
        }
    }
}
