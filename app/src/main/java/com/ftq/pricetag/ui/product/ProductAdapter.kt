package com.ftq.pricetag.ui.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ftq.pricetag.databinding.ItemProductBinding
import com.ftq.pricetag.entity.ProductEntity


class ProductAdapter(
    private var products: List<ProductEntity>,
    private val onEditClick: (ProductEntity) -> Unit,
    private val onDeleteClick: (ProductEntity) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val branch = products[position]
        holder.bind(branch)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun updateData(productEntities: List<ProductEntity>) {
        products = productEntities
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(productEntity: ProductEntity) {
            binding.apply {
                textViewSkuCode.text = productEntity.sku_code
                textViewProductName.text = productEntity.name
                buttonEditProduct.setOnClickListener { onEditClick(productEntity) }
                buttonDeleteProduct.setOnClickListener { onDeleteClick(productEntity) }
            }
        }
    }
}
