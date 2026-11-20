package com.sena.dummyjson.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sena.dummyjson.domain.model.ProductModel
import com.sena.dummyjson.ui.composable.ProductCardItem
import com.sena.dummyjson.ui.theme.PrimaryPurple
import com.sena.dummyjson.ui.theme.ProductColorBlue
import com.sena.dummyjson.ui.theme.ProductColorGreen
import com.sena.dummyjson.ui.theme.ProductColorLightBlue
import com.sena.dummyjson.ui.theme.ProductColorOrange
import com.sena.dummyjson.ui.theme.SubtitleText

@Composable
fun ProductListSection(
    products: List<ProductModel>,
    isLoading: Boolean,
    onProductClick: (ProductModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    val cardColors = listOf(
        ProductColorBlue,
        ProductColorGreen,
        ProductColorOrange,
        ProductColorLightBlue
    )

    when {
        isLoading && products.isEmpty() -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(color = PrimaryPurple)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Cargando productos...",
                        fontSize = 14.sp,
                        color = SubtitleText
                    )
                }
            }
        }

        products.isEmpty() -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No se encontraron productos.",
                    fontSize = 14.sp,
                    color = SubtitleText
                )
            }
        }

        else -> {
            LazyColumn(
                modifier = modifier.fillMaxWidth(),
                contentPadding = PaddingValues(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                itemsIndexed(
                    items = products,
                    key = { index, item -> "${item.id}_$index" }
                ) { index, product ->
                    ProductCardItem(
                        product = product,
                        accentColor = cardColors[index % cardColors.size],
                        onDetailClick = { onProductClick(product) }
                    )
                }
            }
        }
    }
}
