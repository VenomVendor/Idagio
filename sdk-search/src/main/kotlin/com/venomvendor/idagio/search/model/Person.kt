/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 20-May-2019.
 */

package com.venomvendor.idagio.search.model

import com.google.gson.annotations.SerializedName
import com.venomvendor.idagio.core.annotation.Mandatory

data class Person(
    @Mandatory
    @SerializedName("id")
    val id: Int,

    @SerializedName("forename")
    val forename: String? = null,

    @SerializedName("score")
    val score: Double? = null,

    @SerializedName("functions")
    val functions: List<String?>? = null,

    @SerializedName("surname")
    val surname: String? = null,

    @SerializedName("kind")
    val kind: String? = null,

    @SerializedName("matchedQueries")
    val matchedQueries: List<Any?>? = null,

    @SerializedName("popularity")
    val popularity: Double? = null,

    @SerializedName("name")
    val name: String? = null
)
