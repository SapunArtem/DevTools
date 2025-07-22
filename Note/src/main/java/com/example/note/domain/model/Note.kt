package com.example.note.domain.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


/**
 * Доменная модель заметки, используемая в бизнес-логике приложения.
 *
 * @property id Уникальный идентификатор заметки. Может быть `null` для новых заметок.
 * @property title Заголовок заметки.
 * @property content Текстовое содержимое заметки.
 * @property createdAt Время создания заметки в формате миллисекунд.
 */
data class Note(
    val id: Long? = null,
    val title: String,
    val content: String,
    val createdAt: Long = System.currentTimeMillis()
) {
    /**
     * Возвращает отформатированную дату создания заметки.
     * @return строка в формате "dd MMM yyyy, HH:mm".
     */
    fun getFormatDate(): String {
        val dateFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
        return dateFormat.format(Date(createdAt))
    }
}
