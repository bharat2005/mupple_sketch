package com.bharat.mupple_app_sketch.core.components



import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.w3c.dom.Text


@Composable
fun MyDialog(
    title : String? = null,
    text: String,
    colorButtonText : String? = null,
    grayButtonText : String? = null,
    onColorButtonClick : () -> Unit = {},
    onGrayButtonClick : () ->  Unit = {},
    onDismiss : () -> Unit = {}

) {

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            shape = RoundedCornerShape(18.dp)
        ){
            Column(
                modifier = Modifier.fillMaxWidth().padding(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if(title != null){
                    Text(title!!, color = Color.Black, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
                }

                Text(text, textAlign = TextAlign.Center, color = Color.Black)

                Spacer(modifier = Modifier.height(12.dp))

                if(colorButtonText != null) {
                    MyButton(
                        onClick = onColorButtonClick,
                        text = colorButtonText,
                        modifier = Modifier.fillMaxWidth().height(44.dp)
                    )
                }

                if(grayButtonText != null) {
                    MyButton(
                        onClick = onGrayButtonClick,
                        text = grayButtonText,
                        modifier = Modifier.fillMaxWidth().height(44.dp),
                        backgroundColor = Color.LightGray,
                        contentColor = Color.Black
                    )
                }



            }

        }
    }

}