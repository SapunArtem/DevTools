package com.example.movieapp.presentation.components.topBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movieapp.R

@Composable
fun TopBarTitle(){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(40.dp),
            painter = painterResource(id = R.drawable.movie_icon),
            contentDescription = "movie_icon"
        )
        Text(
            text = stringResource(id = R.string.app_name),
        )
    }
}