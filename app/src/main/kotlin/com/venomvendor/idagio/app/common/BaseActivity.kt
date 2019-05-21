/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 21-May-2019.
 */

package com.venomvendor.idagio.app.common

import android.os.Bundle

import androidx.annotation.CheckResult
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 * Root activity responsible for common operation across the app.
 */
abstract class BaseActivity : AppCompatActivity() {

    /**
     * Get the Layout for the user.
     *
     * @return layout to append
     */
    @get:CheckResult
    @get:LayoutRes
    protected abstract val layout: Int

    /**
     * Views are ready to be initialized.
     */
    protected abstract fun initViews()

    /**
     * {@inheritDoc}
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

        // Init Views
        initViews()
    }
}
