package com.sumalogos.webster.sumalogos.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.sumalogos.webster.sumalogos.R
import util.AppConstant

/**
 * Created by webster on 29/12/17.
 */

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        goToLandingPage()
    }

    private fun goToLandingPage() {
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, LandingPageActivity::class.java))
        }, AppConstant.SPLASH_TIME.toLong())
    }
}