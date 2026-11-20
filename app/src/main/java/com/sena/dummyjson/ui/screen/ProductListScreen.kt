package com.sena.dummyjson.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sena.dummyjson.domain.model.ProductModel
import com.sena.dummyjson.ui.section.NoticesSection
import com.sena.dummyjson.ui.section.ProductListHeaderSection
import com.sena.dummyjson.ui.section.ProductListSection
import com.sena.dummyjson.ui.state.ProductUIState
import com.sena.dummyjson.ui.theme.HomeBackground

@Composable
fun ProductListScreen(
    uiState: ProductUIState,
    onRefreshClick: () -> Unit,
    onAddClick: () -> Unit,
    onProductClick: (ProductModel) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(HomeBackground)
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {
        ProductListHeaderSection(
            productCount = uiState.products.size,
            onRefreshClick = onRefreshClick,
            onAddClick = onAddClick
        )

        if (uiState.successMessage != null || uiState.errorMessage != null) {
            Spacer(modifier = Modifier.height(14.dp))
            NoticesSection(
                successMessage = uiState.successMessage,
                errorMessage = uiState.errorMessage
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        ProductListSection(
            products = uiState.products,
            isLoading = uiState.isLoading,
            onProductClick = onProductClick,
            modifier = Modifier.weight(1f)
        )
    }
}
