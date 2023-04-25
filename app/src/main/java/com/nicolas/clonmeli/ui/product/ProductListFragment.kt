package com.nicolas.clonmeli.ui.product

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicolas.clonmeli.databinding.FragmentProductListBinding
import com.nicolas.clonmeli.ui.product.adapter.ProductAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductListFragment: Fragment() {

    private val productAdapter = ProductAdapter { productId ->
        goToProductDetail(productId)
    }

    private val viewModel by viewModels<ProductViewModel>()
    private var binding: FragmentProductListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.productList.observe(viewLifecycleOwner) {
            productAdapter.submitList(it.data)
        }
    }

    private fun setupUI() {
        binding?.apply {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        if (it.isNotEmpty()) {
                            recyclerViewProducts.scrollToPosition(0)
                            viewModel.searchProduct(query)
                            searchView.clearFocus()
                        } else {
                            searchView.clearFocus()
                        }
                    }
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean = false
            })

            recyclerViewProducts.adapter = productAdapter
            recyclerViewProducts.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = productAdapter
                setHasFixedSize(true)
            }
        }
    }

    private fun goToProductDetail(productId: String) {
        val request = NavDeepLinkRequest.Builder
            .fromUri("meli-app://com.nicolas.app/product_detail_fragment/$productId".toUri())
            .build()
        findNavController().navigate(request)
    }
}