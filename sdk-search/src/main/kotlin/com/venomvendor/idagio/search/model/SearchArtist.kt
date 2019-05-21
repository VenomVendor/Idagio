/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 20-May-2019.
 */

package com.venomvendor.idagio.search.model

import com.google.gson.annotations.SerializedName
import com.venomvendor.idagio.core.network.model.Status

data class SearchArtist(
    @SerializedName("artists") val artist: Artist
) : Status()
