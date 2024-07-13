package com.ftq.pricetag.ui.retailer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ftq.pricetag.entity.RetailerEntity
import com.ftq.pricetag.databinding.RetailerItemBinding

class RetailerAdapter : RecyclerView.Adapter<RetailerAdapter.RetailerViewHolder>() {
    private var retailers = emptyList<RetailerEntity>()
    inner class RetailerViewHolder(val binding: RetailerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val action = RetailerFragmentDirections.actionRetailersFragmentToUpdateRetailerFragment(
                    retailerId = retailers[adapterPosition].id,
                    retailerName = retailers[adapterPosition].name
                )
                it.findNavController().navigate(action)
            }

            binding.buttonDelete.setOnClickListener {
                val action = RetailerFragmentDirections.actionRetailersFragmentToDeleteRetailerFragment(
                    retailerId = retailers[adapterPosition].id
                )
                it.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetailerViewHolder {
        val binding = RetailerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RetailerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RetailerViewHolder, position: Int) {
        val currentRetailer = retailers[position]
        holder.binding.textViewName.text = currentRetailer.name
    }

    override fun getItemCount(): Int {
        return retailers.size
    }

    fun setRetailers(retailers: List<RetailerEntity>) {
        this.retailers = retailers
        notifyDataSetChanged()
    }

}