package com.nicolas.clonmeli.data.remote

import com.nicolas.clonmeli.domain.model.ProductAttribute
import com.nicolas.clonmeli.domain.model.ProductDetail
import com.nicolas.clonmeli.domain.model.ProductItem

fun ProductResponseApi.transformToProductList(): List<ProductItem> {
    return (results?.map { product ->
        ProductItem(
            id = product.id ?: String(),
            title = product.title ?: String(),
            price = product.price ?: 0L,
            thumbnail = product.thumbnail ?: String()
        )
    } ?: listOf())
}

fun ProductDetailApi.transformToProductDetail(): ProductDetail {
    return ProductDetail(
        id = id ?: String(),
        title = title ?: String(),
        price = price ?: 0L,
        soldQuantity = soldQuantity ?: 0,
        pictures = pictures?.map { picture -> picture.url ?: String() } ?: listOf(),
        attributes = attributes?.map { attribute ->
            ProductAttribute(
                name = attribute.name ?: String(),
                value = attribute.valueName
            )
        } ?: listOf(),
        warranty = warranty ?: String(),
        availableQuantity = availableQuantity ?: 0
    )
}