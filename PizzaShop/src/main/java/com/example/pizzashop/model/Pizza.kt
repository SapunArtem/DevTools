package com.example.pizzashop.model

/**
 * Представляет элемент пиццы
 * @property id Уникальный идентификатор пиццы
 * @property name Название пиццы
 * @property price Стоимость пиццы
 * @property description Описание состава пиццы
 * @property image Ресурс изображения пиццы
 */
data class Pizza(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    val image: Int

)