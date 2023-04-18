package com.nicolas.clonmeli.ui.productDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.nicolas.clonmeli.R
import com.nicolas.clonmeli.databinding.FragmentProductDetailBinding
import com.nicolas.clonmeli.domain.MeliResult
import com.nicolas.clonmeli.domain.model.ProductDetail
import com.nicolas.clonmeli.shared.extensions.formatToCurrency
import com.nicolas.clonmeli.ui.product.ProductViewModel
import com.nicolas.clonmeli.ui.productDetail.adapter.ProductAttributeAdapter
import com.nicolas.clonmeli.ui.productDetail.adapter.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment: Fragment() {
    private var binding: FragmentProductDetailBinding? = null
    private val arguments: ProductDetailFragmentArgs by navArgs()
    private val viewModel by viewModels<ProductViewModel>()

    lateinit var viewPager: ViewPager2
    lateinit var viewPagerAdapter: ViewPagerAdapter

    lateinit var productAttributeAdapter: ProductAttributeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = view.findViewById(R.id.id_view_pager)

        setUpObservers()
        fetchData()
    }

    private fun setUpObservers() {
        fetchData()
        viewModel.productDetails.observe(viewLifecycleOwner) {
            it?.let {
                when(it) {
                    is MeliResult.Success -> displayDetails(it.data)
                    else -> {}
                }
            }
        }
    }

    private fun fetchData() {
        viewModel.getProductDetails(arguments.productId)
    }

    private fun displayDetails(productDetail: ProductDetail?) {
        productDetail.let { productDetails ->
            binding?.apply {

                productDetails?.title?.let {
                    tvTitle.isVisible = true
                    tvTitle.text = it
                }

                productDetails?.pictures?.let {
                    viewPagerAdapter = ViewPagerAdapter(it)
                    viewPager.adapter = viewPagerAdapter
                }

                productDetails?.price?.let {
                    tvPrice.text = it.toInt().formatToCurrency()
                }

                productDetails?.availableQuantity?.let {
                    tvProductStock.text = getString(R.string.label_stock_available, productDetail?.availableQuantity)
                }

                productDetails?.soldQuantity.let {
                    tvSoldQuantity.text = getString(R.string.label_sold_quantity, productDetail?.soldQuantity)
                }

                productDetails?.attributes?.let {
                    productAttributeAdapter = ProductAttributeAdapter(it)
                    rVProductsAttributes.adapter = productAttributeAdapter
                    rVProductsAttributes.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = productAttributeAdapter
                        setHasFixedSize(true)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
