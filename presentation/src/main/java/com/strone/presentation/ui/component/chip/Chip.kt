package com.strone.presentation.ui.component.chip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.strone.presentation.ui.theme.ColorBackgroundGray
import com.strone.presentation.ui.theme.ColorGray
import com.strone.presentation.ui.theme.ColorOnPrimary
import com.strone.presentation.ui.theme.ColorTertiaryContainer
import com.strone.presentation.util.clickable

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    chipTextStyle: TextStyle = TextStyle.Default,
    chipColors: FilledChipColors = FilledChipColors(),
    onChipClicked: (Int) -> Unit,
    chipIndex: Int
) {
    Surface(
        color = when {
            selected -> chipColors.selectedContainerColor
            else -> chipColors.unselectedContainerColor
        },
        shape = RoundedCornerShape(100.dp),
        modifier = modifier.clickable(showRipple = false) { onChipClicked(chipIndex) }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                color = when {
                    selected -> chipColors.selectedTextColor
                    else -> chipColors.unselectedTextColor
                },
                style = chipTextStyle,
            )
        }
    }
}

class FilledChipColors(
    val selectedContainerColor: Color = ColorTertiaryContainer,
    val selectedTextColor: Color = ColorOnPrimary,
    val unselectedContainerColor: Color = ColorBackgroundGray,
    val unselectedTextColor: Color = ColorGray,
)