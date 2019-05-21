/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 21-May-2019.
 */

package com.venomvendor.idagio.search.repository

import com.google.gson.Gson
import com.venomvendor.idagio.core.di.coreModule
import com.venomvendor.idagio.search.di.searchModule
import com.venomvendor.idagio.search.factory.SearchService
import com.venomvendor.idagio.search.helper.BaseTest
import com.venomvendor.idagio.search.model.SearchArtist
import io.reactivex.Single
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.get
import org.mockito.Mockito
import org.mockito.Mockito.`when` as mockitoWhen

class SearchRepositoryTest : BaseTest() {

    override fun before() {
        super.before()
        startKoin {
            modules(coreModule, searchModule)
        }
    }

    @Test
    fun `searchArtists With Results`() {
        val response: String = with(this).read("response.json")
        val searchObj = get<Gson>().fromJson<SearchArtist>(response, SearchArtist::class.java)

        val search: SearchService = Mockito.mock(SearchService::class.java)
        val searchRepository = SearchRepository(get(), search)

        mockitoWhen(search.searchArtists(Mockito.anyString()))
            .thenReturn(Single.just(searchObj))

        searchRepository.searchArtists("ARR")
            .test()
            .assertSubscribed()
            .assertComplete()
            .assertNoErrors()
            .assertValue {
                it.count() == 4
            }
    }

    @Test
    fun `searchArtists With Error`() {
        val response: String = with(this).read("error.json")
        val searchObj = get<Gson>().fromJson<SearchArtist>(response, SearchArtist::class.java)

        val search: SearchService = Mockito.mock(SearchService::class.java)
        val searchRepository = SearchRepository(get(), search)

        mockitoWhen(search.searchArtists(Mockito.anyString()))
            .thenReturn(Single.just(searchObj))

        searchRepository.searchArtists("ARR")
            .test()
            .assertSubscribed()
            .assertError {
                it.message == "Invalid request query input"
            }
    }
}
