/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 21-May-2019.
 */

package com.venomvendor.idagio.helper

import com.venomvendor.idagio.app.core.IdagioApplication
import com.venomvendor.idagio.app.util.EspressoIdlingResource
import com.venomvendor.idagio.helper.di.appTestModule
import org.koin.core.context.loadKoinModules

class TestIdagioApplication : IdagioApplication() {

    override fun onCreate() {
        super.onCreate()

        EspressoIdlingResource.setDefaultIdlingResource()

        loadKoinModules(appTestModule)
    }
}
