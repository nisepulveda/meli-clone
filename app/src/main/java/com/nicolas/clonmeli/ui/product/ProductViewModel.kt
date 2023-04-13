package com.nicolas.clonmeli.ui.product

import androidx.lifecycle.*
import com.nicolas.clonmeli.data.repository.Repository
import com.nicolas.clonmeli.domain.MeliResult
import com.nicolas.clonmeli.domain.model.ProductDetail
import com.nicolas.clonmeli.domain.model.ProductItem

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    val productList = MutableLiveData<MeliResult<List<ProductItem>>>()
    val productDetails: MutableLiveData<MeliResult<ProductDetail>> = MutableLiveData()

    fun searchProduct(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val products = repository.getProductsByName(query)
            productList.postValue(products)
        }
    }

    fun getProductDetails(productId: String) {
        viewModelScope.launch {
            productDetails.value = repository.getProductsDetail(productId)
        }
    }
}