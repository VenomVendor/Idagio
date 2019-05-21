/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 21-May-2019.
 */

package com.venomvendor.idagio.app.util

import androidx.recyclerview.widget.DiffUtil
import com.venomvendor.idagio.search.model.Person

/**
 * Helper Utility for Recycler View's Data.
 */
class DiffUtilHelper private constructor() {

    init {
        throw UnsupportedOperationException("Instance should not be created")
    }

    companion object {

        val PERSON_DIFF: DiffUtil.ItemCallback<Person> = object : DiffUtil.ItemCallback<Person>() {
            override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
                return oldItem == newItem
            }
        }
    }
}
