/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 21-May-2019.
 */

package com.venomvendor.idagio.helper

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class IdagioTestRunner : AndroidJUnitRunner() {
    @Throws(
        ClassNotFoundException::class,
        IllegalAccessException::class,
        InstantiationException::class
    )
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, TestIdagioApplication::class.java.name, context)
    }
}
