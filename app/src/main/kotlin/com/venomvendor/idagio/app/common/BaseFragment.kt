/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 21-May-2019.
 */

package com.venomvendor.idagio.app.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CheckResult
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import java.util.Objects

/**
 * Root fragment/view to handles common functionality across app.
 */
abstract class BaseFragment : Fragment() {

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
     *
     * @param view current view
     */
    protected abstract fun initViews(view: View)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Retain state over orientation.
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Create View.
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Init Views
        initViews(Objects.requireNonNull<View>(getView(), "View is not loaded yet"))
    }
}
