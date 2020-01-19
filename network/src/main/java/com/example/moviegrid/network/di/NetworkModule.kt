package com.example.moviegrid.network.di

import com.example.moviegrid.network.NetworkClient
import dagger.Binds
import dagger.Module

@Module
interface NetworkModule {
    @Binds
    fun bindsNetworkClient(impl: NetworkClient.NetworkClientImpl): NetworkClient
}