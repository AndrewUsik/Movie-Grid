package com.example.moviegrid.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.moviegrid.MovieGridApp
import com.example.moviegrid.core.viewmodel.ViewModelFactory
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        MovieGridApp.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    protected fun addFragment(fragment: BaseFragment) {
        (activity as BaseActivity).addFragment(fragment)
    }

    protected fun initToolbar(toolbar: Toolbar) {
        val activity = (activity as AppCompatActivity)
        activity.setSupportActionBar(toolbar)
        toolbar.navigationIcon = null
        toolbar.subtitle = null
    }

    protected fun onBackPressed() {
        (activity as BaseActivity).onBackPressed()
    }

    protected inline fun <reified V : ViewModel> bindViewModel() =
        lazy { ViewModelProviders.of(this, viewModelFactory).get(V::class.java) }
}