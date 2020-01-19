package com.example.moviegrid.network.di

import com.example.moviegrid.core.di.ToolsProvider
import com.example.moviegrid.network.NetworkClient
import dagger.Component
import javax.inject.Singleton

interface NetworkProvider {
    fun provideNetworkClient(): NetworkClient
}

@Singleton
@Component(
    dependencies = [ToolsProvider::class],
    modules = [ApiModule::class, NetworkModule::class]
)
interface NetworkComponent : NetworkProvider {
    class Initializer private constructor() {
        companion object {
            fun init(toolsProvider: ToolsProvider): NetworkProvider {
                return DaggerNetworkComponent.builder().toolsProvider(toolsProvider).build()
            }
        }
    }

}