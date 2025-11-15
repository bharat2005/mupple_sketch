package com.bharat.mupple_app_sketch.auth_feature.presentation.registerProfile

import androidx.lifecycle.ViewModel
import com.bharat.mupple_app_sketch.auth_feature.domain.model.UserProfileDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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

    fun goToNextStep(){
        val nextStep = when(_uiState.value.currentStep){
            RegisterProfileSteps.GENDER -> RegisterProfileSteps.DOB
            RegisterProfileSteps.DOB -> RegisterProfileSteps.NICK_NAME
            RegisterProfileSteps.NICK_NAME -> RegisterProfileSteps.College
            RegisterProfileSteps.College -> RegisterProfileSteps.EMAIL_AUTH
            RegisterProfileSteps.EMAIL_AUTH -> RegisterProfileSteps.END
            RegisterProfileSteps.END -> null
        }
        if(nextStep != null){
            _uiState.update { it.copy(currentStep = nextStep) }
        }
    }

    fun goToPrevStep(){
        val prevStep = when(_uiState.value.currentStep){
            RegisterProfileSteps.GENDER -> null
            RegisterProfileSteps.DOB -> RegisterProfileSteps.GENDER
            RegisterProfileSteps.NICK_NAME -> RegisterProfileSteps.DOB
            RegisterProfileSteps.College -> RegisterProfileSteps.NICK_NAME
            RegisterProfileSteps.EMAIL_AUTH -> RegisterProfileSteps.College
            RegisterProfileSteps.END -> RegisterProfileSteps.EMAIL_AUTH
        }
        if(prevStep != null){
            _uiState.update { it.copy(currentStep = prevStep) }
        }
    }



}