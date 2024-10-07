package com.mehmetbaloglu.jettrivia.retrofit

import com.mehmetbaloglu.jettrivia.data.model.Question
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface QuestionsApi {

    //to get all questions
    @GET("world.json")
    suspend fun getAllQuestions(): Question


}