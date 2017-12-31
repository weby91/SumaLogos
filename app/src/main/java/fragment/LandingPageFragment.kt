package fragment

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
import com.sumalogos.webster.sumalogos.R
import kotlinx.android.synthetic.main.fragment_landing.*
import kotlinx.android.synthetic.main.fragment_landing.view.*


/**
 * Created by webster on 01/01/18.
 */

class LandingPageFragment : Fragment() {

    private var fbCallbackManager: CallbackManager? = null

    private lateinit var tvFb: TextView

    private lateinit var vFragment: View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        vFragment = inflater!!.inflate(R.layout.fragment_landing, container, false)

        initFbLogin()

        return vFragment
    }

    private fun initFbLogin() {
        fbCallbackManager = CallbackManager.Factory.create()

        vFragment.loginButton.fragment = this
        vFragment.loginButton.setReadPermissions("public_profile", "email")

        vFragment.tvFbLogin.setOnClickListener {
            fbOnClick()
        }

//        tvFbLogin?.setOnTouchListener({ v, event ->
//            if (MotionEvent.ACTION_UP == event.action)
//                Toast.makeText(getApplicationContext(), "onTouch: Up", Toast.LENGTH_SHORT).show()
//            false
//        })

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
                        ) { `object`, response ->

                            Log.d("DUMA", "Response" + response.rawResponse)

//                            viewModel.facebookRequestOnCompleted(`object`)
//
//                            tvFBLogin.setSelected(facebookDataAccess.isFbLogin())
//                            tvFBLogin.setText(getString(R.string.facebook_connected_text))
                            !tvFbLogin!!.isEnabled

                        }
                        Log.d("DUMA", "Result" )

                        val parameters = Bundle()

                        parameters.putString("fields",
                                "id,name,email,gender,birthday,friends,updated_time,picture,link")

                        request.parameters = parameters

                        request.executeAsync()
                    }

                    override fun onCancel() {

                    }

                    override fun onError(e: FacebookException) {
                        Log.e("DUMA", e.message)
                    }
                })

        !loginButton.isPressed
        loginButton.invalidate()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        fbCallbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}