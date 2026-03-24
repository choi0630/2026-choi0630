package com.example.smarttasknotes.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.smarttasknotes.data.mock.MockDataFactory
import com.example.smarttasknotes.data.model.TaskNoteType

// TODO : Task/Note 구분하여 출력 로직 구현
@Composable
fun TaskNoteItem(modifier: Modifier = Modifier) {
    
}

// TODO : TaskNoteItem 미리보기 구현하기
@Preview
@Composable
private fun TaskNoteItemPreview() {
    val items = MockDataFactory.getDataList()

}
