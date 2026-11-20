package com.sena.dummyjson.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sena.dummyjson.ui.composable.ErrorNoticeBadge
import com.sena.dummyjson.ui.composable.SuccessNoticeBadge

@Composable
fun NoticesSection(
    successMessage: String?,
    errorMessage: String?,
) {
    if (successMessage != null || errorMessage != null) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (successMessage != null) {
                SuccessNoticeBadge(text = successMessage)
            }
            if (errorMessage != null) {
                ErrorNoticeBadge(text = errorMessage)
            }
        }
    }
}
