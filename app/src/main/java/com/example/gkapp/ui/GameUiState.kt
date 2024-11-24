package com.example.gkapp.ui

import com.example.gkapp.data.Question
import com.example.gkapp.data.questions

const val MAX_NO_OF_QUESTIONS = 5
const val SCORE_INCREASE = 20


data class GameUiState(
    val currentQuestion: Question = questions[0],
    val currentQuestionCount: Int = 1,
    val score: Int = 0,
    val isGameOver: Boolean = false
)