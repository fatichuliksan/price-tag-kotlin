package com.ftq.pricetag.ui.branch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ftq.pricetag.model.BranchModel
import com.ftq.pricetag.databinding.ItemBranchBinding


class BranchAdapter(
    private var branches: List<BranchModel>,
    private val onEditClick: (BranchModel) -> Unit,
    private val onDeleteClick: (BranchModel) -> Unit
) : RecyclerView.Adapter<BranchAdapter.BranchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BranchViewHolder {
        val binding = ItemBranchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BranchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BranchViewHolder, position: Int) {
        val branch = branches[position]
        holder.bind(branch)
    }

    override fun getItemCount(): Int {
        return branches.size
    }

    fun updateData(newBranches: List<BranchModel>) {
        branches = newBranches
        notifyDataSetChanged()
    }

    inner class BranchViewHolder(private val binding: ItemBranchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(branch: BranchModel) {
//            binding.textViewBranchName.text = branch.branchName
//            binding.textViewRetailerName.text = branch.retailerName
//
//            binding.buttonEditBranch.setOnClickListener {
//                onEditClick(branch)
//            }
//
//            binding.buttonDeleteBranch.setOnClickListener {
//                onDeleteClick(branch)
//            }
            // code atas bawah sama saja beda style

            binding.apply {
                textViewBranchName.text = branch.branchName
                textViewRetailerName.text = branch.retailerName
                buttonEditBranch.setOnClickListener { onEditClick(branch) }
                buttonDeleteBranch.setOnClickListener { onDeleteClick(branch) }
            }
        }
    }
}
