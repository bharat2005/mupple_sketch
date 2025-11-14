package com.bharat.mupple_app_sketch.auth_feature.presentation.start

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class LoginUiState(
    val isLogingIn : Boolean = false,
    val loginError: String?  = null,
    val loginSuccess : Boolean = false
)

class StartViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun onGoogleLoginError(error : String){
        _uiState.update { it.copy(
            isLogingIn = false,
            loginSuccess = false,
            loginError = error
        ) }
    }











}