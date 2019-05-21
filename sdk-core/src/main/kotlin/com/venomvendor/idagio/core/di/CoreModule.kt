/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 20-May-2019.
 */

package com.venomvendor.idagio.core.di

import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapterFactory
import com.venomvendor.gson.NullDefenseTypeAdapterFactory
import com.venomvendor.idagio.core.annotation.Mandatory
import com.venomvendor.idagio.core.data.RepositoryManager
import com.venomvendor.idagio.core.data.factory.Repository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val coreModule = module {
    val baseUrl = "https://api.idagio.com/v1.8/lucene"

    /**
     * Provides [Gson] to generate objects from Json.
     */
    single {
        val gsonBuilder = GsonBuilder()

        get<List<TypeAdapterFactory>>().forEach {
            gsonBuilder.registerTypeAdapterFactory(it)
        }

        gsonBuilder.create()
    }

    /**
     * Provides [Gson] to generate objects from Json.
     */
    factory {
        GsonConverterFactory.create(get())
    }

    /**
     * Provides [TypeAdapterFactory] to attach with [Gson], this removes invalid object from response.
     */
    factory<List<TypeAdapterFactory>> {
        listOf(NullDefenseTypeAdapterFactory(Mandatory::class.java))
    }

    /**
     * Provides [RxJava2CallAdapterFactory] to create network response to Reactive streams.
     */
    factory {
        RxJava2CallAdapterFactory.create()
    }

    /**
     * Provides Single Okhttp3 Client for http layer
     */
    single {
        OkHttpClient().newBuilder()
            .build()
    }

    /**
     * Provides Instance of networking client.
     */
    single {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(get())
            .addCallAdapterFactory(get())
            .client(get())
            .build()
    }

    /**
     * Creates Repository Manager for the given repository
     */
    factory { (repository: Repository<*>) ->
        RepositoryManager(repository)
    }
}
