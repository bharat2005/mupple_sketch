package com.bharat.mupple_app_sketch.auth_feature.domain.repo

import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun signInWithGoogle(cred : AuthCredential) : Flow<Result<Unit>>

    fun registerWithGoogle(cred : AuthCredential) : Flow<Result<Unit>>

}