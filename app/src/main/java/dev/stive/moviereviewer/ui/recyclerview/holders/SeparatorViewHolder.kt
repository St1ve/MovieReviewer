package dev.stive.moviereviewer.ui.recyclerview.holders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.stive.moviereviewer.R

class SeparatorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val rating: TextView = view.findViewById(R.id.separator_rating)

    fun bind(separatorText: String) {
        rating.text = separatorText
    }

    companion object {
        fun create(parent: ViewGroup): SeparatorViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_separator_rating, parent, false)
            return SeparatorViewHolder(view)
        }
    }

}