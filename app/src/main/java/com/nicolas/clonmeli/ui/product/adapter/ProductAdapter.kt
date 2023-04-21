package com.nicolas.clonmeli.ui.product.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.nicolas.clonmeli.databinding.ItemProductBinding
import com.nicolas.clonmeli.domain.model.ProductItem
import com.nicolas.clonmeli.shared.extensions.formatToCurrency
import javax.inject.Inject

class ProductAdapter(private val onItemClickProduct: (String) -> Unit) :
    ListAdapter<ProductItem, ProductAdapter.ProductViewHolder>(DiffCallBack) {

//    var onItemClick: ((item: ProductItem, view: View) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onItemClickProduct)
    }

    object DiffCallBack : DiffUtil.ItemCallback<ProductItem>() {
        override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem == newItem
        }
    }

    inner class ProductViewHolder(private var binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root) {
//        init {
//            binding.root.setOnClickListener(this)
//        }

        fun bind(data: ProductItem, onItemClickProduct: (String) -> Unit) {
            binding.apply {
                root.setOnClickListener {
                    onItemClickProduct.invoke(data.id)
                }
                imgProduct.load(data.thumbnail) { crossfade(true) }
                title.text = data.title
                price.text = data.price.toInt().formatToCurrency()
            }
        }

//        override fun onClick(view: View?) {
//            if (view != null) {
//                getItem(absoluteAdapterPosition)?.let {
//                    onItemClick?.invoke(it, view) }
//            }
//        }
    }
}