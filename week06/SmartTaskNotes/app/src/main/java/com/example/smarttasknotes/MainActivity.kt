package com.example.smarttasknotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.smarttasknotes.navexample.screens.MainScreen
import com.example.smarttasknotes.ui.screens.Week06HomeScreenA
import com.example.smarttasknotes.ui.theme.SmartTaskNotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  enableEdgeToEdge()
        setContent {
            SmartTaskNotesTheme {
                MainScreen()

                //Week06HomeScreenA()
            }
        }
    }
}
