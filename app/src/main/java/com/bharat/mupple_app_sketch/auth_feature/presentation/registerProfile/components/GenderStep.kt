package com.bharat.mupple_app_sketch.auth_feature.presentation.registerProfile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bharat.mupple_app_sketch.auth_feature.domain.model.Gender
import com.bharat.mupple_app_sketch.auth_feature.presentation.registerProfile.RegisterProfileUiState
import com.bharat.mupple_app_sketch.auth_feature.presentation.registerProfile.RegisterProfileViewModel
import com.bharat.mupple_app_sketch.core.components.MyButton
import com.bharat.mupple_app_sketch.R
@Composable
fun GenderStep(
    viewModel : RegisterProfileViewModel,
    uiState : RegisterProfileUiState
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        StepFrame(
            title = "Gender",
            text = "Once registered, your gender can not be changed."
        ) {

            Column(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {

                Gender.entries.forEach { gender ->
                    val isSelected = gender == uiState.userProfileDetails.gender
                    val bgColor = if(isSelected) Color(0xBE8BC34A) else Color.White
                    val textColor = if(isSelected) MaterialTheme.colorScheme.primary else Color.Gray

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(bgColor)
                            .clickable(onClick = {}),
                        contentAlignment = Alignment.Center
                    ){
                        if(isSelected){
                            Icon(
                                painter = painterResource(R.drawable.check),
                                tint = MaterialTheme.colorScheme.primary,
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.CenterStart)
                            )
                        }
                        Text(gender.displayName, color = textColor)
                    }
                }


            }


        }


        MyButton(
            onClick = {viewModel.goToNextStep()},
            modifier = Modifier.fillMaxWidth().height(50.dp),
            text = "Next"
        )


    }

}