package com.example.moviegrid.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.moviegrid.MovieGridApp
import com.example.moviegrid.R
import com.example.moviegrid.core.viewmodel.ViewModelFactory
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MovieGridApp.appComponent.inject(this)
    }

    fun replaceFragment(fragment: BaseFragment) {
        try {
            supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.container, fragment)
                .commitAllowingStateLoss()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }

    fun replaceFragmentWithBackStack(fragment: BaseFragment) {
        try {
            supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commitAllowingStateLoss()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    protected inline fun <reified V : ViewModel> bindViewModel() =
        lazy { ViewModelProviders.of(this, viewModelFactory).get(V::class.java) }

}
