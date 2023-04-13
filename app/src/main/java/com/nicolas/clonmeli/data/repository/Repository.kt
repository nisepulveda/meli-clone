package com.nicolas.clonmeli.data.repository

import androidx.lifecycle.LiveData
import com.nicolas.clonmeli.data.remote.ProductResponseApi
import com.nicolas.clonmeli.data.remote.RemoteDatasource
import com.nicolas.clonmeli.domain.MeliResult
import com.nicolas.clonmeli.domain.model.ProductDetail
import com.nicolas.clonmeli.domain.model.ProductItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteProducts: RemoteDatasource
) {
    suspend fun getProductsByName(query: String): MeliResult<List<ProductItem>> {
        return withContext(Dispatchers.IO) {
            remoteProducts.getProductsByName(query)
        }
    }

    suspend fun getProductsDetail(productId: String): MeliResult<ProductDetail> {
        return withContext(Dispatchers.IO) {
            remoteProducts.getProductsDetail(productId)
        }
    }
}