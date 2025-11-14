package com.bharat.mupple_app_sketch.auth_feature.data.di

import com.bharat.mupple_app_sketch.auth_feature.data.repo.AuthRepositoryIml
import com.bharat.mupple_app_sketch.auth_feature.domain.repo.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthRepoModule {

    @Singleton
    @Binds
   abstract fun provideAuthRepo(authRepositoryIml: AuthRepositoryIml) : AuthRepository


}