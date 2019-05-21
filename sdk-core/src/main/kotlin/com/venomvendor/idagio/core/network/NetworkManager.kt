/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 20-May-2019.
 */

package com.venomvendor.idagio.core.network

import retrofit2.Retrofit

class NetworkManager {

    inline fun <reified T> createWebService(retrofit: Retrofit): T {
        return retrofit.create(T::class.java)
    }
}
