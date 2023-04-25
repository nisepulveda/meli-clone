package com.nicolas.clonmeli.ui.productDetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicolas.clonmeli.databinding.ItemProductAttributeBinding
import com.nicolas.clonmeli.domain.model.ProductAttribute


class ProductAttributeAdapter(private val attribute: List<ProductAttribute>):
    RecyclerView.Adapter<ProductAttributeAdapter.ProductAttributeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAttributeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductAttributeBinding.inflate(inflater, parent, false)
        return ProductAttributeViewHolder(binding)
    }

    override fun getItemCount(): Int = attribute.size

    override fun onBindViewHolder(holder: ProductAttributeViewHolder, position: Int) {
        holder.bind(attribute[position])
    }

    inner class ProductAttributeViewHolder(private val binding: ItemProductAttributeBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ProductAttribute) {
            binding.apply {
                tvAttributeName.text = data.name
                tvAttributeValue.text = data.value
            }
        }
    }
}