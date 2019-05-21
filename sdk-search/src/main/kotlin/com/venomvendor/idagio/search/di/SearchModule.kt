/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 21-May-2019.
 */

package com.venomvendor.idagio.search.di

import com.venomvendor.idagio.core.network.NetworkManager
import com.venomvendor.idagio.core.storage.RoomManager
import com.venomvendor.idagio.core.storage.factory.Storage
import com.venomvendor.idagio.search.factory.SearchService
import com.venomvendor.idagio.search.repository.SearchRepository
import com.venomvendor.idagio.search.viewmodel.SearchArtistViewModel
import org.koin.dsl.module

val searchModule = module {

    factory {
        SearchArtistViewModel()
    }

    factory {
        SearchRepository(get(), get())
    }

    factory {
        RoomManager() as Storage
    }

    factory {
        get<NetworkManager>().getWebService<SearchService>(get())
    }
}
