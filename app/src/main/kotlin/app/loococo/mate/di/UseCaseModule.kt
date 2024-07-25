package app.loococo.mate.di

import app.loococo.domain.repository.AuthRepository
import app.loococo.domain.repository.PreferencesRepository
import app.loococo.domain.repository.WorkspaceRepository
import app.loococo.domain.usecase.AuthUseCase
import app.loococo.domain.usecase.PreferencesUseCase
import app.loococo.domain.usecase.WorkspaceUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideAuthUseCase(repo: AuthRepository): AuthUseCase = AuthUseCase(repo)

    @Provides
    @Singleton
    fun provideWorkspaceUseCase(repo: WorkspaceRepository): WorkspaceUseCase = WorkspaceUseCase(repo)

    @Provides
    @Singleton
    fun providePreferencesUseCase(repo: PreferencesRepository): PreferencesUseCase = PreferencesUseCase(repo)

}
