/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 21-May-2019.
 */

package com.venomvendor.idagio.search.viewmodel

import com.google.gson.Gson
import com.venomvendor.idagio.core.di.coreModule
import com.venomvendor.idagio.search.di.searchModule
import com.venomvendor.idagio.search.helper.BaseTest
import com.venomvendor.idagio.search.model.SearchArtist
import com.venomvendor.idagio.search.repository.SearchRepository
import io.reactivex.Single
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.get
import org.mockito.Mockito
import org.mockito.Mockito.`when` as mockitoWhen

class SearchArtistViewModelTest : BaseTest() {

    override fun before() {
        super.before()
        startKoin {
            modules(coreModule, searchModule)
        }
    }

    @Test
    fun `getArtists With Results`() {
        val response: String = with(this).read("response.json")
        val mockRepository = Mockito.mock(SearchRepository::class.java)
        val searchObj = get<Gson>().fromJson<SearchArtist>(response, SearchArtist::class.java)

        mockitoWhen(mockRepository.searchArtists(Mockito.anyString()))
            .thenReturn(
                Single.just(searchObj.artist.people)
            )

        val randomUserViewModel = SearchArtistViewModel(mockRepository)

        randomUserViewModel.getArtists("Test")
            .test()
            .assertSubscribed()
            .assertComplete()
            .assertNoErrors()
            .assertValue {
                it.count() == 4
            }
    }

    @Test
    fun `getArtists With Error`() {
        val mockRepository = Mockito.mock(SearchRepository::class.java)

        mockitoWhen(mockRepository.searchArtists(Mockito.anyString()))
            .thenReturn(
                Single.error(Exception("Test"))
            )

        val randomUserViewModel = SearchArtistViewModel(mockRepository)

        randomUserViewModel.getArtists("Test")
            .test()
            .assertSubscribed()
            .assertError {
                it.message == "Test"
            }
    }
}
