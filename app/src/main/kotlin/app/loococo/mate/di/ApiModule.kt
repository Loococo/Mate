package app.loococo.mate.di

import app.loococo.mate.di.network.OtherNetworkClient
import app.loococo.data.remote.api.AuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun providerAuthApi(@OtherNetworkClient retrofit: Retrofit): AuthApi =
        retrofit.create(AuthApi::class.java)

}
