package com.example.movies.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.model.Movies

class MovieDataAdapter : RecyclerView.Adapter<MovieDataAdapter.ViewHolder>() {

    private var movieData: List<Movies> = listOf()
    private lateinit var itemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    fun setOnItemClickListener(clickListener: OnItemClickListener) {
        itemClickListener = clickListener
    }

    fun setMovieDataNew(data: List<Movies>) {
        movieData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_movie, parent, false
        )
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieData[position], itemClickListener)
    }

    override fun getItemCount() = movieData.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var movie: TextView = itemView.findViewById(R.id.textView_movieTitle)
        private var image: AppCompatImageView = itemView.findViewById(R.id.imageView)

        fun bind(movieData: Movies, listener: OnItemClickListener) {

            movie.text = movieData.title
            Glide.with(itemView)
                .asBitmap()
                .load("https://image.tmdb.org/t/p/w185_and_h278_bestv2${movieData.poster_path}")
                .into(image)

            itemView.setOnClickListener {
                listener.onItemClick(itemView, adapterPosition)
            }
        }

    }
}