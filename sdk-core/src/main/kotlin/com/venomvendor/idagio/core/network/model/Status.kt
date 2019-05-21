package com.venomvendor.idagio.core.network.model

import com.google.gson.annotations.SerializedName

open class Status(

    @SerializedName("error")
    val error: String? = null,

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("statusCode")
    val statusCode: Int? = null
) {
    val isSuccess: Boolean
        get() = error == null
}
