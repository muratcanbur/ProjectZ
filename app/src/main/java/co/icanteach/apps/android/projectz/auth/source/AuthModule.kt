package co.icanteach.apps.android.projectz.auth.source


import co.icanteach.apps.android.projectz.core.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlin.annotation.AnnotationRetention.RUNTIME

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Qualifier
    @Retention(RUNTIME)
    annotation class RemoteTasksDataSource

    @Singleton
    @RemoteTasksDataSource
    @Provides
    fun provideTasksRemoteDataSource(): AuthDataSource {
        return AuthRemoteDataSource()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object TasksRepositoryModule {

    @Singleton
    @Provides
    fun provideTasksRepository(
        @AppModule.RemoteTasksDataSource remoteTasksDataSource: AuthDataSource,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): AuthRepository {
        return AuthRepository(
            remoteTasksDataSource,
            ioDispatcher
        )
    }
}