package com.example.smarttasknotes.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TextInput(
    value: String,
    label : String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            label = { Text(label) },
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        )
    }
}

@Preview
@Composable
private fun TaskInputLayoutPreview() {
    var taskValue by remember{mutableStateOf("")}
    var noteValue by remember{mutableStateOf("")}
    Column {
        TextInput(taskValue, "📅 마감일", {taskValue = it})
        TextInput(noteValue, "📝 내용", {noteValue = it})
    }

}
