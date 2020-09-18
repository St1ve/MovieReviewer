package dev.stive.moviereviewer.ui.recyclerview.holders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import dev.stive.moviereviewer.R
import dev.stive.moviereviewer.databinding.ItemMovieFooterBinding

class LoadingViewHolder(
    private val binding: ItemMovieFooterBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error)
            binding.errorMsg.text = loadState.error.localizedMessage
        binding.pbLoading.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState !is LoadState.Loading
        binding.errorMsg.isVisible = loadState !is LoadState.Loading
    }

    companion object{
        fun create(parent: ViewGroup, retry: () -> Unit): LoadingViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie_footer,parent, false)
            val binding = ItemMovieFooterBinding.bind(view)
            return LoadingViewHolder(binding, retry)
        }
    }
}