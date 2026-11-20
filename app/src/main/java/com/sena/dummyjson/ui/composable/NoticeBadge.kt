package com.sena.dummyjson.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sena.dummyjson.R
import com.sena.dummyjson.ui.theme.GreenCardBorder
import com.sena.dummyjson.ui.theme.GreenIconBackground
import com.sena.dummyjson.ui.theme.RedCancel
import com.sena.dummyjson.ui.theme.RedErrorBackground
import com.sena.dummyjson.ui.theme.RedErrorBorder
import com.sena.dummyjson.ui.theme.SuccessGreenText

@Composable
fun SuccessNoticeBadge(
    text: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = GreenIconBackground,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = GreenCardBorder,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_check),
            contentDescription = "Éxito",
            tint = SuccessGreenText,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = text,
            color = SuccessGreenText,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun ErrorNoticeBadge(
    text: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = RedErrorBackground,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = RedErrorBorder,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_info),
            contentDescription = "Error",
            tint = RedCancel,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = text,
            color = RedCancel,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
