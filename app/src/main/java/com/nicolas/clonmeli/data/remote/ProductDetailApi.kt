package com.nicolas.clonmeli.data.remote

import com.google.gson.annotations.SerializedName

data class ProductDetailApi(
    @SerializedName("id") val id: String? = String(),
    @SerializedName("title") val title: String? = String(),
    @SerializedName("price") val price: Long? = null,
    @SerializedName("sold_quantity") val soldQuantity: Int? = null,
    @SerializedName("pictures") val pictures: List<ProductPicturesApi>? = listOf(),
    @SerializedName("attributes") val attributes: List<ProductAttributesApi>? = listOf(),
    @SerializedName("warranty") val warranty: String? = String(),
    @SerializedName("available_quantity") val availableQuantity: Int? = null

)

data class ProductPicturesApi(
    @SerializedName("id") val id: String? = String(),
    @SerializedName("url") val url: String? = String()
)

data class ProductAttributesApi(
    @SerializedName("id") val id: String? = String(),
    @SerializedName("name") val name: String? = String(),
    @SerializedName("value_name") val valueName: String? = String()
)
