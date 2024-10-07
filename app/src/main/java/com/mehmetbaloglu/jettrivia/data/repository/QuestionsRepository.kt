package com.mehmetbaloglu.jettrivia.data.repository

import android.util.Log
import com.mehmetbaloglu.jettrivia.data.DataOrException
import com.mehmetbaloglu.jettrivia.data.model.QuestionItem
import com.mehmetbaloglu.jettrivia.retrofit.QuestionsApi
import javax.inject.Inject

class QuestionsRepository @Inject constructor(private val api: QuestionsApi) {
    private val dataOrException = DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()


    suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = api.getAllQuestions()
            if (dataOrException.data.toString().isNotEmpty()) {
                dataOrException.loading = false
            }

        } catch (exception: Exception) {
            dataOrException.e = exception
            Log.d("Exc", "getAllQuestions: ${dataOrException.e!!.localizedMessage}")
        }
        return dataOrException
    }
}