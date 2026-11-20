package com.sena.dummyjson.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.sena.dummyjson.ui.screen.ProductDetailScreen
import com.sena.dummyjson.ui.screen.ProductFormScreen
import com.sena.dummyjson.ui.screen.ProductListScreen
import com.sena.dummyjson.viewModel.ProductViewModel

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsState()
    var pendingMutation by remember { mutableStateOf(false) }

    LaunchedEffect(uiState.isSaving, uiState.errorMessage) {
        if (pendingMutation && !uiState.isSaving) {
            pendingMutation = false
            if (uiState.errorMessage == null) {
                navController.popBackStack(ProductList, inclusive = false)
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = ProductList,
        modifier = modifier
    ) {
        composable<ProductList> {
            ProductListScreen(
                uiState = uiState,
                onRefreshClick = {
                    viewModel.loadProducts()
                },
                onAddClick = {
                    navController.navigate(ProductForm(productId = null))
                },
                onProductClick = { product ->
                    viewModel.selectProduct(product)
                    navController.navigate(ProductDetail(productId = product.id))
                }
            )
        }

        composable<ProductDetail> { backStackEntry ->
            val args = backStackEntry.toRoute<ProductDetail>()
            val product = uiState.products.find { it.id == args.productId } ?: uiState.selectedProduct

            if (product != null) {
                ProductDetailScreen(
                    product = product,
                    isSaving = uiState.isSaving,
                    errorMessage = uiState.errorMessage,
                    onBackClick = {
                        viewModel.selectProduct(null)
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate(ProductForm(productId = product.id))
                    },
                    onDeleteClick = {
                        pendingMutation = true
                        viewModel.deleteProduct(product)
                    }
                )
            }
        }

        composable<ProductForm> { backStackEntry ->
            val args = backStackEntry.toRoute<ProductForm>()
            val initialProduct = args.productId?.let { id ->
                uiState.products.find { it.id == id } ?: uiState.selectedProduct?.takeIf { it.id == id }
            }

            ProductFormScreen(
                initialProduct = initialProduct,
                isSaving = uiState.isSaving,
                errorMessage = uiState.errorMessage,
                onBackClick = {
                    navController.popBackStack()
                },
                onSubmit = { product ->
                    pendingMutation = true
                    if (initialProduct == null) {
                        viewModel.createProduct(product)
                    } else {
                        viewModel.updateProduct(product)
                    }
                }
            )
        }
    }
}
