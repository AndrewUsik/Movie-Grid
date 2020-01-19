package com.example.moviegrid.di

import com.example.moviegrid.core.App
import com.example.moviegrid.core.di.ToolsProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface ToolsComponent : ToolsProvider {

    @Component.Builder
    interface Builder {
        fun build(): ToolsComponent
        @BindsInstance
        fun app(app: App): Builder
    }

    class Initializer private constructor() {
        companion object {
            fun init(app: App): ToolsProvider = DaggerToolsComponent.builder()
                .app(app)
                .build()
        }
    }
}