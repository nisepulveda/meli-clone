package com.nicolas.clonmeli.domain

sealed class MeliResult<out T : Any>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<out T : Any>(data: T): MeliResult<T>(data)
    class Error(message: String? = String(), val code: Int? = null): MeliResult<Nothing>()
}