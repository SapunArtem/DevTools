package com.example.newsapp.presentation.components.error

import com.example.newsapp.R

/**
 * Возвращает ID строкового ресурса для отображения сообщения об ошибке,
 * основываясь на тексте исключения.
 *
 * @param exception Текст ошибки или исключения.
 * @return ID ресурса строки с описанием ошибки.
 */
fun errorException(exception: String?): Int {

    return when {
        exception == null -> R.string.e_unknown
        exception.contains("400") ->
            R.string.e_400

        exception.contains("401") ->
            R.string.e_401

        exception.contains("403") ->
            R.string.e_403

        exception.contains("409") ->
            R.string.e_409

        exception.contains("415") ->
            R.string.e_415

        exception.contains("422") ->
            R.string.e_422

        exception.contains("429") ->
            R.string.e_429

        exception.contains("500") ->
            R.string.e_500

        exception.contains("network", ignoreCase = true) ->
            R.string.e_nework

        else -> R.string.e_unknown
    }
}