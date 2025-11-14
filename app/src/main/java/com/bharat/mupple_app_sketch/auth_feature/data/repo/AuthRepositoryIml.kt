package com.bharat.mupple_app_sketch.auth_feature.data.repo

import android.util.Log
import com.bharat.mupple_app_sketch.auth_feature.domain.repo.AuthRepository
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryIml @Inject constructor(
    private val firebaseAuthInstance : FirebaseAuth,
    private val firebaseFirestoreInstance : FirebaseFirestore
) : AuthRepository {
    override fun signInWithGoogle(cred: AuthCredential): Flow<Result<Unit>> {
        return flow {
            val result = firebaseAuthInstance.signInWithCredential(cred).await()
            val uid = result?.user?.uid ?: throw Exception("Uid is null")
            val userDoc = firebaseFirestoreInstance.collection("users").document(uid).get().await()


            if(userDoc.exists()){
                emit(Result.success(Unit))
            } else {
                firebaseAuthInstance.signOut()
                emit(Result.failure(Exception("User does not exist.")))
            }

        } .catch { e ->
            firebaseAuthInstance.signOut()
            emit(Result.failure(Exception(e)))
        }
    }


    override fun registerWithGoogle(cred: AuthCredential): Flow<Result<Unit>> {
        return flow{
            val result = firebaseAuthInstance.signInWithCredential(cred).await()
            val uid = result?.user?.uid ?: throw Exception("Uid is null")
            val userDoc = firebaseFirestoreInstance.collection("users").document(uid).get().await()

            if(userDoc.exists()){
                firebaseAuthInstance.signOut()
                emit(Result.failure(Exception("User alredy Exist")))
            } else {
                emit(Result.success(Unit))
            }

        }.catch { e ->
            firebaseAuthInstance.signOut()
            emit(Result.failure(Exception(e)))
        }
    }
}