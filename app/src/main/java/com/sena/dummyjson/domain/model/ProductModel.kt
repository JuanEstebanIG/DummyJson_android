package com.sena.dummyjson.domain.model

data class ProductModel(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price : Double,
    val imageUrl: String = ""
)
