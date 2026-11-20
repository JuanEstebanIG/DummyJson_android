package com.sena.dummyjson.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sena.dummyjson.R
import com.sena.dummyjson.ui.theme.ButtonOrange
import com.sena.dummyjson.ui.theme.CardWhite
import com.sena.dummyjson.ui.theme.ProductCoverBackground
import java.util.Locale

@Composable
fun ProductCover(
    productTitle: String,
    category: String,
    price: Double,
    imageUrl: String = "",
) {
    if (imageUrl.isNotBlank()) {
        Card(
            modifier = Modifier
                .width(180.dp)
                .height(190.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = CardWhite),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = productTitle,
                contentScale = ContentScale.Fit,
                placeholder = painterResource(id = R.drawable.ic_store),
                error = painterResource(id = R.drawable.ic_store),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            )
        }
    } else {
        Box(
            modifier = Modifier
                .width(160.dp)
                .height(190.dp)
                .background(
                    color = ProductCoverBackground,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(14.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = productTitle,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = category,
                    color = Color.LightGray,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "$${String.format(Locale.US, "%.2f", price)}",
                    color = ButtonOrange,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
