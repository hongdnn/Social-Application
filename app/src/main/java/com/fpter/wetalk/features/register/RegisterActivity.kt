package com.fpter.wetalk.features.register

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fpter.wetalk.databinding.ActivityRegisterBinding
import com.fpter.wetalk.features.register.viewmodel.RegisterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel by viewModel<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnSignUp.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val firstName = binding.edtFirstName.text.toString()
            val lastName = binding.edtLastName.text.toString()
            registerViewModel.register(email, password, firstName, lastName)
        }

        registerViewModel.loading.observe(this) {
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

        registerViewModel.updateStatusResult.observe(this) { text ->
            text?.let {
                Toast.makeText(this, text, Toast.LENGTH_LONG).show()
            }
        }

    }

}