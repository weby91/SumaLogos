package com.sumalogos.webster.sumalogos.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sumalogos.webster.sumalogos.R
import com.sumalogos.webster.sumalogos.fragment.LandingPageFragment
import com.sumalogos.webster.sumalogos.util.Utils

class LandingPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        if (!Utils.getInstance(this).isLogin) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.flContainer, LandingPageFragment())
                    .commit()
        } else {
            goToHome()
        }
    }

    private fun goToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        this.startActivity(intent)
    }

    override fun onBackPressed() {

    }
}
