package app.loococo.mate.di

import app.loococo.data.local.preferences.SharedPreferencesManager
import app.loococo.data.repository.AuthRepositoryImpl
import app.loococo.data.repository.PreferencesRepositoryImpl
import app.loococo.domain.repository.AuthRepository
import app.loococo.domain.repository.PreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Binds
    fun provideAuthRepositoryImpl(repository: AuthRepositoryImpl): AuthRepository

    @Binds
    fun providePreferencesRepositoryImpl(repository: PreferencesRepositoryImpl): PreferencesRepository
}