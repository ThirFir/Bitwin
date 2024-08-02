package com.strone.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.strone.presentation.ui.theme.ColorBackgroundGray
import com.strone.presentation.ui.theme.Typography
import com.strone.presentation.util.clickable
import com.strone.presentation.util.min
import com.strone.presentation.util.minus
import com.strone.presentation.util.plus

@Composable
fun FilledCentralTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    style: TextStyle = Typography.labelMedium,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    softWrap: Boolean = true,
    color: Color = Color.Black,
    overflow: TextOverflow = TextOverflow.Clip,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    cursorBrush: Brush = SolidColor(Color.Black),
    navigationIcon: @Composable () -> Unit,
    trailingIcon: @Composable () -> Unit,
    label: String = "",
) {
    var adaptedFontSize by remember { mutableStateOf(fontSize) }
    val focusRequester = remember { FocusRequester() }
    var lastTextLength by remember { mutableIntStateOf(value.length) }

    BasicTextField(
        value = TextFieldValue(value, selection = TextRange(value.length)),
        onValueChange = {
            onValueChange(it.text)
        },
        modifier = modifier.focusRequester(focusRequester).clickable {
            focusRequester.requestFocus()
        },
        enabled = enabled,
        readOnly = readOnly,
        textStyle = TextStyle(
            color = color,
            fontSize = adaptedFontSize,
            fontStyle = fontStyle,
            fontWeight = fontWeight,
            fontFamily = fontFamily
        ),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        visualTransformation = visualTransformation,
        onTextLayout = onTextLayout,
        interactionSource = interactionSource,
        cursorBrush = cursorBrush,
        decorationBox = @Composable { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = ColorBackgroundGray, shape = RoundedCornerShape(12.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                navigationIcon()
                Text(
                    modifier = Modifier.widthIn(max = 120.dp),
                    text = value.ifEmpty { label },
                    style = style,
                    color = color,
                    fontSize = adaptedFontSize,
                    fontStyle = fontStyle,
                    fontWeight = fontWeight,
                    fontFamily = fontFamily,
                    maxLines = maxLines,
                    onTextLayout = {
                        if (it.didOverflowWidth) {
                            adaptedFontSize -= 0.25f
                        }
                        if(value.length < lastTextLength) {
                            if (it.didOverflowWidth.not()) {
                                adaptedFontSize = (adaptedFontSize + 0.25f).min(fontSize)
                            }
                        }
                        if (value.isEmpty())
                            adaptedFontSize = fontSize
                        lastTextLength = value.length
                        onTextLayout(it)
                    },
                    softWrap = softWrap,
                    overflow = overflow
                )
                trailingIcon()
            }
        }
    )
}