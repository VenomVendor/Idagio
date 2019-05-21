/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 21-May-2019.
 */

package com.venomvendor.idagio

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.google.gson.Gson
import com.venomvendor.idagio.app.ui.ArtistListFragment
import com.venomvendor.idagio.helper.BaseTest
import com.venomvendor.idagio.helper.RecyclerViewItemCountAssertion.withItemCount
import com.venomvendor.idagio.search.model.SearchArtist
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import org.koin.core.get

class MainActivityTest : BaseTest() {
//    @Rule
//    @JvmField
//    var activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)
//
//    override fun setUp() {
//        super.setUp()
//        activityTestRule.launchActivity(Intent())
//    }

    @Test
    fun getDataFromServerSuccessfullyWithFourResults() {
        val response = with(this).read("response.json")

        /* Launches Fragment directly */
        launchFragmentInContainer<ArtistListFragment>(null, R.style.AppTheme)

        val server = get<MockWebServer>()
        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(response)
        )

        val query = "Aria"

        // Start Typing
        onView(withId(R.id.search))
            .perform(
                typeText(query),
                closeSoftKeyboard()
            )

        val resObj = Gson().fromJson<SearchArtist>(response, SearchArtist::class.java)

        onView(withId(R.id.artist_list))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(resObj.artist.people.count() - 1))

        onView(withId(R.id.artist_list))
            .check(withItemCount(resObj.artist.people.count()))
    }

    @Test
    fun getZeroResults() {
        launchFragmentInContainer<ArtistListFragment>(null, R.style.AppTheme)

        val query = "      "

        // Start Typing
        onView(withId(R.id.search))
            .perform(
                typeText(query),
                closeSoftKeyboard()
            )

        onView(withId(R.id.artist_list))
            .check(withItemCount(0))
    }

    @Test
    fun getZeroResultsWithTwoChars() {
        launchFragmentInContainer<ArtistListFragment>(null, R.style.AppTheme)

        // Dot get's added automatically, need to investigate
        val query = "   A   "

        // Start Typing
        onView(withId(R.id.search))
            .perform(
                typeText(query),
                closeSoftKeyboard()
            )

        onView(withId(R.id.artist_list))
            .check(withItemCount(0))
    }
}
