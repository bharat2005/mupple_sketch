package com.bharat.mupple_app_sketch.auth_feature.presentation.registerProfile.components

import android.os.Build
import android.widget.NumberPicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.bharat.mupple_app_sketch.auth_feature.presentation.registerProfile.RegisterProfileUiState
import com.bharat.mupple_app_sketch.auth_feature.presentation.registerProfile.RegisterProfileViewModel
import com.bharat.mupple_app_sketch.core.components.MyButton





@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DobStep(
    viewModel: RegisterProfileViewModel,
    uiState: RegisterProfileUiState
) {

    Column(
        modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.ime).padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        StepFrame(
            title = "Date Of Birth",
            text = "Once registered, it cannot be changed. If you register incorrectly, you may not be able to communicate with us."
        ) {

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 34.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                //Month
                AndroidView(
                    factory = { context ->
                        NumberPicker(context).apply {
                            val monthNames = arrayOf(
                                "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
                            )
                            minValue = 1
                            maxValue = 12
                            displayedValues = monthNames
                            setOnValueChangedListener { _,_,newMonth ->

                            }

                        }
                    }
                )

                //Days
                AndroidView(
                    factory = { context ->
                        NumberPicker(context).apply {
                            minValue = 1
                            maxValue = 30
                            setOnValueChangedListener { _,_,newMonth ->

                            }

                        }
                    }
                )


                //Year
                AndroidView(
                    factory = { context ->
                        NumberPicker(context).apply {
                            minValue = 2025
                            maxValue = 1950
                            setOnValueChangedListener { _,_,newMonth ->

                            }

                        }
                    }
                )







            }



    }


        MyButton(
            onClick = {viewModel.goToNextStep()},
            modifier = Modifier.fillMaxWidth().height(50.dp),
            text = "Next"
        )


}
}

