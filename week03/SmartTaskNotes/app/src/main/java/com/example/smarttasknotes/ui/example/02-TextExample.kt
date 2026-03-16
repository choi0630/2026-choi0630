package com.example.smarttasknotes.ui.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smarttasknotes.R

@Preview(showBackground = true)
@Composable
fun SimpleText() {
    Text(text="Hello World")
}

@Preview(showBackground = true)
@Composable
fun StringResourceText() {
    Text(text=stringResource(R.string.hello_world))
}

@Preview(showBackground = true)
@Composable
fun BlueText() {
    Text(text="Hello World", color = Color.Blue)
}

@Preview(showBackground = true)
@Composable
fun BigText() {
    Text(text="Hello World", fontSize = 30.sp)
}

@Preview(showBackground = true)
@Composable
fun ItalicText() {
    Text(text="Hello World", fontStyle = FontStyle.Italic)
}

@Preview(showBackground = true)
@Composable
fun BoldText() {
    Text(text="Hello World", fontWeight = FontWeight.Bold)
}

@Preview(showBackground = true)
@Composable
fun CenterText() {
    Text(
        text="Hello World",
        modifier = Modifier.width(150.dp),
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun TextShadow() {
    val offset = Offset(5.0f, 10.0f)
    Text(
        text = "Hello world!",
        style = TextStyle(
            fontSize = 24.sp,
            shadow = Shadow(
                color = Color.Blue,
                offset = offset,
                blurRadius = 3f
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
fun DifferentFonts() {
    Column {
        Text(text="Hello World", fontFamily = FontFamily.Serif)
        Text(text="Hello World", fontFamily = FontFamily.SansSerif)
    }
}

@Preview(showBackground = true)
@Composable
fun MultipleStylesInText() {
    Text(
        text=buildAnnotatedString { // 문자열의 특정부분에 개별적 스타일 적용
            withStyle(style = SpanStyle(color = Color.Blue)) {
                append("H")
            }
            append("ello ")

            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
                append("W")
            }
            append("orld")
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ParagraphStyle() {
    Text(
        text=buildAnnotatedString {
            withStyle(style = ParagraphStyle(lineHeight = 30.sp)) {
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    append("Hello\n")
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                ) {
                    append("World\n")
                }
                append("Compose")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun LongText() {
    Text(text="hello ".repeat(50), maxLines = 2)
}

@Preview(showBackground = true)
@Composable
fun OverflowedText() {
    Text(text="Hello Compose ".repeat(50), maxLines = 2, overflow = TextOverflow.Ellipsis) // ... 으로 표시
}

@Preview(showBackground = true)
@Composable
fun SelectableText() {
    SelectionContainer { // 내부에 포함된 텍스트를 드래그하여 선택
        Text(text="This text is selectable")
    }
}

@Preview(showBackground = true)
@Composable
fun PartiallySelectableText() {
    SelectionContainer {
        Column {
            Text(text="This text is selectable")
            Text(text="This one too")
            Text(text="This one as well")
            DisableSelection {
                Text(text="But not this one")
                Text(text="Neither this one")
            }
            Text(text="But again, you can select this one")
            Text(text="And this one too")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextDecorationStyle() {
    Column {
        Text(
            text = "Text with Underline",
            style = TextStyle(
                color = Color.Black,
                fontSize = 24.sp,
                textDecoration = TextDecoration.Underline
            )
        )
        Text(
            text = "Text with Strike",
            style = TextStyle(
                color = Color.Blue,
                fontSize = 24.sp,
                textDecoration = TextDecoration.LineThrough
            )
        )
    }
}