/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 20-May-2019.
 */

package com.venomvendor.idagio.search.factory

import com.venomvendor.idagio.search.model.SearchArtist

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Webservice to make search API calls
 */
interface SearchService {

    /**
     * Search for Artists
     *
     * @param query query to search
     * @return SingleObservable to subscribe the result.
     */
    @GET("/v1.8/lucene/search")
    fun searchArtists(@Query("term") query: String): Single<SearchArtist>
}
