package app.loococo.mate.di

import app.fuyuki.kioku.di.network.AuthNetworkClient
import app.fuyuki.kioku.di.network.OtherNetworkClient
import app.loococo.mate.BuildConfig
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
    fun providerNetworkClientBuilder(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        builder
            .writeTimeout(60, TimeUnit.MINUTES)
            .readTimeout(60, TimeUnit.MINUTES)
        return builder
    }

    @AuthNetworkClient
    @Provides
    fun providerAuthNetworkClient(
        clientBuilder: OkHttpClient.Builder,
    ): OkHttpClient = clientBuilder
        .addInterceptor {
            val old = it.request()
            val request = old.newBuilder()
                .removeHeader("Authorization")
                .addHeader("Authorization", "Bearer")
                .method(old.method, old.body)
                .build()
            it.proceed(request)
        }.build()

    @OtherNetworkClient
    @Provides
    fun providerOtherNetworkClient(
        clientBuilder: OkHttpClient.Builder,
    ): OkHttpClient = clientBuilder.build()


    @AuthNetworkClient
    @Provides
    fun providerAuthRetrofit(@AuthNetworkClient client: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

    @OtherNetworkClient
    @Provides
    fun providerOtherRetrofit(@AuthNetworkClient client: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

}