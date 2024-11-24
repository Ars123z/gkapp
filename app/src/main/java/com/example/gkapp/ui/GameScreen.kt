package com.example.gkapp.ui

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.TextButton
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gkapp.R
import com.example.gkapp.data.Question



@Composable
fun GameScreen(gameViewModel: GameViewModel = viewModel()) {
    val gameUiState by gameViewModel.uiState.collectAsState()
    val mediumPadding = 16.dp

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding()
            .padding(mediumPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = typography.displayLarge,
        )
        Spacer(
            modifier = Modifier
                .padding(bottom = 50.dp)
        )
        GameLayout(
            currentQuestion = gameUiState.currentQuestion,
            currentQuestionCount = gameUiState.currentQuestionCount,
            onClick = {
                gameViewModel.updateUserAnswer(it)
                gameViewModel.checkUserAnswer()
            }
        )
    Spacer(
        modifier = Modifier
            .padding(mediumPadding)
    )
Card(
    modifier = Modifier,
) {
   Text(
       text = stringResource(R.string.score, gameUiState.score),
       style = typography.headlineMedium,
       color = colorScheme.onBackground,
       modifier = Modifier.padding(8.dp)
   )
}

        if (gameUiState.isGameOver) {
            FinalScoreDialog(
                score = gameUiState.score,
                onPlayAgain = { gameViewModel.resetGame() }
            )
        }
    }
}

@Composable
fun GameLayout(
    currentQuestion: Question,
    currentQuestionCount: Int,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val mediumPadding = 16.dp
   Card(
       modifier = modifier,
       elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
   ) {
       Column(
           verticalArrangement = Arrangement.spacedBy(mediumPadding),
           horizontalAlignment = Alignment.CenterHorizontally,
           modifier = Modifier.padding(mediumPadding)
       ) {
          Text(
              modifier = Modifier
                  .clip(shapes.medium)
                  .background(colorScheme.surfaceTint)
                  .align(alignment = Alignment.End),
              text = stringResource(R.string.question_count, currentQuestionCount),
              style = typography.titleMedium,
              color = colorScheme.onPrimary,
              textAlign = TextAlign.End
          )
           Text(
               text = currentQuestion.body,
               style = typography.headlineMedium,
           )
           Text(
               text = "Only one of the options is correct",
               style = typography.titleMedium,
               textAlign = TextAlign.Center,
           )
           Column(
               modifier = Modifier.fillMaxWidth(),
               verticalArrangement = Arrangement.spacedBy(mediumPadding),
               horizontalAlignment = Alignment.Start
           ) {
               TextButton(
                   onClick = { onClick(currentQuestion.options[0]) },
                   modifier = Modifier
                       .padding(horizontal = mediumPadding),
                   colors = ButtonDefaults.buttonColors(
                       containerColor = colorScheme.tertiary
                   )
               ) {
                   Text(
                       modifier = Modifier
                           .width(150.dp),
                       text = currentQuestion.options[0],
                       style = typography.titleLarge,
                   )
               }
               TextButton(
                   onClick = { onClick(currentQuestion.options[1]) },
                   modifier = Modifier
                       .padding(horizontal = mediumPadding),
                   colors = ButtonDefaults.buttonColors(
                       containerColor = colorScheme.tertiary
                   )
               ) {
                   Text(
                       modifier = Modifier
                           .width(150.dp),
                       text = currentQuestion.options[1],
                       style = typography.titleLarge,
                   )
               }
               TextButton(
                   onClick = { onClick(currentQuestion.options[2]) },
                   modifier = Modifier
                       .padding(horizontal = mediumPadding),
                   colors = ButtonDefaults.buttonColors(
                       containerColor = colorScheme.tertiary
                   )
               ) {
                   Text(
                       modifier = Modifier
                           .width(150.dp),
                       text = currentQuestion.options[2],
                       style = typography.titleLarge,
                   )
               }
           }
       }
   }
}

@Composable
private fun FinalScoreDialog(
    score: Int,
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
        },
        title = { Text(text = "Congratulations") },
        text = { Text(text = "You scored $score points") },
        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text(text = "Exit")
            }
        },
        confirmButton = {
            TextButton(onClick = onPlayAgain) {
                Text(text = "Play Again")
            }
        }
    )
}