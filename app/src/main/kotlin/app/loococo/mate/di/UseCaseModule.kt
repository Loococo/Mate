package app.loococo.mate.di

import app.loococo.domain.repository.AuthRepository
import app.loococo.domain.usecase.AuthUseCase
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

}
