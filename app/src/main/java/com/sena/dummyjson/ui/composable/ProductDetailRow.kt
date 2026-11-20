package com.sena.dummyjson.ui.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sena.dummyjson.ui.theme.SubtitleText
import com.sena.dummyjson.ui.theme.TitleText

@Composable
fun ProductDetailRow(
    label: String,
    value: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            color = TitleText,
            fontSize = 14.sp,
            modifier = Modifier.width(96.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = value,
            color = SubtitleText,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )
    }
}
