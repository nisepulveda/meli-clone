package com.nicolas.clonmeli.ui.productDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.clonmeli.data.repository.Repository
import com.nicolas.clonmeli.domain.MeliResult
import com.nicolas.clonmeli.domain.model.ProductDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    val productDetails: MutableLiveData<MeliResult<ProductDetail>> = MutableLiveData()

    fun getProductDetails(productId: String) {
        viewModelScope.launch {
            productDetails.value = repository.getProductsDetail(productId)
        }
    }
}