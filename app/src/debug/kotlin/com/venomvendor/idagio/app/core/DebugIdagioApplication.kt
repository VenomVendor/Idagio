/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 20-May-2019.
 */

@file:Suppress("unused")

package com.venomvendor.idagio.app.core

import android.annotation.SuppressLint
import com.venomvendor.idagio.debug.di.networkTrackerModule
import org.koin.core.context.loadKoinModules

/**
 * Application class for core functionality.
 */
@SuppressLint("Registered")
class DebugIdagioApplication : IdagioApplication() {

    override fun onCreate() {
        super.onCreate()

        loadKoinModules(networkTrackerModule)
    }
}
