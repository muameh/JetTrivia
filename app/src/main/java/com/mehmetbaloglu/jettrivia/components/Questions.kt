package com.mehmetbaloglu.jettrivia.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mehmetbaloglu.jettrivia.data.model.QuestionItem
import com.mehmetbaloglu.jettrivia.ui.viewmodels.QuestionsViewModel
import com.mehmetbaloglu.jettrivia.util.AppColors


@Composable
fun Questions(viewModel: QuestionsViewModel) {
    val questions = viewModel.dataInViewModel.value.data?.toMutableList()

    if (viewModel.dataInViewModel.value.loading == true) {
        CircularProgressIndicator()

    } else {
        if (questions != null) {
            QuestionDisplay(question = questions.first())
        }

    }
}

@Composable
fun QuestionDisplay(
    question: QuestionItem,
    //questionIndex: MutableState<Int>,
    //viewModel: QuestionsViewModel,
    onNextClicked: (Int) -> Unit = {}
) {
    val choicesState = remember(question) {
        question.choices?.toMutableList()
    }

    val answerState = remember(question) {
        mutableStateOf<Int?>(null)
    }

    val correctAnswerState = remember(question) {
        mutableStateOf<Boolean?>(null)
    }

    val updateAnswer: (Int) -> Unit = remember(question) {
        {
            answerState.value = it
            correctAnswerState.value = choicesState?.get(it) == question.answer
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp), color = AppColors.Light_Blue
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = androidx.compose.ui.Alignment.Start
        ) {
            QuestionTracker()
            HorizontalDivider(
                modifier = Modifier.padding(8.dp), color = AppColors.DarkBlue, thickness = 1.dp
            )
            Column() {
                question.question?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .padding(6.dp)
                            .align(androidx.compose.ui.Alignment.Start)
                            .fillMaxHeight(0.3f),
                        fontSize = 17.sp,
                        color = AppColors._TextDark,
                        fontWeight = FontWeight.Bold
                    )
                }
                choicesState?.forEachIndexed { index, answerText ->
                    Row(
                        modifier = Modifier
                            .padding(3.dp)
                            .fillMaxWidth()
                            .height(45.dp)
                            .border(
                                width = 4.dp, brush = Brush.linearGradient(
                                    colors = listOf(AppColors._Purple, AppColors._Orange)
                                ), shape = RoundedCornerShape(15.dp)
                            )
                            .clip(
                                RoundedCornerShape(
                                    topStartPercent = 50,
                                    topEndPercent = 50,
                                    bottomStartPercent = 50,
                                    bottomEndPercent = 50
                                )
                            )
                            .background(Color.Transparent)
                    ) {
                        RadioButton(
                            selected = (answerState.value == index),
                            onClick = { updateAnswer(index) },
                            modifier = Modifier.padding(start = 16.dp),
                            colors = RadioButtonDefaults.colors(
                                selectedColor = if (correctAnswerState.value == true && index == answerState.value) {
                                    Color.Green.copy(alpha = 0.2f)
                                } else {
                                    Color.Red.copy(alpha = 0.2f)
                                }
                            )
                        )
                        if (answerText != null) {
                            Text(text = answerText, modifier = Modifier.padding(6.dp))
                        }

                    }
                }


            }

        }


    }
}


@Composable
fun QuestionTracker(
    counter: Int = 10, outOf: Int = 100
) {
    Text(modifier = Modifier.padding(20.dp), text = buildAnnotatedString {
        withStyle(
            style = ParagraphStyle(
                textIndent = TextIndent.None
            )
        ) {
            withStyle(
                style = SpanStyle(
                    color = AppColors.DarkBlue,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    fontSize = 30.sp
                )
            ) {
                append("Question $counter/")
                withStyle(
                    style = SpanStyle(
                        color = AppColors.Indigo,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Light,
                        fontSize = 18.sp
                    )
                ) {
                    append("$outOf")
                }
            }
        }
    })
}

