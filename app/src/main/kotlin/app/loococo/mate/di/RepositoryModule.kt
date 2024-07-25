package app.loococo.mate.di

import app.loococo.data.repository.AuthRepositoryImpl
import app.loococo.data.repository.PreferencesRepositoryImpl
import app.loococo.data.repository.WorkspaceRepositoryImpl
import app.loococo.domain.repository.AuthRepository
import app.loococo.domain.repository.PreferencesRepository
import app.loococo.domain.repository.WorkspaceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    fun provideAuthRepositoryImpl(repository: AuthRepositoryImpl): AuthRepository

    @Binds
    fun provideWorkspaceRepositoryImpl(repository: WorkspaceRepositoryImpl): WorkspaceRepository

    @Binds
    fun providePreferencesRepositoryImpl(repository: PreferencesRepositoryImpl): PreferencesRepository
}