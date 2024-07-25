package app.loococo.mate.di

import app.loococo.mate.di.network.OtherNetworkClient
import app.loococo.data.remote.api.AuthApi
import app.loococo.data.remote.api.WorkspaceApi
import app.loococo.mate.di.network.AuthNetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideAuthApi(@OtherNetworkClient retrofit: Retrofit): AuthApi =
        retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideWorkspaceApi(@AuthNetworkClient retrofit: Retrofit): WorkspaceApi =
        retrofit.create(WorkspaceApi::class.java)
}