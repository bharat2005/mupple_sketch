package com.bharat.mupple_app_sketch.legal_feature.domain.repo

import com.bharat.mupple_app_sketch.legal_feature.domain.model.LegalDocument
import kotlinx.coroutines.flow.Flow

interface LegalRepository {
    fun getLegalDocument(documentType : String) : Flow<Result<LegalDocument>>
}