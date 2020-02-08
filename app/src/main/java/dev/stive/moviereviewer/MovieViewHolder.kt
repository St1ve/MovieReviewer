package dev.stive.moviereviewer

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleTv: TextView = itemView.findViewById(R.id.txtTitleMovie)
    val descriptionTv: TextView = itemView.findViewById(R.id.txtDescriptionMovie)
    val imagePosterTv: ImageView = itemView.findViewById(R.id.imgPosterMovie)

    fun bind(title:String, description:String, image:Bitmap){
        titleTv.text = title
        descriptionTv.text = description
        imagePosterTv.setImageBitmap(image)
    }
}