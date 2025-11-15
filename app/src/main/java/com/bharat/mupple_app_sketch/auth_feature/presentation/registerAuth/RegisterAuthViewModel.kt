package com.bharat.mupple_app_sketch.auth_feature.presentation.registerAuth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bharat.mupple_app_sketch.auth_feature.domain.usecase.RegisterUserUseCase
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RegisterUiState(
    val isRegistering : Boolean = false,
    val registrationError : String? = null,
    val registrationSuccess : Boolean = false
)
@HiltViewModel
class RegisterAuthViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    fun onLocalGoogleRegisterationError(error : String){
        _uiState.update { it.copy(
            isRegistering = false,
            registrationSuccess = false,
            registrationError = error
        ) }
    }

    fun onGoogleRegistrationErrorDismiss(){
        _uiState.update { it.copy(
            isRegistering = false,
            registrationSuccess = false,
            registrationError = null
        ) }
    }

    fun setGoogleRegistrationLoading(boolean : Boolean){
        _uiState.update { it.copy(
            isRegistering = boolean,
            registrationSuccess = false,
            registrationError = null
        ) }
    }

    fun clearRegistraionSuccessFlag(){
        _uiState.update { it.copy(registrationSuccess = false) }
    }

    fun onLocalGoogleRegistrationSuccess(idToken : String){
        val cred = GoogleAuthProvider.getCredential(idToken, null)
        viewModelScope.launch {
            registerUserUseCase(cred)
                .collect { result ->
                    result.fold(
                        onSuccess = {
                            _uiState.update { it.copy(
                                isRegistering = false,
                                registrationError = null,
                                registrationSuccess = true
                            ) }
                        },
                        onFailure = { e ->
                            _uiState.update { it.copy(
                                isRegistering = false,
                                registrationError = e.message,
                                registrationSuccess = false
                            ) }
                        }
                    )
                }

        }
    }




}