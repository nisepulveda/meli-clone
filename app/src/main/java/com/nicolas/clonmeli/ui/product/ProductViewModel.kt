package com.nicolas.clonmeli.ui.product

import androidx.lifecycle.*
import com.nicolas.clonmeli.data.repository.Repository
import com.nicolas.clonmeli.domain.MeliResult
import com.nicolas.clonmeli.domain.model.ProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    val productList = MutableLiveData<MeliResult<List<ProductItem>>>()

    fun searchProduct(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val products = repository.getProductsByName(query)
            productList.postValue(products)
        }
    }
}