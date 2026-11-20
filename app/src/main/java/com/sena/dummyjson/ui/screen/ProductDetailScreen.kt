package com.sena.dummyjson.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sena.dummyjson.domain.model.ProductModel
import com.sena.dummyjson.ui.composable.ActionButtonsRow
import com.sena.dummyjson.ui.composable.ErrorNoticeBadge
import com.sena.dummyjson.ui.section.ProductDetailSection
import com.sena.dummyjson.ui.section.TopBarHeaderSection
import com.sena.dummyjson.ui.theme.HomeBackground
import com.sena.dummyjson.ui.theme.PrimaryPurple

@Composable
fun ProductDetailScreen(
    product: ProductModel,
    isSaving: Boolean,
    errorMessage: String?,
    onBackClick: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(HomeBackground)
            .padding(horizontal = 24.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBarHeaderSection(
                title = "Detalle del Producto",
                onBackClick = onBackClick
            )

            ProductDetailSection(product = product)

            if (errorMessage != null) {
                Spacer(modifier = Modifier.height(16.dp))
                ErrorNoticeBadge(text = errorMessage)
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            if (isSaving) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    color = PrimaryPurple
                )
            }

            ActionButtonsRow(
                enabled = !isSaving,
                onEditClick = onEditClick,
                onDeleteClick = onDeleteClick
            )
        }
    }
}
