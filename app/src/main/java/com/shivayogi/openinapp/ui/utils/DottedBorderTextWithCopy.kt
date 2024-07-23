package com.shivayogi.openinapp.ui.utils

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shivayogi.openinapp.R
import com.shivayogi.openinapp.ui.theme.BlueSurface
import com.shivayogi.openinapp.ui.theme.color_background_card

@Composable
fun DottedBorderTextWithCopy(
    displayText: String,
    linkText: String,
    linkUrl: String
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current

    Box(
        modifier = Modifier
            .dottedBorder(
                color = BlueSurface,
                cornerRadius = 16.dp,
                dotRadius = 2.dp,
                gap = 1.5.dp
            )
            .padding(2.dp)
            .background(color_background_card)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val annotatedString = remember {
                AnnotatedString.Builder(linkUrl).apply {
                    addStringAnnotation(
                        tag = "URL",
                        annotation = linkUrl,
                        start = displayText.indexOf(linkText),
                        end = displayText.indexOf(linkText) + linkText.length
                    )
                    addStyle(
                        style = androidx.compose.ui.text.SpanStyle(
                            color = Color.Blue,
                            textDecoration = TextDecoration.Underline
                        ),
                        start = displayText.indexOf(linkText),
                        end = displayText.indexOf(linkText) + linkText.length
                    )
                }.toAnnotatedString()
            }

            Text(
                text = annotatedString,
                maxLines = 1,
                color = BlueSurface,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp, 4.dp)
                    .clickable {
                        // Handle link click
                        Toast
                            .makeText(context, "Link clicked: $linkUrl", Toast.LENGTH_SHORT)
                            .show()
                    }
            )

            IconButton(
                modifier = Modifier
                    .size(36.dp)
                    .padding(2.dp),
                onClick = {
                    clipboardManager.setText(AnnotatedString(displayText))
                    Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_copy),
                    contentDescription = "copy_icon",
                    modifier = Modifier.size(30.dp),
                    tint = BlueSurface
                )
            }
        }
    }
}


fun Modifier.dottedBorder(
    color: Color,
    cornerRadius: Dp,
    dotRadius: Dp,
    gap: Dp
) = drawBehind {
    drawDottedRoundRect(
        color = color,
        cornerRadius = cornerRadius.toPx(),
        dotRadius = dotRadius.toPx(),
        gap = gap.toPx()
    )
}

fun DrawScope.drawDottedRoundRect(
    color: Color,
    cornerRadius: Float,
    dotRadius: Float,
    gap: Float
) {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(dotRadius * 2, gap), 0f)
    val stroke = Stroke(width = dotRadius, pathEffect = pathEffect)
    drawRoundRect(
        color = color,
        size = size,
        cornerRadius = CornerRadius(0f, cornerRadius),
        style = stroke
    )
}