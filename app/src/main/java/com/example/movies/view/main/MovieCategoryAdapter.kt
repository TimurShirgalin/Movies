package com.example.movies.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.model.Categories
import com.example.movies.view.details.MovieDetails

class MovieAdapter(private val manager: FragmentManager?) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movieCategories: List<Categories> = listOf()

    fun setMovieData(data: List<Categories>) {
        movieCategories = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v: View = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.fragment_movie_categories, viewGroup, false
        )
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(movieCategories[i])
    }

    override fun getItemCount() = movieCategories.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var movieCategory: TextView = itemView.findViewById(R.id.textView_movieCategory)

        fun bind(cardData: Categories) {
            movieCategory.text = cardData.categoryName
            setMovieDataRecycler(cardData)
        }

        private fun setMovieDataRecycler(cardData: Categories) {
            val movieDataRecycler: RecyclerView = itemView.findViewById(R.id.recycler_movieData)
            val movieDataLayoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            val movieDataAdapter = MovieDataAdapter()

            movieDataRecycler.layoutManager = movieDataLayoutManager
            movieDataRecycler.adapter = movieDataAdapter
            movieDataAdapter.setMovieDataNew(cardData.movieList)

            movieDataAdapter.setOnItemClickListener(object : MovieDataAdapter.OnItemClickListener {
                override fun onItemClick(view: View?, position: Int) {
                    if (manager != null) {
                        val bundle = Bundle()
                        bundle.putParcelable(MovieDetails.KEY, cardData.movieList[position])
                        manager.beginTransaction()
                            .replace(R.id.container, MovieDetails.newInstance(bundle))
                            .addToBackStack(null)
                            .commit()
                    }
                }
            })
        }
    }
}

