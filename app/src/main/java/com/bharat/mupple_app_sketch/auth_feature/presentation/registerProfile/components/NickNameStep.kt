package com.bharat.mupple_app_sketch.auth_feature.presentation.registerProfile.components

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import com.bharat.mupple_app_sketch.R
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.bharat.mupple_app_sketch.auth_feature.presentation.registerProfile.RegisterProfileUiState
import com.bharat.mupple_app_sketch.auth_feature.presentation.registerProfile.RegisterProfileViewModel
import com.bharat.mupple_app_sketch.core.components.MyButton

@Composable
fun NickNameStep(
    viewModel: RegisterProfileViewModel,
    uiState: RegisterProfileUiState
) {
    val nickNameValue by remember(uiState.userProfileDetails.nickname) {
        mutableStateOf(
            TextFieldValue(uiState.userProfileDetails.nickname, TextRange(uiState.userProfileDetails.nickname.length))
        )
    }


    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 16.dp).windowInsetsPadding(WindowInsets.ime),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        StepFrame(
            title = "Nick Name",
            text = "You can change your nick name later."
        ) {

            Column(
                modifier = Modifier.fillMaxWidth().padding(12.dp)
            ){
                OutlinedTextField(
                    value = nickNameValue,
                    onValueChange = {
                        viewModel.onNickNameChanged(it.text)
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Black
                    ),
                   placeholder = {Text("Please enter")},
                    trailingIcon = {
                        if(nickNameValue.text.isNotEmpty()){
                            IconButton(
                                onClick = {viewModel.onNickNameChanged("")}
                            ) {
                                Icon(painterResource(R.drawable.cancel),null)
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