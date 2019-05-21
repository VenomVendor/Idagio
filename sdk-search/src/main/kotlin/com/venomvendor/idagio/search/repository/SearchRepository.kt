/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 20-May-2019.
 */

package com.venomvendor.idagio.search.repository

import androidx.annotation.CheckResult
import com.venomvendor.idagio.core.data.RepositoryManager
import com.venomvendor.idagio.core.data.factory.Repository
import com.venomvendor.idagio.core.storage.factory.Storage
import com.venomvendor.idagio.search.factory.SearchService
import com.venomvendor.idagio.search.model.Person
import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class SearchRepository(
    private val storage: Storage,
    private val search: SearchService
) : Repository<List<Person>>, KoinComponent {
    private lateinit var query: String

    private val repoManager: RepositoryManager<List<Person>> by inject {
        parametersOf(this)
    }

    override val cachedData: List<Person>?
        get() = storage.retrieve(Person::class.java)

    override val request: Single<List<Person>>
        get() {
            return search.searchArtists(query)
                .doOnSuccess {
                    if (!it.isSuccess) {
                        throw RuntimeException(it.message)
                    }
                }
                .map {
                    it.artist.people
                }
                .doOnSuccess {
                    saveData(it)
                }
        }

    @Throws(Exception::class)
    override fun saveData(data: List<Person>) {
        // Do In parallel
        storage.save(data)
    }

    @CheckResult
    fun searchArtists(query: String): Single<List<Person>> {
        this.query = query

        // Create request object
        return repoManager
            // Execute repository
            .execute()
    }
}
