package com.mehmetbaloglu.jettrivia.data.model


import com.google.gson.annotations.SerializedName

data class QuestionItem(
    val answer: String?,
    val category: String?,
    val choices: List<String?>?,
    val question: String?
)