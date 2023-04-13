package com.nicolas.clonmeli.domain.model


data class ProductDetail(
    val id: String,
    val title: String,
    val price: Long,
    val soldQuantity: Int,
    val pictures: List<String>,
    val attributes: List<ProductAttribute>,
    val warranty: String,
    val availableQuantity: Int
)

data class ProductAttribute(
    val name: String,
    val value: String? = null,
)