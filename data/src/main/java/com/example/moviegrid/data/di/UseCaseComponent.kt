package com.example.moviegrid.data.di

import com.example.moviegrid.core.App
import com.example.moviegrid.core.di.ToolsProvider
import com.example.moviegrid.core.di.UseCaseProvider
import com.example.moviegrid.data.AppSchedulers
import com.example.moviegrid.data.dao.MovieDao
import com.example.moviegrid.data.db.SystemDatabase
import com.example.moviegrid.data.gateway.MovieGatewayImpl
import com.example.moviegrid.domain.Schedulers
import com.example.moviegrid.domain.gateway.MovieGateway
import com.example.moviegrid.domain.interactor.MovieUseCase
import com.example.moviegrid.network.di.NetworkComponent
import com.example.moviegrid.network.di.NetworkProvider
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [NetworkProvider::class, ToolsProvider::class],
    modules = [RepositoryModule::class, DataModule::class]
)
interface UseCaseComponent : UseCaseProvider {
    class Initializer private constructor() {
        companion object {
            fun init(toolsProvider: ToolsProvider): UseCaseProvider {
                val networkComponent = NetworkComponent.Initializer.init(toolsProvider)
                return DaggerUseCaseComponent.builder()
                    .networkProvider(networkComponent)
                    .toolsProvider(toolsProvider)
                    .build()
            }
        }
    }
}

@Module
class DBModule {
    //region db
    @Provides
    @Singleton
    fun provideSystemDatabase(app: App): SystemDatabase {
        return SystemDatabase.newInstance(app.getApplicationContext())
    }

    //endregion
}

@Module(
    includes = [DBModule::class]
)
class DataModule {
    @Provides
    @Singleton
    fun provideMovieUseCase(
        schedulers: Schedulers,
        movieGateway: MovieGateway
    ): MovieUseCase {
        return MovieUseCase(schedulers, movieGateway)
    }

    @Provides
    @Singleton
    fun provideMoviesDao(systemDatabase: SystemDatabase): MovieDao {
        return systemDatabase.movieDao()
    }
}

@Module
interface RepositoryModule {
    @Binds
    fun bindsSchedulers(impl: AppSchedulers): Schedulers

    @Binds
    fun bindsMovieGateway(impl: MovieGatewayImpl): MovieGateway
}
