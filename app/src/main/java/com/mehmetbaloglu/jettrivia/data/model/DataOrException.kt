package com.mehmetbaloglu.jettrivia.data.model

data class DataOrException<T, Boolean, Exception>(
    var data: T? = null,
    var loading: Boolean? = null,
    var e: Exception? = null
)