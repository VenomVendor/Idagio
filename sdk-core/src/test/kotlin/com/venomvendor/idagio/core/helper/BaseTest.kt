/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 20-May-2019.
 */

package com.venomvendor.idagio.core.helper

import androidx.annotation.CallSuper
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

abstract class BaseTest : KoinTest {

    @Before
    @CallSuper
    open fun before() {
        RxJavaPlugins.setIoSchedulerHandler {
            Schedulers.trampoline()
        }
    }

    @After
    @CallSuper
    open fun autoClose() {
        stopKoin()
        RxJavaPlugins.reset()
    }
}
