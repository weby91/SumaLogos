package com.sumalogos.webster.sumalogos.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sumalogos.webster.sumalogos.R
import fragment.LandingPageFragment

class LandingPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        supportFragmentManager.beginTransaction()
                .replace(R.id.flContainer, LandingPageFragment())
                .commit()
    }

    override fun onBackPressed() {

    }
}
