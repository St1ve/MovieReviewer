package dev.stive.moviereviewer.ui.recyclerview.adapters

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import dev.stive.moviereviewer.ui.recyclerview.holders.LoadingViewHolder

class LoadingAdapter(private val retry: () -> Unit) : LoadStateAdapter<LoadingViewHolder>() {
    override fun onBindViewHolder(holder: LoadingViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadingViewHolder {
        return LoadingViewHolder.create(parent, retry)
    }
}