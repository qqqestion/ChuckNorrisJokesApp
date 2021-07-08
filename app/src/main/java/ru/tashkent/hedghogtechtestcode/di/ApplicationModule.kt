package ru.tashkent.hedghogtechtestcode.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.tashkent.hedghogtechtestcode.BuildConfig
import ru.tashkent.hedghogtechtestcode.network.ChuckNorrisApi
import ru.tashkent.hedghogtechtestcode.util.AppCoroutineDispatchers
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl("http://api.icndb.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(createClient())
        .build()

    @Singleton
    @Provides
    fun provideChuckNorrisApi(retrofit: Retrofit) = retrofit.create(ChuckNorrisApi::class.java)

    private fun createClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BASIC)
            clientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        return clientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideAppCoroutineDispatchers() = AppCoroutineDispatchers(
        io = Dispatchers.IO,
        computation = Dispatchers.Default,
        main = Dispatchers.Main
    )
}