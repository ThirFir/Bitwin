package com.strone.presentation.ui.component.chip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.strone.presentation.ui.theme.Typography

@Composable
fun ChipRow(
    modifier: Modifier = Modifier,
    chipStates: List<ChipState>,
    onChipClicked: (Int) -> Unit,
    chipColors: FilledChipColors = FilledChipColors(),
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        items(chipStates.size) { index ->
            Chip(
                text = chipStates[index].text,
                selected = chipStates[index].selected,
                chipTextStyle = TextStyle.Default.copy(
                    fontStyle = Typography.labelMedium.fontStyle
                ),
                onChipClicked = { onChipClicked(index) },
                chipIndex = index,
                chipColors = chipColors
            )
        }
    }
}

@Stable
data class ChipState(
    var text: String,
    var selected: Boolean,
)