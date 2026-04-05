package com.example.smarttasknotes.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smarttasknotes.util.HomeTab

@Composable
fun SingleChoiceButton(
    selectedHomeTab: HomeTab,
    onSelectedChange: (HomeTab) -> Unit,
    modifier: Modifier = Modifier
) {
    val options = listOf(HomeTab.TASK, HomeTab.NOTE)

    SingleChoiceSegmentedButtonRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        options.forEachIndexed { index, itemType ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                onClick = {
                    onSelectedChange(itemType)
                },
                selected = selectedHomeTab == itemType,
                label = {
                    Text(
                        text = when (itemType) {
                            HomeTab.TASK -> "Task"
                            HomeTab.NOTE -> "Note"
                        }
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun SingleChoiceButtonPreview() {
    var selectedHomeTab by remember { mutableStateOf(HomeTab.TASK) }

    SingleChoiceButton(
        selectedHomeTab = selectedHomeTab,
        onSelectedChange = { selectedHomeTab = it }
    )
}
