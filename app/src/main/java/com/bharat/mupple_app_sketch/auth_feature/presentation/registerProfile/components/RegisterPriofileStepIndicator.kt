package com.bharat.mupple_app_sketch.auth_feature.presentation.registerProfile.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.bharat.mupple_app_sketch.auth_feature.presentation.registerProfile.RegisterProfileSteps
import com.bharat.mupple_app_sketch.auth_feature.presentation.registerProfile.RegisterProfileUiState
import com.bharat.mupple_app_sketch.auth_feature.presentation.registerProfile.RegisterProfileViewModel

@Composable
fun RegisterProfileStepIndicator(
    handleBack : () -> Unit,
    viewModel: RegisterProfileViewModel,
    uiState: RegisterProfileUiState
) {
    val totalSteps = RegisterProfileSteps.values().size
    val currentStep = uiState.currentStep.ordinal + 1
    val progress = currentStep / totalSteps.toFloat()

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(600, easing = LinearOutSlowInEasing)
    )

        Box(
            Modifier.fillMaxWidth().height(80.dp),
            contentAlignment = Alignment.Center
        ) {

            if(uiState.currentStep != RegisterProfileSteps.END){
                IconButton(
                    onClick = handleBack,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(Icons.Default.ArrowBack, null)
                }
            }



            LinearProgressIndicator(
                progress = animatedProgress,
                modifier = Modifier.fillMaxWidth(0.7f)
            )


        }

}