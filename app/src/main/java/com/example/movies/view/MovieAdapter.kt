package com.example.movies.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.model.CardData
import com.example.movies.model.CardSource

class MovieAdapter(private val dataSource: CardSource) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    lateinit var itemClickListener: OnItemClickListener

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v: View = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.fragment_movie_categories, viewGroup, false
        )
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(dataSource.getCardData(i), itemClickListener)
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    fun setOnItemClickListener(clickListener: OnItemClickListener) {
        itemClickListener = clickListener
    }

    override fun getItemCount() = dataSource.size()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var movieCategory: TextView? = itemView.findViewById(R.id.textView_movieCategory)
        private var movieTitle: TextView? =
            itemView.findViewById(R.id.textView_movieTitle)

        fun bind(cardData: CardData?, listener: OnItemClickListener) {
            movieCategory?.text = cardData?.getCategory()
            movieTitle?.text = cardData?.getTitle()
            itemView.setOnClickListener {
                listener.onItemClick(it, adapterPosition)
            }
        }
    }
}