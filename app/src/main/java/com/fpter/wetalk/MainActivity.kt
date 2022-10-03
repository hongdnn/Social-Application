package com.fpter.wetalk

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.fpter.wetalk.application.prefs.AppPreference
import com.fpter.wetalk.features.login.LoginActivity
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val appPreference: AppPreference by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            navigateToFirstScreen(
                appPreference
            )
        }, 1000)
    }

//    override fun onPause() {
//        super.onPause()
//        handler.removeCallbacksAndMessages(null)
//    }

    private fun navigateToFirstScreen(appPreference: AppPreference) {
        if (appPreference.isLoggedIn()) {

        } else {
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.homeContainer, LoginFragment())
//                setReorderingAllowed(true)
//                addToBackStack(null) // name can be null
//                commit()
//            }
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        finish()
    }
}