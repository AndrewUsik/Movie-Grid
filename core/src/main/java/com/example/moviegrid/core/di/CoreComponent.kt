package com.example.moviegrid.core.di

import com.example.moviegrid.core.App
import com.example.moviegrid.domain.interactor.MovieUseCase

interface ApplicationProvider : ToolsProvider, CoreProvider, UseCaseProvider

interface ToolsProvider {
    fun provideContext(): App
}

interface CoreProvider {
}

interface UseCaseProvider {
    fun provideMovieUseCase(): MovieUseCase
}
