package com.bharat.mupple_app_sketch.legal_feature.data.repo

import com.bharat.mupple_app_sketch.legal_feature.domain.model.LegalDocument
import com.bharat.mupple_app_sketch.legal_feature.domain.repo.LegalRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LegalRepositoryIml @Inject constructor(
    private val firebaseFireStoreInstance : FirebaseFirestore
) : LegalRepository {
    override fun getLegalDocument(documentType: String): Flow<Result<LegalDocument>> {
        return flow {
            val docSnapShot = firebaseFireStoreInstance.collection("legal").document(documentType).get().await()
            val legalDocument = docSnapShot.toObject(LegalDocument::class.java)

            if(legalDocument != null){
                emit(Result.success(legalDocument))
            } else {
                emit(Result.failure(Exception("Document doesn't exist!")))
            }
        } .catch { e ->
            emit(Result.failure(Exception(e.message)))
        }
    }
}