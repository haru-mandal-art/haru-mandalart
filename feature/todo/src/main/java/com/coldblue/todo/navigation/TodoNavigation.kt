package com.coldblue.todo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.coldblue.data.navires.todoRoute
import com.coldblue.todo.TodoScreen

fun NavController.navigateToTodo(navOptions: NavOptions) {
    this.navigate(todoRoute, navOptions)
}

fun NavGraphBuilder.todoScreen(navigateToTutorial: () -> Unit, navigateToHistory: () -> Unit) {
    composable(route = todoRoute) {
        TodoScreen(
            navigateToTutorial = { navigateToTutorial() },
            navigateToHistory = { navigateToHistory() })
    }
}