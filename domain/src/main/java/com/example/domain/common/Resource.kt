package com.example.domain.common

import com.example.domain.enum.Status

data class Resource<out T>(val status: Status, val data: T? = null, val error: Entity.Error? = null) {

    fun isLoading() = status == Status.LOADING

    /**
     * Will call [onSuccess] callback with nullable data [T] on [Status.SUCCESS]
     */
    infix fun onSuccess(onSuccess: (data: T?) -> Unit): Resource<T> {
        return when (status) {
            Status.SUCCESS -> {
                onSuccess(data)
                this
            }
            else -> this
        }
    }

    /**
     * Will call [onError] callback with [Error] on [Status.ERROR]
     */
    infix fun onError(onError: (error: Entity.Error) -> Unit): Resource<T> {
        return when (status) {
            Status.ERROR -> {
                error?.let(onError)
                this
            }
            else -> this
        }
    }
}

sealed class Entity {
    data class Error(val errorCode: Int, val errorMessage: String) : Entity()
}