package com.example.smarttasknotes.navexample.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.smarttasknotes.navexample.screens.HomeScreen
import com.example.smarttasknotes.navexample.screens.ScreenA
import com.example.smarttasknotes.navexample.screens.ScreenB
import com.example.smarttasknotes.navexample.screens.ScreenC
import com.example.smarttasknotes.navexample.screens.ScreenD
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object A

@Serializable
object B

@Serializable
object C

@Serializable
object D

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            HomeScreen(
                onNavigateA = { navController.navigate(route = A) },
                onNavigateB = { navController.navigate(route = B) }
            )
        }
        composable<A> {
            ScreenA(
                onNavigateC = { navController.navigate(route = C) },
                onNavigateD = { navController.navigate(route = D) },
            )
        }
        composable<B> {
            ScreenB()
        }
        composable<C> {
            ScreenC(onNavigate = { navController.navigate(route = Home) })
        }
        composable<D> {
            ScreenD (onNavigate = { navController.navigate(route = Home) })
        }
    }
}