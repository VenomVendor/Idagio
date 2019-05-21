/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 20-May-2019.
 */

package com.venomvendor.idagio.core.data

import com.venomvendor.idagio.core.data.factory.Repository
import com.venomvendor.idagio.core.di.coreModule
import com.venomvendor.idagio.core.helper.BaseTest
import com.venomvendor.idagio.core.helper.model.TestData
import io.reactivex.Single
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.inject
import org.mockito.Mockito
import org.mockito.Mockito.`when` as mockitoWhen

/**
 * Test Repository manager with mock implementation
 */
class RepositoryManagerTest : BaseTest() {
    private val mockRepository = Mockito.mock(Repository::class.java)

    private val fakeRepoManager: RepositoryManager<TestData> by inject {
        parametersOf(mockRepository)
    }

    override fun before() {
        super.before()
        startKoin {
            modules(coreModule)
        }
    }

    @Test
    fun `test RepositoryManager to get data from Cache (+ve)`() {
        val testData = "Test"
        mockitoWhen(mockRepository.cachedData)
            .thenReturn(
                TestData(1, testData)
            )
        fakeRepoManager.execute()
            .test()
            .assertSubscribed()
            .assertValue {
                it.name == testData
            }
    }

    @Test
    fun `test RepositoryManager to get data from Request (+ve)`() {
        val testDataReq = "Test"
        mockitoWhen(mockRepository.cachedData)
            .thenReturn(
                null
            )

        mockitoWhen(mockRepository.request)
            .thenReturn(
                Single.just(TestData(1, testDataReq))
            )
        fakeRepoManager.execute()
            .test()
            .assertSubscribed()
            .assertValue {
                it.name == testDataReq
            }
    }

    @Test
    fun `test RepositoryManager, ensure data from Request (+ve)`() {
        val testData = "Test"
        mockitoWhen(mockRepository.request)
            .thenReturn(
                Single.just(TestData(1, testData))
            )
        fakeRepoManager.execute()
            .test()
            .assertSubscribed()
            .assertValue {
                it.name == testData
            }
    }
}
