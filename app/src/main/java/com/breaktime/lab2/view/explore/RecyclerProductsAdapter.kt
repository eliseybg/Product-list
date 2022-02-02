package com.breaktime.lab2.view.explore

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.breaktime.lab2.R
import com.breaktime.lab2.api.model.Product
import com.breaktime.lab2.databinding.SearchItemBinding
import com.breaktime.lab2.util.ResourcesProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RecyclerProductsAdapter(private val resourcesProvider: ResourcesProvider) :
    RecyclerView.Adapter<RecyclerProductsAdapter.ViewHolder>() {
    var items = emptyList<Product>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: SearchItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.search_item, parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    inner class ViewHolder(private val binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product, position: Int) = with(binding) {
            binding.product = product
            image.setImageDrawable(resourcesProvider.getDrawable(R.drawable.loading))
            GlobalScope.launch {
                product.bitmap.collect {
                    if (it != null)
                        withContext(Dispatchers.Main) {
                            image.setImageBitmap(it)
                        }
                }
            }

            if (product.isVisible) {
                expand.visibility = View.VISIBLE
            } else {
                expand.visibility = View.GONE
            }
            root.setOnClickListener {
                product.isVisible = !product.isVisible
                notifyItemChanged(position)
            }
            more.setOnClickListener {
                product.isVisible = !product.isVisible
                notifyItemChanged(position)
            }
            favorite.setOnClickListener {
                favorite.setCompoundDrawables(
                    resourcesProvider.getDrawable(R.drawable.ic_favorite_24), null, null, null
                )
            }
        }
    }
}
