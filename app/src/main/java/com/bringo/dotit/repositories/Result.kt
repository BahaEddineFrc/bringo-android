package com.bringo.dotit.repositories

sealed class Result<out T: Any> {   //like enum structures but with classes
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}