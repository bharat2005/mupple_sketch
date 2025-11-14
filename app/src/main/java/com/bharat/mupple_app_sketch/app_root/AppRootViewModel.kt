package com.bharat.mupple_app_sketch.app_root

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

enum class AuthState {
    UNKNOWN,UNAUTHENTICATED,AUTHENTICATED
}

class AppRootViewModel @Inject constructor(

) : ViewModel() {

    private val _authState = MutableStateFlow(AuthState.UNAUTHENTICATED)
    val authState = _authState.asStateFlow()


}