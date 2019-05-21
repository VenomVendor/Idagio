/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 21-May-2019.
 */

package com.venomvendor.idagio.app.ui.factory

import android.view.View

/**
 * Handle item click
 *
 * @param <T> Type of data
</T> */
interface OnItemClickListener<T> {

    /**
     * Callback when item is clicked
     */
    fun onClick(item: T, view: View, position: Int)
}
