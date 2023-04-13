package com.nicolas.clonmeli.data.remote

import com.google.gson.annotations.SerializedName

data class ProductResponseApi (
    @SerializedName("results") val results: List<RemoteProductItem>? = listOf()
)

data class RemoteProductItem(
    @SerializedName("id") var id: String? = String(),
    @SerializedName("title") val title: String? = String(),
    @SerializedName("price") val price: Long? = null,
    @SerializedName("thumbnail") val thumbnail: String? = String()
)