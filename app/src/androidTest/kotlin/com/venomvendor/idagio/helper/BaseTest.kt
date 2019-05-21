/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 21-May-2019.
 */

package com.venomvendor.idagio.helper

import androidx.annotation.CallSuper
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.venomvendor.idagio.app.util.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.core.KoinComponent

@RunWith(AndroidJUnit4::class)
abstract class BaseTest : KoinComponent {

    @Before
    @CallSuper
    open fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    @CallSuper
    open fun autoClose() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
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

