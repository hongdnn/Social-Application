package com.fpter.wetalk.features.login

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.fpter.wetalk.R
import com.fpter.wetalk.databinding.ActivityLoginBinding
import com.fpter.wetalk.features.home.HomeActivity
import com.fpter.wetalk.features.login.viewmodel.LoginViewModel
import com.fpter.wetalk.features.register.RegisterActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.txtSignUp.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }



        binding.btnSignIn.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            loginViewModel.login(email, password)
        }

        loginViewModel.loading.observe(this) {
            it?.let { loading ->
                if(loading) {
                    binding.loadingLayout.vLoading.visibility = View.VISIBLE
                    binding.loadingIcon.ivLoading.visibility = View.VISIBLE
                } else {
                    binding.loadingLayout.vLoading.visibility = View.GONE
                    binding.loadingIcon.ivLoading.visibility = View.GONE
                }
            }
        }

        loginViewModel.loginResult.observe(this) { text ->
            text?.let {
                if (it == "Success") {
                    startActivity(Intent(this, HomeActivity::class.java))
                } else {
                    val builder = AlertDialog.Builder(this, R.style.Theme_Material3_Light_Dialog)
                        .setMessage(it)
                        .setNeutralButton("OK", null)
                    val dialog: AlertDialog = builder.create()
                    dialog.show()

                    val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    layoutParams.gravity = Gravity.CENTER
                    dialog.getButton(AlertDialog.BUTTON_NEUTRAL).layoutParams = layoutParams

                }
            }
        }
    }
}

//ToastManagerHelper(this).showToastError(it)
//                    val snack: Snackbar = Snackbar.make(frameLayout, it, Snackbar.LENGTH_LONG)
//                    val view = snack.view
//                    snack.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
//                    val params = view.layoutParams as CoordinatorLayout.LayoutParams
//                    params.gravity = Gravity.CENTER
//                    view.layoutParams = params
//                    snack.show()