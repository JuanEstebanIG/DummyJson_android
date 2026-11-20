package com.sena.dummyjson.data.remote.dto.product

import com.squareup.moshi.Json

data class PatchProduct(
    @param:Json(name = "category")
    val category: String,
)
