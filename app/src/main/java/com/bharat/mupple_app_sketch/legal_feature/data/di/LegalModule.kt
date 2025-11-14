package com.bharat.mupple_app_sketch.legal_feature.data.di

import com.bharat.mupple_app_sketch.legal_feature.data.repo.LegalRepositoryIml
import com.bharat.mupple_app_sketch.legal_feature.domain.repo.LegalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LegalModule {

    @Binds
    @Singleton
    abstract fun bindLegalRepository(
        legalRepositoryIml: LegalRepositoryIml
    ) : LegalRepository

}