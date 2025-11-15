package com.bharat.mupple_app_sketch.auth_feature.presentation.registerProfile

import androidx.lifecycle.ViewModel
import com.bharat.mupple_app_sketch.auth_feature.domain.model.UserProfileDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

enum class RegisterProfileSteps{
    GENDER,
    DOB,
    NICK_NAME,
    College,
    EMAIL_AUTH,
    END
}

data class RegisterProfileUiState(
    val isRegisteringProfile : Boolean = false,
    val registrationProfileError : String? = null,
    val isLoading : Boolean = false,
    val currentStep : RegisterProfileSteps = RegisterProfileSteps.GENDER,
    val userProfileDetails : UserProfileDetails = UserProfileDetails()
)

@HiltViewModel
class RegisterProfileViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterProfileUiState())
    val uiState = _uiState.asStateFlow()



}