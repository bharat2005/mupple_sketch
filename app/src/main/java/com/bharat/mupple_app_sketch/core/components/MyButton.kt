package com.bharat.mupple_app_sketch.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun MyButton(
    modifier: Modifier,
    backgroundColor : Color = MaterialTheme.colorScheme.primary,
    contentColor : Color = Color.White,
    shape : Shape = RoundedCornerShape(24.dp),
    text : String ,
    onClick : () -> Unit
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(backgroundColor)
            .clickable(onClick = onClick, indication = ripple(color = Color.Black, bounded = true), interactionSource = remember { MutableInteractionSource() }),
        contentAlignment = Alignment.Center
    ){
        Text(text, color = contentColor)
    }

}