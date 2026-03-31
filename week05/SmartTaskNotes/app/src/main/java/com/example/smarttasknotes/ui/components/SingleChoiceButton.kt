package com.example.smarttasknotes.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.smarttasknotes.util.HomeTab

@Composable
fun SingleChoiceButton(
    modifier: Modifier = Modifier
) {
    val options = listOf(HomeTab.TASK, HomeTab.NOTE)

//    SingleChoiceSegmentedButtonRow(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 16.dp)
//    ) {
//        options.forEachIndexed { index, itemType ->
//            SegmentedButton(
//                shape = SegmentedButtonDefaults.itemShape(
//                    index = index,
//                    count = options.size
//                ),
//                selected = TODO(),
//                onClick = TODO(),
//                label = {
//                    Text(
//                        text = when (itemType) {
//                            HomeTab.TASK -> "Task"
//                            HomeTab.NOTE -> "Note"
//                        }
//                    )
//                }
//            )
//        }
//    }
}

@Preview
@Composable
private fun SingleChoiceButtonPreview() {

}
