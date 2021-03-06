package com.example.movies.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.model.MovieData

class MovieDataAdapter : RecyclerView.Adapter<MovieDataAdapter.ViewHolder>() {

    private var movieData: List<MovieData> = listOf()
    private val cardsLimit = 10
    private lateinit var itemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    fun setOnItemClickListener(clickListener: OnItemClickListener) {
        itemClickListener = clickListener
    }

    fun setMovieDataNew(data: List<MovieData>) {
        movieData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_movie, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(movieData[position], itemClickListener)

    override fun getItemCount() = if (movieData.size >= cardsLimit) {
        cardsLimit
    } else {
        movieData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var movie: TextView = itemView.findViewById(R.id.textView_movieTitle)
        private var image: AppCompatImageView = itemView.findViewById(R.id.imageView)

        fun bind(movieData: MovieData, listener: OnItemClickListener) {
            movie.text = movieData.titles
            image.setImageResource(movieData.image)

            itemView.setOnClickListener {
                listener.onItemClick(it, adapterPosition)
            }
        }
    }
}