/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 20-May-2019.
 */

@file:Suppress("unused")

package com.venomvendor.idagio.app.core

import android.annotation.SuppressLint

/**
 * Application class for core functionality.
 */
@SuppressLint("Registered")
class DebugIdagioApplication : IdagioApplication() {

// TODO: Fix this org.koin.core.error.KoinAppAlreadyStartedException
//    override fun onCreate() {
//        super.onCreate()
//
//        // Start Koin
//        startKoin {
//            // Declare used Android context
//            androidContext(this@DebugIdagioApplication)
//            // Declare modules
//            modules(networkTrackerModule, CORE_MODULE)
//        }
//    }
}
