package app.fuyuki.kioku.di.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthNetworkClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OtherNetworkClient
