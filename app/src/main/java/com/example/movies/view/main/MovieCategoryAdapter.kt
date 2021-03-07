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
import com.example.movies.model.CardData
import com.example.movies.view.details.MovieDetails

class MovieAdapter(private val manager: FragmentManager?) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movieCategories: List<CardData> = listOf()

    fun setMovieData(data: List<CardData>) {
        movieCategories = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.fragment_movie_categories, viewGroup, false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) =
        viewHolder.bind(movieCategories[i])

    override fun getItemCount() = movieCategories.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var movieCategory: TextView = itemView.findViewById(R.id.textView_movieCategory)

        fun bind(cardData: CardData) {
            movieCategory.text = cardData.category
            setMovieDataRecycler(cardData)
        }

        private fun setMovieDataRecycler(cardData: CardData) {
            MovieDataAdapter().let {
                it.setMovieDataNew(cardData.movieData)
                it.setOnItemClickListener(object : MovieDataAdapter.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        manager?.beginTransaction()?.apply {
                            add(
                                R.id.container, MovieDetails.newInstance(Bundle().apply {
                                    putParcelable(MovieDetails.KEY, cardData.movieData[position])
                                })
                            )
                            addToBackStack(null)
                            commit()
                        }
                    }
                })
                val movieDataRecycler: RecyclerView = itemView.findViewById(R.id.recycler_movieData)
                movieDataRecycler.apply {
                    setHasFixedSize(true)
                    layoutManager =
                        LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                    this.adapter = it
                }
            }
        }
    }
}

