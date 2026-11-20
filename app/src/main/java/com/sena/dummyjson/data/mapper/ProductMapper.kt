package com.sena.dummyjson.data.mapper

import com.sena.dummyjson.data.remote.dto.product.ProductX
import com.sena.dummyjson.domain.model.ProductModel

fun ProductX.toDomain(): ProductModel = ProductModel(
    id = id,
    title = title,
    description = description,
    category = category,
    price = price,
    imageUrl = thumbnail.ifBlank { images.firstOrNull().orEmpty() }
)