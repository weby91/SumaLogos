package com.sumalogos.webster.sumalogos.fragment

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.facebook.*
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.Scope
import com.google.firebase.iid.FirebaseInstanceId
import com.google.gson.JsonObject
import com.sumalogos.webster.sumalogos.R
import com.sumalogos.webster.sumalogos.activity.HomeActivity
import com.sumalogos.webster.sumalogos.model.Devotion
import com.sumalogos.webster.sumalogos.model.Result
import com.sumalogos.webster.sumalogos.model.User
import com.sumalogos.webster.sumalogos.util.AppConstant
import com.sumalogos.webster.sumalogos.util.AppDatabase
import com.sumalogos.webster.sumalogos.util.LiveDataResult
import com.sumalogos.webster.sumalogos.util.Status
import com.sumalogos.webster.sumalogos.viewmodel.BaseViewModel
import com.sumalogos.webster.sumalogos.viewmodel.LandingPageViewModel
import kotlinx.android.synthetic.main.fragment_landing.*
import kotlinx.android.synthetic.main.fragment_landing.view.*


/**
 * Created by webster on 01/01/18.
 */

class LandingPageFragment : BaseFragment()  {

    private var fbCallbackManager: CallbackManager? = null

    private lateinit var vFragment: View

    private var appDatabase: AppDatabase? = null

    private var googleApiClient: GoogleApiClient? = null

    private var mGoogleSignInClient: GoogleSignIn? = null

    private val landingPageViewModel = LandingPageViewModel()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        vFragment = inflater!!.inflate(R.layout.fragment_landing, container, false)

        initFbLogin()
        setupGmailSDK()

        return vFragment
    }

    private fun initFbLogin() {
        fbCallbackManager = CallbackManager.Factory.create()

        vFragment.loginButton.fragment = this
        vFragment.loginButton.setReadPermissions("public_profile", "email")

//        try {
//            object : AccessTokenTracker() {
//                override fun onCurrentAccessTokenChanged(accessToken: AccessToken, accessToken2: AccessToken?) {
//                    if (accessToken2 == null) {
//                        Log.d("weby", "User Logged Out.")
//                    }
//                }
//            }
//        } catch (e: Exception) {
//            Log.d("weby", e.message)
//        }


        vFragment.tvFbLogin.setOnClickListener {
            fbOnClick()
        }

        vFragment.tvGoogleLogin.setOnClickListener {
            googleOnClick()
        }

    }

    private fun googleOnClick() {
        if (googleApiClient!!.hasConnectedApi(Auth.GOOGLE_SIGN_IN_API)) {
            googleApiClient!!.clearDefaultAccountAndReconnect()
        }

        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
        startActivityForResult(signInIntent, AppConstant.REQUEST_CODE_RC_SIGN_IN)
    }

    private fun fbOnClick() {
        vFragment.loginButton?.performClick()
        vFragment.loginButton?.isPressed
        vFragment.loginButton?.invalidate()
        if (!isFbLoggedIn()) {
            showProgressDialog()
            vFragment.loginButton?.registerCallback(fbCallbackManager,
                    object : FacebookCallback<LoginResult> {
                        override fun onSuccess(loginResult: LoginResult) {

                            val request = GraphRequest.newMeRequest(
                                    loginResult.accessToken
                            ) { result, _ ->

                                !tvFbLogin!!.isEnabled

                                var user = User()

                                user = landingPageViewModel.initFacebookUser(user, result)

                                if (user != null) {
                                    createUser(user)
                                } else {
                                    //TODO at allert message
                                }
                            }

                            val parameters = Bundle()

                            parameters.putString("fields",
                                    "id,name,email,gender,birthday,friends,updated_time,picture,link")

                            request.parameters = parameters

                            request.executeAsync()
                        }

                        override fun onCancel() {
                            cancelProgressDialog()
                        }

                        override fun onError(e: FacebookException) {
                            cancelProgressDialog()
                            Toast.makeText(activity,
                                    "Facebook Error",
                                    Toast.LENGTH_SHORT).show()
                        }
                    })
        }

        !loginButton.isPressed
        loginButton.invalidate()
    }

    fun createUser(user: User) {
        user.fcmToken = FirebaseInstanceId.getInstance().token

        val liveData = landingPageViewModel.createUser(user)

        liveData.observe(this, object : Observer<LiveDataResult<*>> {
            override fun onChanged(response: LiveDataResult<*>?) {
                try {
                    liveData.removeObserver(this)

                    if (response != null && response.status === Status.SUCCESS) {
                        val jsonObject = response.data as JsonObject
                        val id = java.lang.Long.parseLong(jsonObject.get("id").toString())
                        user.id = id
                        BaseViewModel.setUser(user)

                        retrieveDevotions()

//                        goToHome()
                        //TODO setelah itu bikin untuk gmail login
                        //TODO setelah itu insert data2 devotion ke database atau localdb (txt atau sejenisnya)
                        //TODO test selesai baca, PushNotif, if no internet handling
                        //TODO last login date setiap kali dia onCreate Home
                    }
                } catch (e: Exception) {
                    cancelProgressDialog()
                    Log.d("weby", e.message)
                }
            }
        })
    }

    private fun retrieveDevotions() {
        val liveData = landingPageViewModel.retrieveDevotions()

        liveData.observe(this, object : Observer<LiveDataResult<*>> {
            override fun onChanged(response: LiveDataResult<*>?) {
                try {
                    liveData.removeObserver(this)

                    if (response != null && response.status === Status.SUCCESS) {
                        val result = response.data as Result<*>

                        val devotions = result.data as List<Devotion>
                        BaseViewModel.addDevotions(devotions)

                        goToHome()
                    }
                } catch (e: Exception) {
                    Log.d("weby", e.message)
                }
                cancelProgressDialog()
            }
        })
    }

    private fun goToHome() {
        val intent = Intent(activity, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        activity.startActivity(intent)
    }

    private fun setupGmailSDK() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(Scope("https://www.googleapis.com/auth/gmail.readonly"))
                .requestServerAuthCode(getString(R.string.google_client_id), false)
                .requestEmail()
                .build()

        if (googleApiClient == null || !googleApiClient!!.isConnected) {
            googleApiClient = GoogleApiClient.Builder(activity)
                    .enableAutoManage(activity
                    ) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build()
        }
    }

    private fun isFbLoggedIn(): Boolean {
        return AccessToken.getCurrentAccessToken() != null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == AppConstant.REQUEST_CODE_RC_SIGN_IN) {
            if (data != null) {
                val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)

                if (result.status.statusCode == CommonStatusCodes.SUCCESS) {
                    val account = result.signInAccount

                    if (account != null) {
                        var user = User()

                        user.email = account.email
                        user.socialMediaId = account.id
                        user.fullName = account.displayName
                        user.socialMediaPicture = account.photoUrl.toString()

                        user = landingPageViewModel.initGoogleUser(user)

                        createUser(user)
                    }
                    Log.d("weby", result.toString())
                }
            }
        } else {
            fbCallbackManager?.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onPause() {
        super.onPause()

        if (googleApiClient != null && !googleApiClient!!.isConnected) {
            googleApiClient!!.stopAutoManage(activity)
            googleApiClient!!.disconnect()
        }
    }
}