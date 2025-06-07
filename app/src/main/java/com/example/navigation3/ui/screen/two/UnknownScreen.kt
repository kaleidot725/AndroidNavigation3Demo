package com.example.navigation3.ui.screen.two

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
fun UnknownScreen(
    modifier: Modifier = Modifier,
    onReset: () -> Unit,
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
                Text(text = "UNKNOWN")
            },
        )
        Button(
            onClick = onReset,
            modifier = Modifier.fillMaxWidth(),
        ) { Text(text = "Reset") }
    }
}
