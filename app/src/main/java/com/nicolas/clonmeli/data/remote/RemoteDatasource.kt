package com.nicolas.clonmeli.data.remote

import androidx.lifecycle.LiveData
import com.nicolas.clonmeli.domain.MeliResult
import com.nicolas.clonmeli.domain.model.ProductDetail
import com.nicolas.clonmeli.domain.model.ProductItem
import retrofit2.Response
import javax.inject.Inject

class RemoteDatasource @Inject constructor(private val apiService: MeliApi) {

    suspend fun getProductsByName(query: String): MeliResult<List<ProductItem>> {
        val result = executeRetrofitRequest { apiService.getProductsByName(query) }
        return handleResultRetrofit(result) {
            it.transformToProductList()
        }
    }

    suspend fun getProductsDetail(productId: String): MeliResult<ProductDetail> {
        val result = executeRetrofitRequest { apiService.getProductsDetail(productId) }
        return handleResultRetrofit(result) {
            it.transformToProductDetail()
        }
    }
}