package app.loococo.mate.di.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthNetworkClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OtherNetworkClient
