package com.rosario.covid19.util

data class Resource<out T>(val status: Status, val data: T?, val error: Throwable?) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(status = Status.SUCCESS, data = data, error = null)

        fun <T> error(data: T?, error: Throwable): Resource<T> =
            Resource(status = Status.ERROR, data = data, error = error)

        fun <T> loading(data: T?): Resource<T> = Resource(status = Status.LOADING, data = data, error = null)
    }
}