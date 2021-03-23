package com.example.movies.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.MainContentBinding
import com.example.movies.hide
import com.example.movies.model.CardData
import com.example.movies.show
import com.example.movies.viewModel.AppState
import com.example.movies.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    private var _binding: MainContentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = MainContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java).apply {
            getLiveData().observe(viewLifecycleOwner, { renderData(it) })
            getMovieDataFromLocalSource()
        }
    }

    private fun initRecyclerView(recyclerView: RecyclerView, data: List<CardData>) {
        val adapter = MovieAdapter(activity?.supportFragmentManager).apply { setMovieData(data) }
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.loadingLayout.hide()
                val recyclerView: RecyclerView = binding.recyclerMovieCategories
                initRecyclerView(recyclerView, appState.data)
            }
            is AppState.Error -> {
                binding.loadingLayout.hide()
                Snackbar
                    .make(binding.recyclerMovieCategories, "Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") { viewModel.getMovieDataFromLocalSource() }
                    .show()
            }
            is AppState.Loading -> {
                binding.loadingLayout.show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}