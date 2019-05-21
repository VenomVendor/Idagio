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

    companion object {
        fun with(instance: Any): TestHelper {
            return TestHelper(instance)
        }
    }
}

class TestHelper(private val testInstance: Any) {
    /**
     * Add files under `/src/test/resources`
     * Add files under `/src/androidTest/resources`
     *
     * @param filePath location of file to be read, relative path.
     * @return Contents in file.
     */
    fun read(filePath: String): String {
        return testInstance.javaClass.getResource("/$filePath")!!.readText()
    }
}
