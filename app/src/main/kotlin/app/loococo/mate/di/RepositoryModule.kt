package app.loococo.mate.di

import app.loococo.data.repository.AuthRepositoryImpl
import app.loococo.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Binds
    fun provideAuthRepositoryImpl(repository: AuthRepositoryImpl): AuthRepository
}