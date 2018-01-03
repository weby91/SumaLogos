package com.sumalogos.webster.sumalogos.fragment

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginResult
import com.sumalogos.webster.sumalogos.MyApplication
import com.sumalogos.webster.sumalogos.R
import com.sumalogos.webster.sumalogos.util.AppDatabase
import com.sumalogos.webster.sumalogos.util.LiveDataResult
import com.sumalogos.webster.sumalogos.util.Status
import com.sumalogos.webster.sumalogos.viewmodel.LandingPageViewModel
import kotlinx.android.synthetic.main.fragment_landing.*
import kotlinx.android.synthetic.main.fragment_landing.view.*


/**
 * Created by webster on 01/01/18.
 */

class LandingPageFragment : Fragment() {

    private var fbCallbackManager: CallbackManager? = null

    private lateinit var tvFb: TextView

    private lateinit var vFragment: View

    private var appDatabase: AppDatabase? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        vFragment = inflater!!.inflate(R.layout.fragment_landing, container, false)

        initFbLogin()

        return vFragment
    }

    private fun initFbLogin() {
        fbCallbackManager = CallbackManager.Factory.create()

        vFragment.loginButton.fragment = this
        vFragment.loginButton.setReadPermissions("public_profile", "email")
        appDatabase = MyApplication().getAppDatabase()

        vFragment.tvFbLogin.setOnClickListener {
            fbOnClick()
        }
    }

    private fun fbOnClick() {
        vFragment.loginButton?.performClick()
        vFragment.loginButton?.isPressed
        vFragment.loginButton?.invalidate()
        vFragment.loginButton?.registerCallback(fbCallbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {

                        val request = GraphRequest.newMeRequest(
                                loginResult.accessToken
                        ) { result, response ->

                            !tvFbLogin!!.isEnabled

                            createUser(result.getString("name"))

                        }

                        val parameters = Bundle()

                        parameters.putString("fields",
                                "id,name,email,gender,birthday,friends,updated_time,picture,link")

                        request.parameters = parameters

                        request.executeAsync()
                    }

                    override fun onCancel() {

                    }

                    override fun onError(e: FacebookException) {}
                })

        !loginButton.isPressed
        loginButton.invalidate()
    }

    fun createUser(fullName: String) {
        val landingPageViewModel = LandingPageViewModel()

        val liveData = landingPageViewModel.createUser(fullName)

        liveData.observe(this, object : Observer<LiveDataResult<*>> {
            override fun onChanged(response: LiveDataResult<*>?) {
                try {
                    liveData.removeObserver(this)

                    if (response != null && response.status === Status.SUCCESS) {
                        //TODO TBC HERE
                    }
                } catch (e: Exception) {
                    Log.d("DUMA", e.message)
                }

            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        fbCallbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}