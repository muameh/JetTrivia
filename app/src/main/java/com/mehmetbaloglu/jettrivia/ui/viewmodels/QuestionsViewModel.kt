package com.mehmetbaloglu.jettrivia.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehmetbaloglu.jettrivia.data.model.DataOrException
import com.mehmetbaloglu.jettrivia.data.model.QuestionItem
import com.mehmetbaloglu.jettrivia.data.repository.QuestionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(private val repository: QuestionsRepository) :
    ViewModel() {

    val dataInViewModel: MutableState<DataOrException<ArrayList<QuestionItem>, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))

    init {
        getAllQuestions()
    }

    private fun getAllQuestions() {
        viewModelScope.launch {
            dataInViewModel.value.loading = true
            dataInViewModel.value = repository.getAllQuestions()
            if (dataInViewModel.value.data.toString().isNotEmpty()) {
                dataInViewModel.value.loading = false
            }
        }
    }

}