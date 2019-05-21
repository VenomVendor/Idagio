/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 21-May-2019.
 */

package com.venomvendor.idagio.app.util

import androidx.annotation.VisibleForTesting
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource
import com.venomvendor.idagio.BuildConfig
import java.util.Objects

/**
 * Global Espresso idling resource
 */
class EspressoIdlingResource private constructor() {

    init {
        throw UnsupportedOperationException("Instance should not be created")
    }

    companion object {

        private const val RES_NAME = "EspressoIdlingResource"

        private var sIdlingResource: CountingIdlingResource? = null

        /**
         * Start waiting for data
         */
        fun increment() {
            if (BuildConfig.DEBUG) {
                sIdlingResource?.increment()
            }
        }

        /**
         * Data is received
         */
        fun decrement() {
            if (BuildConfig.DEBUG) {
                sIdlingResource?.decrement()
            }
        }

        val idlingResource: IdlingResource
            @VisibleForTesting(otherwise = VisibleForTesting.NONE)
            get() = Objects.requireNonNull<CountingIdlingResource>(
                sIdlingResource,
                "Missing Idling Resource"
            )

        /**
         * Set Custom idling resource
         */
        @VisibleForTesting(otherwise = VisibleForTesting.NONE)
        fun setIdlingResource(idlingResource: CountingIdlingResource) {
            if (BuildConfig.DEBUG) {
                sIdlingResource = Objects.requireNonNull(idlingResource)
            }
        }

        /**
         * Set default idling resource.
         */
        @VisibleForTesting(otherwise = VisibleForTesting.NONE)
        fun setDefaultIdlingResource() {
            setIdlingResource(CountingIdlingResource(RES_NAME, true))
        }
    }
}
