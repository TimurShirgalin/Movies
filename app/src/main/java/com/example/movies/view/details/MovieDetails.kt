package com.example.movies.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.movies.R
import com.example.movies.databinding.FragmentMovieDetailsBinding
import com.example.movies.model.MovieData
import kotlinx.android.synthetic.main.main_activity.*

class MovieDetails : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity?.toolbar?.setNavigationIcon(R.drawable.back)

        val movieData = arguments?.getParcelable<MovieData>(KEY)

        if (movieData != null) {
            binding.detailsImage.setImageResource(movieData.image)
            binding.detailsTitle.text = movieData.titles
            binding.detailsCategory.text = movieData.category
            binding.detailsDescription.text = movieData.description
        }
    }

    companion object {
        const val KEY = "movieDetails"

        fun newInstance(bundle: Bundle): MovieDetails {
            val fragment = MovieDetails()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}