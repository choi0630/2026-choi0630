package com.example.smarttasknotes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smarttasknotes.ui.screens.Week06AddScreen
import com.example.smarttasknotes.ui.screens.Week06HomeScreenB
import com.example.smarttasknotes.viewmodel.TaskNoteViewModelB
import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@Serializable
object AddScreen


@Composable
fun TaskNoteAppNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeScreen
    ) {
        composable<HomeScreen> {
            val vm: TaskNoteViewModelB = viewModel()
            Week06HomeScreenB(
                homeUiState = vm.homeUiState,
                homeUiActions = vm.homeUiActions
            )
        }

        composable<AddScreen> { backStackEntry ->

            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry<HomeScreen>()
            }
            val vm: TaskNoteViewModelB = viewModel(parentEntry)
            Week06AddScreen(
                state = vm.addUiState,
                actions = vm.addUiActions
            )
        }
    }
}