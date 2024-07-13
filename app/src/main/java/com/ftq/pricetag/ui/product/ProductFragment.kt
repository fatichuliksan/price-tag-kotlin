package com.ftq.pricetag.ui.retailer

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ftq.pricetag.AppDatabase
import com.ftq.pricetag.databinding.FragmentProductBinding
import com.ftq.pricetag.entity.ProductEntity
import com.ftq.pricetag.repository.ProductRepository
import com.ftq.pricetag.ui.product.ProductAdapter
import com.ftq.pricetag.ui.product.ProductViewModel
import com.ftq.pricetag.ui.product.ProductViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ProductFragment : Fragment() {

    private lateinit var binding: FragmentProductBinding
    private lateinit var viewModel: ProductViewModel
    private lateinit var productAdapter: ProductAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductBinding.inflate(inflater, container, false)
        val productDao = AppDatabase.getDatabase(requireContext()).productDao()

        val productRepository = ProductRepository(productDao)

        viewModel = ViewModelProvider(this, ProductViewModelFactory(productRepository)).get(
            ProductViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setupRecyclerView()
        setupObservers()

        binding.buttonAddProduct.setOnClickListener {
            showDialog(null)
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        productAdapter = ProductAdapter(
            listOf(),
            onEditClick = { product ->
                showDialog(product)
            },
            onDeleteClick = { product ->
                viewModel.deleteBranch(product)
            }
        )

        binding.recyclerViewProduct.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productAdapter
        }
    }

    private fun setupObservers() {
        viewModel.products.observe(viewLifecycleOwner, Observer { products ->
            productAdapter.updateData(products)
        })
    }

    private fun showDialog(productEntity: ProductEntity?) {
        val dialogView = LayoutInflater.from(context).inflate(com.ftq.pricetag.R.layout.dialog_product, null)
        val branchNameInput = dialogView.findViewById<EditText>(com.ftq.pricetag.R.id.editTextProductName)
        val productSkuCodeInput = dialogView.findViewById<EditText>(com.ftq.pricetag.R.id.editTextSkuCode)

        if (productEntity != null) {
            branchNameInput.setText(productEntity.name)
            productSkuCodeInput.setText(productEntity.sku_code)
        }
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(if (productEntity == null) "Add Product" else "Edit Product")
            .setView(dialogView)
            .setPositiveButton("Save") { dialog, _ ->
                val name = branchNameInput.text.toString()
                val skuCode = productSkuCodeInput.text.toString()

                if (productEntity == null) {
                    viewModel.addBranch(ProductEntity(0,skuCode, name))
                } else {
                    viewModel.updateBranch(ProductEntity(productEntity.id,skuCode, name))
                }

                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
