package com.example.moviegrid.base

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.moviegrid.MovieGridApp
import com.example.moviegrid.R
import com.example.moviegrid.core.viewmodel.ViewModelFactory
import com.example.moviegrid.extentions.alertDialog
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        MovieGridApp.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    protected fun replaceFragment(fragment: BaseFragment) {
        (activity as BaseActivity).replaceFragment(fragment)
    }

    protected fun replaceFragmentWithBackStack(fragment: BaseFragment) {
        (activity as BaseActivity).replaceFragmentWithBackStack(fragment)
    }

    protected fun showError(messageRes: Int = -1, messageString: String = "") {
        val message =
            if (messageRes != -1 && messageString.isBlank()) getString(messageRes) else messageString
        android.app.AlertDialog.Builder(activity)
            .setTitle(R.string.error_title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(R.string.ok_title) { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()

            }.show()
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

    private fun showError(title: String, message: String, positiveButtonTitle: String) {
        activity?.alertDialog(
            title = title,
            message = message,
            positiveButtonTitle = positiveButtonTitle
        )?.show()
    }

    protected inline fun <reified V : ViewModel> bindViewModel() =
        lazy { ViewModelProviders.of(this, viewModelFactory).get(V::class.java) }
}