package com.mehmetbaloglu.jettrivia

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mehmetbaloglu.jettrivia.ui.theme.JetTriviaTheme
import com.mehmetbaloglu.jettrivia.ui.viewmodels.QuestionsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetTriviaTheme {
                TriviaHome(viewModel = viewModel())
            }
        }
    }
}


@Composable
fun TriviaHome(viewModel: QuestionsViewModel) {
    Questions(viewModel)
}

@Composable
fun Questions(viewModel: QuestionsViewModel) {
    val questions = viewModel.dataInViewModel.value.data?.toMutableList()

    if (viewModel.dataInViewModel.value.loading == true) {
        Log.d("Loading", "Questions: Loading...")
    } else {
        Log.d("Loading", "Questions: Loaded")
        questions?.forEachIndexed { index, it ->
            Log.d("Question", "Question ${index + 1} : ${it.question}")
        }
    }
}


