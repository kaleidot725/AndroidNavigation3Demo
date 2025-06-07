package com.example.navigation3.ui.screen.two

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator

private sealed class Key {
    data object First : Key()

    data class Second(
        val id: String,
    ) : Key()

    data object Unknown : Key()
}

@Composable
fun RootScreen() {
    val backStack = remember { mutableStateListOf<Key>(Key.First) }
    NavDisplay(
        entryDecorators =
            listOf(
                rememberSceneSetupNavEntryDecorator(),
                rememberSavedStateNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
        backStack = backStack,
        entryProvider =
            entryProvider {
                entry<Key.First> {
                    FirstScreen(
                        onNavigateToProduct = {
                            backStack.add(Key.Second("123"))
                        },
                        onBack = {
                            backStack.removeLastOrNull()
                        },
                    )
                }
                entry<Key.Second> {
                    SecondScreen(
                        id = it.id,
                        onNavigateUnknown = {
                            backStack.add(Key.Unknown)
                        },
                        onBack = {
                            backStack.removeLastOrNull()
                        },
                    )
                }
                entry<Key.Unknown> {
                    UnknownScreen(
                        onReset = {
                            backStack.clear()
                            backStack.add(Key.First)
                        },
                    )
                }
            },
    )
}
