package com.adempolat.livescoreapp.utils

// data durumlarını temsil eden sınıf
sealed class DataState<out R> {
    data class Success<out T>(val successData: T) : DataState<T>()
    data class Error(val exception: Exception) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}