/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 21-May-2019.
 */

package com.venomvendor.idagio.search.viewmodel

import androidx.annotation.CheckResult
import androidx.lifecycle.ViewModel
import com.venomvendor.idagio.search.model.Person
import com.venomvendor.idagio.search.repository.SearchRepository
import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.get

class SearchArtistViewModel : ViewModel(), KoinComponent {

    private val searchRepository: SearchRepository = get()

    /* Create Repository that acts as single source of data */
//    private val randomUserViewModel: RandomUserViewModel by viewModel()

    /**
     * THIS API WRAPS **DATA**
     * ******************************************
     * Inside [Single]. Error is propagated as is.
     * ******************************************
     * Get list of Artists
     *
     * @return List of [Person] wrapped inside response.
     */
    @CheckResult
    fun getArtists(query: String): Single<List<Person>> {
        return searchRepository.searchArtists(query)
    }
}
