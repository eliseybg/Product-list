package com.breaktime.lab2.view.explore

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.breaktime.lab2.R
import com.breaktime.lab2.databinding.FragmentExploreBinding
import com.breaktime.lab2.util.ResourcesProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@AndroidEntryPoint
class ExploreFragment : Fragment() {
    private lateinit var binding: FragmentExploreBinding
    private val viewModel: ExploreViewModel by viewModels()

    @Inject
    lateinit var resourcesProvider: ResourcesProvider

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_explore, container, false
        )
        binding.apply {
            currency.setOnClickListener {
                findNavController().navigate(R.id.currencyFragment)
            }
            search.setOnClickListener {
                findNavController().navigate(R.id.searchFragment)
            }
            noConnection.setOnClickListener {
                loadList()
            }
            val adapter = RecyclerProductsAdapter(resourcesProvider)
            list.layoutManager = LinearLayoutManager(context)
            list.adapter = adapter
            loadList()
        }
        return binding.root
    }

    private fun loadList() {
        val products = viewModel.getAllProducts()
        if (isInternetAvailable()) {
            showNoInternet(true)
            products.onEach {
                if (it == null)
                    showApiProblem()
                else
                    (binding.list.adapter as RecyclerProductsAdapter).items = it
            }.launchIn(lifecycleScope)
        } else {
            showNoInternet(false)
        }
    }

    private fun showNoInternet(isConnected: Boolean) {
        if (isConnected) {
            binding.list.visibility = View.VISIBLE
            binding.noConnection.visibility = View.GONE
            binding.noApi.visibility = View.GONE
        } else {
            binding.noConnection.visibility = View.VISIBLE
            binding.list.visibility = View.GONE
            binding.noApi.visibility = View.GONE
        }
    }

    private fun showApiProblem() {
        binding.noApi.visibility = View.VISIBLE
        binding.list.visibility = View.GONE
        binding.noConnection.visibility = View.GONE
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetwork != null
    }
}