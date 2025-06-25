package com.example.pizzashop.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

private val BaseTextStyle = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Bold,
    fontSize = 25.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp,
    color = Color.White
)

val TitleAppBar = BaseTextStyle.copy(

    )

val BasketAppBar =  BaseTextStyle.copy(
    fontSize = 20.sp,

)
val PizzaName =  BaseTextStyle.copy(
    color = Orange
)
val TextDescription =  BaseTextStyle.copy(
    fontWeight = FontWeight.SemiBold,
    fontSize = 20.sp,
    color = Color.Black
)
val PizzaDescription =  BaseTextStyle.copy(
    fontWeight = FontWeight.Normal,
    fontSize = 15.sp,
    color = Color.Black
)
val PizzaPrice =  BaseTextStyle.copy(
    fontWeight = FontWeight.SemiBold,
    fontSize = 20.sp,
    color = Color.Black
)

