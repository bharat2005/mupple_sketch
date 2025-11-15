package com.bharat.mupple_app_sketch.auth_feature.presentation.registerProfile

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.bharat.mupple_app_sketch.auth_feature.presentation.registerProfile.components.CollegeStep
import com.bharat.mupple_app_sketch.auth_feature.presentation.registerProfile.components.DobStep
import com.bharat.mupple_app_sketch.auth_feature.presentation.registerProfile.components.EmailAuthStep
import com.bharat.mupple_app_sketch.auth_feature.presentation.registerProfile.components.EndStep
import com.bharat.mupple_app_sketch.auth_feature.presentation.registerProfile.components.GenderStep
import com.bharat.mupple_app_sketch.auth_feature.presentation.registerProfile.components.NickNameStep
import com.bharat.mupple_app_sketch.auth_feature.presentation.registerProfile.components.RegisterProfileStepIndicator

@Composable
fun RegisterProfileScreen(
    onExit : () -> Unit,
    viewModel: RegisterProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()





    Column(
        modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.systemBars)
    ) {

        RegisterProfileStepIndicator()

        AnimatedContent(
            targetState = uiState.currentStep,
            transitionSpec = {
                if(targetState.ordinal > initialState.ordinal){
                    slideInHorizontally(initialOffsetX = {it}) + fadeIn() togetherWith
                            slideOutHorizontally(targetOffsetX = {-it}) + fadeOut()
                } else {
                    slideInHorizontally(initialOffsetX = {-it}) + fadeIn() togetherWith
                            slideOutHorizontally(targetOffsetX = {it}) + fadeOut()
                }
            }
        ) { targetState ->
            when(targetState){
                RegisterProfileSteps.GENDER -> GenderStep(
                    viewModel,
                    uiState
                )
                RegisterProfileSteps.DOB -> DobStep(
                    viewModel,
                    uiState
                )
                RegisterProfileSteps.NICK_NAME -> NickNameStep(
                    viewModel,
                    uiState
                )
                RegisterProfileSteps.College -> CollegeStep(
                    viewModel,
                    uiState
                )
                RegisterProfileSteps.EMAIL_AUTH -> EmailAuthStep(
                    viewModel,
                    uiState
                )
                RegisterProfileSteps.END -> EndStep(
                    viewModel,
                    uiState
                )

            }
        }






    }


    
}