package com.example.navigation3.ui.screen.one

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(
    modifier: Modifier = Modifier,
    id: String,
    onNavigateUnknown: () -> Unit = {},
    onBack: () -> Unit = {},
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .statusBarsPadding(),
    ) {
        TopAppBar(
            title = {
                Text(text = "SECOND : $id")
            },
        )
        Button(
            onClick = onNavigateUnknown,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "NAVIGATE TO UNKNOWN")
        }

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "BACK")
        }
    }
}
