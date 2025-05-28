package com.example.navigation3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay

class MainActivity : ComponentActivity() {
    sealed class Screen {
        data object First : Screen()

        data class Second(
            val id: String,
        ) : Screen()

        data object Unknown : Screen()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val backStack = remember { mutableStateListOf<Screen>(Screen.First) }
            NavDisplay(
                backStack = backStack,
                onBack = { backStack.removeLastOrNull() },
                entryProvider = { key ->
                    when (key) {
                        is Screen.First -> {
                            NavEntry(key) {
                                FirstScreen(
                                    onNavigateToProduct = {
                                        backStack.add(Screen.Second("123"))
                                    },
                                    onBack = {
                                        backStack.removeLastOrNull()
                                    },
                                )
                            }
                        }

                        is Screen.Second -> {
                            NavEntry(key) {
                                SecondScreen(
                                    id = key.id,
                                    onNavigateUnknown = {
                                        backStack.add(Screen.Unknown)
                                    },
                                    onBack = {
                                        backStack.removeLastOrNull()
                                    },
                                )
                            }
                        }

                        is Screen.Unknown -> {
                            NavEntry(key) {
                                UnknownScreen(
                                    onReset = {
                                        backStack.clear()
                                        backStack.add(Screen.First)
                                    },
                                )
                            }
                        }
                    }
                },
            )
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FirstScreen(
    modifier: Modifier = Modifier,
    onNavigateToProduct: () -> Unit = {},
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
                Text(text = "FIRST")
            },
        )
        Button(
            onClick = onNavigateToProduct,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "NAVIGATE TO SECOND")
        }
        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "BACK")
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SecondScreen(
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

@Suppress("ktlint:standard:function-naming")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UnknownScreen(
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
