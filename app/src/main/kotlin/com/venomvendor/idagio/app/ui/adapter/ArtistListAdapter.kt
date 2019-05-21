/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 21-May-2019.
 */

package com.venomvendor.idagio.app.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.venomvendor.idagio.R
import com.venomvendor.idagio.app.ui.GlideApp
import com.venomvendor.idagio.app.ui.factory.OnItemClickListener
import com.venomvendor.idagio.app.util.DiffUtilHelper
import com.venomvendor.idagio.search.model.Person

/**
 * Recycler Adapter for displaying list of results.
 */
internal class ArtistListAdapter(context: Context) :
    ListAdapter<Person, ArtistListAdapter.ArtistViewHolder>(DiffUtilHelper.PERSON_DIFF) {

    private val glide = GlideApp.with(context)

    // Event listener
    private var itemClickListener: OnItemClickListener<Person>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val view = LayoutInflater.from(parent.context)
            // Update item view
            .inflate(R.layout.artist_item, parent, false)

        return ArtistViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        // Get item
        val artist = getItem(position)

        // Update views
        holder.artistId.text = artist.id.toString()

        holder.artistDesc.text = artist.forename
        holder.artistType.text = artist.score.toString()

        // Load image
        glide.load("https://api.adorable.io/avatars/$position")
            .centerCrop()
            // place holder until image loads
            .placeholder(R.drawable.circle_bg)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.media)
    }

    fun setOnItemClickLister(listener: OnItemClickListener<Person>?) {
        itemClickListener = listener
    }

    /**
     * View Holder pattern, used by recycler view.
     */
    internal inner class ArtistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        internal val artistId = itemView.findViewById<TextView>(R.id.artist_id)
        internal val artistDesc = itemView.findViewById<TextView>(R.id.artist_desc)
        internal val artistType = itemView.findViewById<TextView>(R.id.artist_type)
        internal val media = itemView.findViewById<ImageView>(R.id.media)

        init {
            // Add event listener
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val selectedPos = adapterPosition
            if (selectedPos == RecyclerView.NO_POSITION) {
                return
            }

            // Pass on to root level event listener
            itemClickListener?.onClick(getItem(selectedPos), view, selectedPos)
        }
    }
}
