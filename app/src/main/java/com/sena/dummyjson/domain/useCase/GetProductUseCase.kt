package com.sena.dummyjson.domain.useCase

import com.sena.dummyjson.domain.model.ProductModel
import com.sena.dummyjson.domain.repository.ProductRepository
import jakarta.inject.Inject

class GetProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend fun invouke(): List<ProductModel> = repository.getProducts()
    suspend fun byId(id: Int) : ProductModel = repository.getProductById(id)
}