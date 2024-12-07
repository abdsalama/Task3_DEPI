package com.abdo.task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Movie(val title: String, val description: String, val imageResId: Int)

class MovieAdapter(private val movieList: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.movieImage)
        val titleView: TextView = itemView.findViewById(R.id.movieTitle)
        val descriptionView: TextView = itemView.findViewById(R.id.movieDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.titleView.text = movie.title
        holder.descriptionView.text = movie.description
        holder.imageView.setImageResource(movie.imageResId)
    }

    override fun getItemCount(): Int = movieList.size
}
