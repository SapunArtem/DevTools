package com.example.newsapp.data.api.models


/**
 * Модель данных новостного источника.
 *
 * @property id Уникальный идентификатор источника
 * @property name Название источника
 * @property url URL веб-сайта источника
 * @property icon URL иконки источника
 * @property priority Приоритет источника (для сортировки)
 * @property description Описание источника
 * @property category Список категорий источника
 * @property language Список поддерживаемых языков
 * @property country Список стран, которые охватывает источник
 * @property total_article Общее количество статей от этого источника
 * @property last_fetch Дата последнего обновления данных (может быть null)
 */
data class NewsItemDto(
    val id: String,
    val name: String,
    val url: String,
    val icon: String?,
    val priority: Int,
    val description: String,
    val category: List<String>,
    val language: List<String>,
    val country: List<String>,
    val total_article: Int,
    val last_fetch: String?

)
