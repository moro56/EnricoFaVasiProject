package com.emperor.hpproject.domain.di

import com.emperor.hpproject.domain.Repository
import com.emperor.hpproject.domain.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindsRepository(repository: RepositoryImpl): Repository
}