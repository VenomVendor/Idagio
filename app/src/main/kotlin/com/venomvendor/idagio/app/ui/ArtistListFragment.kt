/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 21-May-2019.
 */

package com.venomvendor.idagio.app.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.text.trimmedLength
import androidx.recyclerview.widget.LinearLayoutManager
import com.venomvendor.idagio.R
import com.venomvendor.idagio.app.common.BaseFragment
import com.venomvendor.idagio.app.ui.adapter.ArtistListAdapter
import com.venomvendor.idagio.app.ui.factory.OnItemClickListener
import com.venomvendor.idagio.app.util.EspressoIdlingResource
import com.venomvendor.idagio.search.model.Person
import com.venomvendor.idagio.search.viewmodel.SearchArtistViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.artist_list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Main View for listing Artists.
 */
class ArtistListFragment : BaseFragment(), OnItemClickListener<Person> {

    private val minThreshold = 3
    private val searchArtistViewModel: SearchArtistViewModel by viewModel()

    private val disposable = CompositeDisposable()

    /* Adapter holding data. */
    private lateinit var artistAdapter: ArtistListAdapter

    @get:LayoutRes
    override val layout = R.layout.artist_list_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Initialize Listeners
        initListener()
    }

    override fun initViews(view: View) {
        // For performance
        artist_list.setHasFixedSize(true)
        // Set layout type

        updateLayoutManager()

        // Create Adapter
        artistAdapter = ArtistListAdapter(context!!)
        // Set adapter
        artist_list.adapter = artistAdapter
    }

    private fun updateLayoutManager() {
        artist_list.layoutManager = LinearLayoutManager(context)
    }

    /**
     * Initialize event listeners
     */
    private fun initListener() {
        // TODO: Replace with Extension
        search.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.trimmedLength() >= minThreshold) {
                    searchForArtist(s)
                }
            }
        })

        artistAdapter.setOnItemClickLister(this)

        refresh.setOnRefreshListener {
            if (search.text != null && search.text!!.trimmedLength() >= 3) {
                searchForArtist(search.text!!)
            }
        }
    }

    private fun searchForArtist(query: CharSequence) {
        EspressoIdlingResource.increment()
        progress_bar.visibility = View.VISIBLE

        disposable.add(
            searchArtistViewModel.getArtists(query.trim().toString())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEvent { _, _ ->
                    resetProgress()
                }
                .subscribe({ persons ->
                    handleData(persons)
                }, { ex ->
                    handleError(ex)
                })
        )
    }

    /**
     * Handler for data received
     *
     * @param response data
     */
    private fun handleData(response: List<Person>) {
        // Update data.
        artistAdapter.submitList(response)
    }

    /**
     * TODO: Handle errors
     */
    private fun handleError(error: Throwable) {
        // Show error toast
        showToast(error.message!!)
    }

    private fun resetProgress() {
        // Update indicators
        progress_bar.visibility = View.GONE
        refresh.isRefreshing = false

        EspressoIdlingResource.decrement()
    }

    override fun onClick(item: Person, view: View, position: Int) {
        showToast(item.forename!!)
    }

    private fun showToast(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
