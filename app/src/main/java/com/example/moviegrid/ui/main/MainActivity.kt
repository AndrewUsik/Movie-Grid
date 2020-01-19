package com.example.moviegrid.ui.main

import android.os.Bundle
import com.example.moviegrid.R
import com.example.moviegrid.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(MainFragment.newInstance())
    }
}
