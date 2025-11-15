package com.bharat.mupple_app_sketch.auth_feature.presentation.registerProfile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bharat.mupple_app_sketch.auth_feature.presentation.registerProfile.RegisterProfileUiState
import com.bharat.mupple_app_sketch.auth_feature.presentation.registerProfile.RegisterProfileViewModel

@Composable
fun NickNameStep(
    viewModel: RegisterProfileViewModel,
    uiState: RegisterProfileUiState
) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Red),
        contentAlignment = Alignment.Center
    ){
        Text("NickNameStep")
        Button(
            onClick = {viewModel.goToNextStep()}
        ) {Text("Next") }
    }

}