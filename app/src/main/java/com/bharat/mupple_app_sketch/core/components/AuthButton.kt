package com.bharat.mupple_app_sketch.core.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import com.bharat.mupple_app_sketch.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AuthButton(
    modifier: Modifier,
    shape : Shape = RoundedCornerShape(24.dp),
    text : String,
    onClick : () -> Unit
    ) {

    Row(
        modifier = modifier
            .clip(shape)
            .background(Color.LightGray)
            .clickable(onClick = onClick, indication = ripple(color = Color.Black, bounded = true), interactionSource = remember { MutableInteractionSource() })
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.CenterHorizontally)
    ){

            Image(
                painter = painterResource(R.drawable.google_icon_button),
                contentDescription = null,
                modifier = Modifier.fillMaxHeight()
            )

            Text(text, color = Color.Black)

        }


}