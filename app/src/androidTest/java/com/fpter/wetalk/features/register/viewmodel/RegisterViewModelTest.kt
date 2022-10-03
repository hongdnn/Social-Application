package com.fpter.wetalk.features.register.viewmodel

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fpter.wetalk.application.helper.GsonHelper
import com.fpter.wetalk.application.prefs.AppPreferenceImpl
import com.fpter.wetalk.features.register.model.RegisterRequest
import com.fpter.wetalk.features.register.repository.IRegisterRepository
import com.fpter.wetalk.features.register.repository.RegisterApiService
import com.fpter.wetalk.features.register.repository.RegisterRepositoryImpl
import com.fpter.wetalk.features.register.usecase.IRegisterUseCase
import com.fpter.wetalk.features.register.usecase.RegisterUseCaseImpl
import com.google.gson.Gson
import io.reactivex.rxjava3.core.Observable
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class RegisterViewModelTest : TestCase() {
    private lateinit var viewModel: RegisterViewModel

    @Mock
    private lateinit var apiService: RegisterApiService

    @InjectMocks
    private lateinit var repository: RegisterRepositoryImpl
//
//    @InjectMocks
//    private lateinit var useCase: IRegisterUseCase

    @Before
    public override fun setUp() {
        super.setUp()
        MockitoAnnotations.openMocks(this)
        val context = ApplicationProvider.getApplicationContext<Context>()
        val repository: IRegisterRepository = RegisterRepositoryImpl(apiService)
        val usecase: IRegisterUseCase = RegisterUseCaseImpl(repository)
        val gsonHelper = GsonHelper(Gson())
        val appPreference = AppPreferenceImpl(context, gsonHelper)
        viewModel = RegisterViewModel(usecase, appPreference)
    }

    @Test
    fun registerUser(){
        Mockito.`when`(repository.registerUser(RegisterRequest("abc@gmail.com", "1","a","b")))
           // .then {
               // assertEquals(true, viewModel.updateStatusResult.value)
            //}
            .thenReturn(Observable.just("Success"))
        //viewModel.register("abc@gmail.com", "1","a","b")
        repository.registerUser(RegisterRequest("abc@gmail.com", "1","a","b"))
            .subscribe {
                    assertEquals("Success", it)

            }
    }
}