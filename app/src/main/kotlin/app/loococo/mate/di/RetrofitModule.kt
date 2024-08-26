package app.loococo.mate.di

import app.loococo.domain.repository.PreferencesRepository
import app.loococo.mate.BuildConfig
import app.loococo.mate.di.network.AuthNetworkClient
import app.loococo.mate.di.network.OtherNetworkClient
import app.loococo.mate.di.network.TokenAuthenticator
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .apply {
            setPrettyPrinting()
            setLenient()
            serializeNulls()
        }.create()

    @Provides
    fun provideNetworkClientBuilder(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        builder
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
        return builder
    }

    @AuthNetworkClient
    @Provides
    fun provideAuthNetworkClient(
        clientBuilder: OkHttpClient.Builder,
        tokenAuthenticator: TokenAuthenticator,
        preferencesRepository: PreferencesRepository
    ): OkHttpClient = clientBuilder
        .addInterceptor { chain ->
            if (preferencesRepository.getToken()?.accessToken != null) {
                val old = chain.request()
                val request = old.newBuilder()
                    .removeHeader("Authorization")
                    .addHeader(
                        "Authorization",
                        "Bearer ${preferencesRepository.getToken()!!.accessToken}"
                    )
                    .method(old.method, old.body)
                    .build()
                chain.proceed(request)
            } else {
                chain.proceed(chain.request())
            }
        }
        .authenticator(tokenAuthenticator)
        .build()

    @OtherNetworkClient
    @Provides
    fun provideOtherNetworkClient(
        clientBuilder: OkHttpClient.Builder
    ): OkHttpClient = clientBuilder.build()

    @AuthNetworkClient
    @Provides
    fun provideAuthRetrofit(
        @AuthNetworkClient client: OkHttpClient,
        gson: Gson
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    @OtherNetworkClient
    @Provides
    fun provideOtherRetrofit(
        @OtherNetworkClient client: OkHttpClient,
        gson: Gson
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()
}
