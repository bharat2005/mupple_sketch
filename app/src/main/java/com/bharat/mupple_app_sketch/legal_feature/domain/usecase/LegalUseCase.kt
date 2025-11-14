package com.bharat.mupple_app_sketch.legal_feature.domain.usecase

import com.bharat.mupple_app_sketch.legal_feature.domain.model.LegalDocument
import com.bharat.mupple_app_sketch.legal_feature.domain.repo.LegalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LegalUseCase @Inject constructor(
    private val legalRepository: LegalRepository
) {
    operator fun invoke(documentType : String) : Flow<Result<LegalDocument>>{
        return legalRepository.getLegalDocument(documentType)
    }
}