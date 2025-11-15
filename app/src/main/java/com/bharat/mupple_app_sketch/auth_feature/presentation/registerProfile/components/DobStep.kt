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
import androidx.compose.material.ripple.rememberRipple
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
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DobStep(
    viewModel: RegisterProfileViewModel,
    uiState: RegisterProfileUiState
) {
    val dob = uiState.userProfileDetails.dob

    var selectedDay by remember {mutableStateOf(dob.dayOfMonth)}
    var selectedMonth by remember { mutableStateOf(dob.monthValue) }
    var selectedYear by remember { mutableStateOf(dob.year ) }

    LaunchedEffect(dob) {
        selectedDay = dob.dayOfMonth
        selectedMonth = dob.monthValue
        selectedYear = dob.year
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        StepFrame(
            title = "Date Of Birth",
            text = "Once registered, it cannot be changed. If you register incorrectly, you may not be able to communicate with us."
        ) {

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 34.dp, vertical = 12.dp),
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
                            value = selectedMonth
                            displayedValues = monthNames
                            setOnValueChangedListener { _,_,newMonth ->
                                val selectedMonth = newMonth
                                val newDob = safeDateOf(
                                    selectedYear,
                                    newMonth,
                                    selectedDay
                                )
                                viewModel.onDobChanged(newDob)
                            }

                        }
                    },
                    update = { picker ->
                        if(picker.value != selectedMonth) picker.value = selectedMonth
                    }
                )

                //Days
                AndroidView(
                    factory = { context ->
                        NumberPicker(context).apply {
                            minValue = 1
                            maxValue = YearMonth.of(selectedYear, selectedMonth).lengthOfMonth()
                            value = selectedDay
                            setOnValueChangedListener { _,_,newDay ->
                                val selectedDay = newDay
                                val newDob = safeDateOf(
                                    selectedYear,
                                    selectedMonth,
                                    newDay
                                )
                                viewModel.onDobChanged(newDob)
                            }

                        }
                    },
                    update = { picker ->
                        if(picker.value != selectedDay) picker.value = selectedDay
                        if(picker.maxValue != YearMonth.of(selectedYear, selectedMonth).lengthOfMonth()) picker.maxValue = YearMonth.of(selectedYear, selectedMonth).lengthOfMonth()
                    }
                )


                //Year
                AndroidView(
                    factory = { context ->
                        NumberPicker(context).apply {
                            val today = LocalDate.now()
                            minValue = today.minusYears(30).year
                            maxValue = today.minusYears(18).year
                            value = selectedYear
                            setOnValueChangedListener { _,_,newYear ->
                                val selectedYear = newYear
                                val newDob = safeDateOf(
                                    newYear,
                                    selectedMonth,
                                    selectedDay
                                )
                                viewModel.onDobChanged(newDob)
                            }

                        }
                    },
                    update = { picker ->
                        if(picker.value != selectedYear) picker.value = selectedYear
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


@RequiresApi(Build.VERSION_CODES.O)
fun safeDateOf(year: Int, month : Int, day : Int) : LocalDate {
    val monthLength = YearMonth.of(year, month).lengthOfMonth()
    val correctDay = minOf(monthLength, day)
    return LocalDate.of(year, month, correctDay)
}

