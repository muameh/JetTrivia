package com.mehmetbaloglu.jettrivia.ui.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.mehmetbaloglu.jettrivia.components.Questions
import com.mehmetbaloglu.jettrivia.ui.viewmodels.QuestionsViewModel

//hiltViewModel() fonksiyonu, Hilt'in ViewModel'inizi oluşturmasını ve yönetmesini sağlar.
//Bu fonksiyonu çağırdığınızda, Hilt uygun ViewModel'i otomatik olarak sağlar ve yaşam döngüsünü yönetir.
//hiltViewModel() fonksiyonunun arka planda yaptığı şey;
//Hilt tarafından sağlanan bir ViewModel Factory kullanarak ilgili ViewModel'in örneğini oluşturmak ve bu örneği Composable fonksiyonunuza geçirmektir.


@Composable
fun TriviaHome(viewModel: QuestionsViewModel = hiltViewModel()) {
    Questions(viewModel)
}

