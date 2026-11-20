package com.sena.dummyjson.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sena.dummyjson.R
import com.sena.dummyjson.domain.model.ProductModel
import com.sena.dummyjson.ui.composable.ProductCover
import com.sena.dummyjson.ui.composable.ProductDetailRow
import com.sena.dummyjson.ui.composable.StatusBadge
import java.util.Locale

@Composable
fun ProductDetailSection(
    product: ProductModel,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StatusBadge(
            text = "Producto #${product.id}",
            iconResId = R.drawable.ic_store
        )

        Spacer(modifier = Modifier.height(24.dp))

        ProductCover(
            productTitle = product.title,
            category = product.category,
            price = product.price,
            imageUrl = product.imageUrl
        )

        Spacer(modifier = Modifier.height(28.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ProductDetailRow(label = "Título:", value = product.title)
            ProductDetailRow(label = "Categoría:", value = product.category)
            ProductDetailRow(
                label = "Precio:",
                value = "$${String.format(Locale.US, "%.2f", product.price)}"
            )
            ProductDetailRow(label = "Descripción:", value = product.description)
            if (product.imageUrl.isNotBlank()) {
                ProductDetailRow(label = "Imagen:", value = product.imageUrl)
            }
        }
    }
}
