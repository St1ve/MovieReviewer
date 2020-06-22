package dev.stive.moviereviewer.presenter.recyclerview.holders

import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import dev.stive.moviereviewer.R

class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val pbFooter = itemView.findViewById<ProgressBar>(R.id.pbFooter)

    fun bind(status: Boolean) {
        if (status)
            pbFooter.visibility = View.VISIBLE
        else
            pbFooter.visibility = View.INVISIBLE
    }
}