package com.example.gkapp.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.gkapp.data.Question
import com.example.gkapp.data.questions
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel: ViewModel() {

//    Game UI State
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private var userAnswer by mutableStateOf("")

//    Set of Questions used in the game
    private var usedQuestions: MutableSet<Question> = mutableSetOf()
    private lateinit var currentQuestion: Question

    init {
        resetGame()
    }
/*
 * Re-initializes the game data to restart the game.
 */

    fun resetGame() {
        usedQuestions.clear()
        _uiState.value = GameUiState(currentQuestion = pickNewQuestion())
    }

    fun updateUserAnswer(guessedAnswer: String) {
        userAnswer = guessedAnswer
    }

    fun checkUserAnswer() {
        if (currentQuestion.answer.equals(userAnswer)) {
            val updatedScore = _uiState.value.score.plus(SCORE_INCREASE)
            updateGameState(updatedScore)
        } else {
            updateGameState(_uiState.value.score)
        }
    }

    fun updateGameState(updatedScore: Int) {
        if (usedQuestions.size == MAX_NO_OF_QUESTIONS) {
// Last round in the game, update isGameOver to true, don't pick a new question
            _uiState.value = GameUiState(
                score = updatedScore,
                isGameOver = true
            )
        } else {
            _uiState.value = GameUiState(
                currentQuestion = pickNewQuestion(),
                currentQuestionCount = _uiState.value.currentQuestionCount.inc(),
                score = updatedScore
            )
        }
    }

    private fun pickNewQuestion(): Question {
        currentQuestion = questions.random()
        if (usedQuestions.contains(currentQuestion)) {
            return pickNewQuestion()
        } else {
            usedQuestions.add(currentQuestion)
            return currentQuestion
        }
    }

}