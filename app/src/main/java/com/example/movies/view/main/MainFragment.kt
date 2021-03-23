package com.example.movies.view.main

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.ServiceData
import com.example.movies.databinding.MainContentBinding
import com.example.movies.model.Categories
import com.example.movies.viewModel.AppState
import com.example.movies.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    private var _binding: MainContentBinding? = null
    private val binding get() = _binding!!
    private val connection = Connect { Toast.makeText(context, "$it", Toast.LENGTH_LONG).show() }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            (context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).registerDefaultNetworkCallback(
                object :
                    ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) = makeToast(true)
                    override fun onLost(network: Network) = makeToast(false)
                })
        } else {
            context?.let {
                LocalBroadcastManager.getInstance(it)
                    .registerReceiver(connection,
                        IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
            }
        }
    }

    private fun makeToast(status: Boolean) =
        Toast.makeText(context, "$status", Toast.LENGTH_LONG).show()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = MainContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
    }

    private fun initRecyclerView(recyclerView: RecyclerView, data: List<Categories>) {
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        val adapter = MovieAdapter(activity?.supportFragmentManager)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        adapter.setMovieData(data)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                val recyclerView: RecyclerView = binding.recyclerMovieCategories
                initRecyclerView(recyclerView, appState.data)
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar
                    .make(binding.recyclerMovieCategories, "Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") { viewModel.getMoviesData() }
                    .show()
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        context?.let {
            LocalBroadcastManager.getInstance(it).unregisterReceiver(connection)
        }
        _binding = null
    }
}
