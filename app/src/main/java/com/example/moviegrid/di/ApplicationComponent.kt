package com.example.moviegrid.di

import com.example.moviegrid.MovieGridApp
import com.example.moviegrid.base.BaseActivity
import com.example.moviegrid.base.BaseFragment
import com.example.moviegrid.core.di.ApplicationProvider
import com.example.moviegrid.core.di.ToolsProvider
import com.example.moviegrid.core.di.UseCaseProvider
import com.example.moviegrid.data.di.UseCaseComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [
        UseCaseProvider::class,
        ToolsProvider::class],
    modules = [
        ViewModelModule::class,
        ApplicationModule::class]
)
interface ApplicationComponent : ApplicationProvider {

    fun inject(app: MovieGridApp)
    fun inject(fragment: BaseFragment)
    fun inject(activity: BaseActivity)

    class Initializer private constructor() {

        companion object {
            fun init(app: MovieGridApp): ApplicationComponent {
                val toolsProvider: ToolsProvider = ToolsComponent.Initializer.init(app)
                val useCaseProvider = UseCaseComponent.Initializer.init(toolsProvider)
                return DaggerApplicationComponent.builder()
                    .toolsProvider(toolsProvider)
                    .applicationModule(ApplicationModule)
                    .useCaseProvider(useCaseProvider)
                    .build()
            }
        }
    }
}