package com.easyhz.cmc15th_hackathon.di

import com.easyhz.cmc15th_hackathon.data.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun bindsPostRepository(
        mainRepositoryImpl: MainRepositoryImpl
    ): MainRepositoryImpl
}