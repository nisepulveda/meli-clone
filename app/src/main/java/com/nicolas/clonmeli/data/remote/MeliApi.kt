package com.nicolas.clonmeli.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MeliApi {

    @GET("sites/MLC/search")
    suspend fun getProductsByName(
        @Query("q") query: String
    ): Response<ProductResponseApi>

    @GET("items/{productId}")
    suspend fun getProductsDetail(
        @Path("productId") productId: String
    ): Response<ProductDetailApi>
}