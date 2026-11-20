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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sena.dummyjson.R
import com.sena.dummyjson.domain.model.ProductModel
import com.sena.dummyjson.ui.composable.ButtonAction
import com.sena.dummyjson.ui.composable.ErrorNoticeBadge
import com.sena.dummyjson.ui.section.ProductFormSection
import com.sena.dummyjson.ui.section.TopBarHeaderSection
import com.sena.dummyjson.ui.theme.HomeBackground
import com.sena.dummyjson.ui.theme.PrimaryPurple
import com.sena.dummyjson.ui.theme.SuccessGreen

@Composable
fun ProductFormScreen(
    initialProduct: ProductModel?,
    isSaving: Boolean,
    errorMessage: String?,
    onBackClick: () -> Unit,
    onSubmit: (ProductModel) -> Unit,
) {
    val isEditing = initialProduct != null
    var title by remember(initialProduct) { mutableStateOf(initialProduct?.title.orEmpty()) }
    var category by remember(initialProduct) { mutableStateOf(initialProduct?.category.orEmpty()) }
    var priceText by remember(initialProduct) {
        mutableStateOf(initialProduct?.price?.toString().orEmpty())
    }
    var description by remember(initialProduct) {
        mutableStateOf(initialProduct?.description.orEmpty())
    }
    var validationError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(HomeBackground)
            .padding(horizontal = 24.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            TopBarHeaderSection(
                title = if (isEditing) "Editar Producto" else "Nuevo Producto",
                onBackClick = onBackClick
            )

            ProductFormSection(
                title = title,
                category = category,
                price = priceText,
                description = description,
                onTitleChange = {
                    title = it
                    validationError = null
                },
                onCategoryChange = {
                    category = it
                    validationError = null
                },
                onPriceChange = {
                    priceText = it
                    validationError = null
                },
                onDescriptionChange = {
                    description = it
                    validationError = null
                }
            )

            val visibleError = validationError ?: errorMessage
            if (visibleError != null) {
                Spacer(modifier = Modifier.height(16.dp))
                ErrorNoticeBadge(text = visibleError)
            }
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            if (isSaving) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    color = PrimaryPurple
                )
            }

            ButtonAction(
                text = if (isEditing) "Guardar Cambios" else "Crear Producto",
                iconResId = R.drawable.ic_check,
                backgroundColor = if (isEditing) SuccessGreen else PrimaryPurple,
                enabled = !isSaving,
                onClick = {
                    val parsedPrice = priceText.trim().toDoubleOrNull()
                    when {
                        title.isBlank() || category.isBlank() || description.isBlank() -> {
                            validationError = "Completa todos los campos del producto."
                        }

                        parsedPrice == null || parsedPrice < 0.0 -> {
                            validationError = "Ingresa un precio numérico válido."
                        }

                        else -> {
                            onSubmit(
                                ProductModel(
                                    id = initialProduct?.id ?: 0,
                                    title = title.trim(),
                                    description = description.trim(),
                                    category = category.trim(),
                                    price = parsedPrice,
                                    imageUrl = initialProduct?.imageUrl.orEmpty()
                                )
                            )
                        }
                    }
                }
            )
        }
    }
}
