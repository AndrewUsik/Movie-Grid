package com.example.moviegrid.domain

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception? = null, val code: Int = 0) : Result<Nothing>()
}