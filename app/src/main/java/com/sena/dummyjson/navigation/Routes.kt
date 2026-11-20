package com.sena.dummyjson.navigation

import kotlinx.serialization.Serializable

@Serializable
data object ProductList

@Serializable
data class ProductDetail(
    val productId: Int,
)

@Serializable
data class ProductForm(
    val productId: Int? = null,
)
