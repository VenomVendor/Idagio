/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 20-May-2019.
 */

package com.venomvendor.idagio.core.data

import androidx.annotation.CheckResult
import com.venomvendor.idagio.core.data.factory.Repository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * Handles core functionality, it is the single source of truth.
 * Gets data from storage, server, saves, handles responses gracefully.
 */
class RepositoryManager<T>(private val repository: Repository<T>) {

    /**
     * Executes request to get data.
     * First data is retrieved from storage, upon failure, the request is sent to server
     * to get data, it also adds functionality to get data from both storage &amp; server.
     * This acts as offline first approach.
     *
     * @param <T> Type of data from server
     * @return LiveData in which data is updated when received.
     */
    @CheckResult
    fun execute(): Single<T> {
        val source = Single.defer {
            val fromCache = repository.cachedData
            if (fromCache != null) {
                return@defer Single.just(fromCache)
            }

            // Executes request and retrieves data from server.
            repository.request
        }

        return attachIoThread(source)
    }

    /**
     * Attaches io thread to current Observable
     *
     * @param source Observable to attach
     * @param <T> Type of data from server
     * @return Updated Observable
     */
    @CheckResult
    private fun attachIoThread(source: Single<T>): Single<T> {
        return source.subscribeOn(Schedulers.io())
    }
}
