package com.example.movieapp.presentation.components.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.presentation.ui.theme.GrayUnselected
import com.example.movieapp.presentation.ui.theme.Orange
import com.example.movieapp.presentation.ui.theme.White


@Composable
fun Rating(
    name: String,
    rating: Double?,
    count: Int?
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Orange)
                .padding(8.dp)
        ) {
            Text(
                text = "$name: ${rating ?: "-"}",
                color = White
            )
        }
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = "(${count ?: 0} ${stringResource(R.string.reviews)})",
            color = GrayUnselected
        )

    }
    Spacer(modifier = Modifier.height(8.dp))
}