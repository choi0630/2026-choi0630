package com.example.smarttasknotes.navexample.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ScreenC(onNavigate:()->Unit) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text="Screen C",
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Button(onClick = {
            onNavigate()
        }){
            Text(text = "Go to Home Screen")
        }
    }
}

