package com.bharat.mupple_app_sketch.legal_feature.presentation.legal

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bharat.mupple_app_sketch.auth_feature.domain.usecase.LoginUserUseCase
import com.bharat.mupple_app_sketch.legal_feature.domain.model.LegalDocument
import com.bharat.mupple_app_sketch.legal_feature.domain.usecase.LegalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class LegalType {
    TERMS_OF_SERVICES,
    PRIVACY_POLICY
}
data class LegalUiState(
    val title : String? = null,
    val isLoading : Boolean = true,
    val legalDocument: LegalDocument? = null,
    val legalError : String? = null
)


@HiltViewModel
class LegalViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val legalUseCase: LegalUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LegalUiState())
    val uiState = _uiState.asStateFlow()

    fun onLegalErrorDismiss(){
        _uiState.update { it.copy(legalError = null) }
    }

    init {
        val documentType : String? = savedStateHandle["documentType"]

        val title = when(documentType){
            LegalType.PRIVACY_POLICY.name -> "Privacy Policy"
            LegalType.TERMS_OF_SERVICES.name -> "Terms of Services"
            else -> null
        }

        _uiState.update { it.copy(title = title) }

        if(documentType != null){
            fetchLegalDocument(documentType)
        } else {
            _uiState.update { it.copy(legalError = "Document type not found.") }
        }


    }

    private fun fetchLegalDocument(documentType : String) : Unit {
        viewModelScope.launch {
            legalUseCase(documentType)
                .collect { result ->
                    result.fold(
                        onSuccess = { legalDocument ->
                            _uiState.update { it.copy(legalDocument = legalDocument, legalError = null, isLoading = false) }
                        },
                        onFailure = { e ->
                            _uiState.update { it.copy(legalDocument = null, isLoading = false, legalError = e.message) }
                        }
                    )
                }
        }

    }
}