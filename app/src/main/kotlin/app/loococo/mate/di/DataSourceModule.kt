package app.loococo.mate.di

import app.loococo.data.remote.manger.AuthDataSource
import app.loococo.data.remote.manger.impl.AuthDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface RemoteDatasourceModule {
    @Binds
    fun provideAuthDataSourceImpl(repository: AuthDataSourceImpl): AuthDataSource
}