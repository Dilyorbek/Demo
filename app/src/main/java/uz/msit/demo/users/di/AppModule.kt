package uz.msit.demo.users.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import uz.msit.demo.core.utils.ApiEndpoint
import uz.msit.demo.users.data.remote.data_source.UserRemoteDataSource
import uz.msit.demo.users.data.remote.data_source.UserRemoteDataSourceImpl
import uz.msit.demo.users.data.remote.service.UserService
import uz.msit.demo.users.data.repository.UserRepositoryImpl
import uz.msit.demo.users.domain.repository.UserRepository
import uz.msit.demo.users.domain.usecase.GetUsersUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideUserService(): UserService {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor { message -> Timber.d("HTTP: $message") }.apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()

        return Retrofit.Builder()
            .baseUrl(ApiEndpoint.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(UserService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(userService: UserService): UserRemoteDataSource {
        return UserRemoteDataSourceImpl(userService)
    }

    @Singleton
    @Provides
    fun provideUserRepository(remoteDataSource: UserRemoteDataSource): UserRepository {
        return UserRepositoryImpl(remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideUseCase(repository: UserRepository): GetUsersUseCase {
        return GetUsersUseCase(repository)
    }
}