package com.bharat.mupple_app_sketch.auth_feature.presentation.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bharat.mupple_app_sketch.auth_feature.domain.usecase.LoginUserUseCase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginUiState(
    val isLoggingIn : Boolean = false,
    val loginError: String?  = null,
    val loginSuccess : Boolean = false
)

@HiltViewModel
class StartViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun onLocalGoogleLoginError(error : String){
        _uiState.update { it.copy(
            isLoggingIn = false,
            loginSuccess = false,
            loginError = error
        ) }
    }

    fun onGoogleLoginErrorDismiss(){
        _uiState.update { it.copy(
            isLoggingIn = false,
            loginSuccess = false,
            loginError = null
        ) }
    }


    fun setIsGoogleLoggingIn(boolean: Boolean){
        _uiState.update { it.copy(
            isLoggingIn = boolean,
            loginSuccess = false,
            loginError = null
        ) }
    }



    fun onLocalGoogleLoginSuccess(idToken : String){
        val cred = GoogleAuthProvider.getCredential(idToken, null)
        viewModelScope.launch {
            loginUserUseCase(cred)
                .collect { result ->
                    result.fold(
                        onSuccess = {

                        },
                        onFailure = { e ->
                            _uiState.update { it.copy(isLoggingIn = false, loginError = e.message, loginSuccess = false) }
                        }
                    )
                }
        }
    }



}