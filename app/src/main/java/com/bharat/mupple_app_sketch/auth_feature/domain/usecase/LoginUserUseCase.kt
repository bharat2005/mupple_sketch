package com.bharat.mupple_app_sketch.auth_feature.domain.usecase

import androidx.credentials.Credential
import com.bharat.mupple_app_sketch.auth_feature.domain.repo.AuthRepository
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
   private val authRepository: AuthRepository
) {
    operator fun invoke (cred: AuthCredential) : Flow<Result<Unit>>{
        return authRepository.signInWithGoogle(cred)
    }
}