package com.minneydev.movieapp.loginFragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.room.Room
import com.minneydev.movieapp.R
import com.minneydev.movieapp.data.User
import com.minneydev.movieapp.savingUserData.USERDATABASE_NAME
import com.minneydev.movieapp.savingUserData.UserDataBase
import com.minneydev.movieapp.savingUserData.UserRepository
import kotlinx.android.synthetic.main.fragment_log_in.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.exitProcess

class  LogInFragment : Fragment() {

    private lateinit var userDataBase: UserDataBase
    private val userRepository by lazy { UserRepository(userDataBase) }
    private val currentUser by lazy { runBlocking {
        userRepository.getLoggedInUser(true)
    } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                closeApp()
            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (currentUser != null) { goToMainScreen() }

        loginBtn.setOnClickListener {
//            goToTestFragment()
            validateLogin()
        }

        registerTextView.setOnClickListener {
            goToRegisterScreen()
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        userDataBase = Room.databaseBuilder(context, UserDataBase::class.java, USERDATABASE_NAME)
            .build()
    }

    private fun validateLogin() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        lifecycleScope.launch {
            val fetchedUser = userRepository.getUserByEmail(email)
            if (fetchedUser != null && password == fetchedUser.password) {
                pass(fetchedUser)
            }else {
                fail()
            }
        }
    }

    private fun pass(user: User) {
        user.isLoggedIn = true
        lifecycleScope.launch { userRepository.logInUser(user) }
        goToMainScreen()

    }

    private fun fail() {
        activity?.let {
            AlertDialog.Builder(it)
                .setTitle(R.string.failed_login_title)
                .setMessage(R.string.failed_login_message)
                .show()
        }
        emailEditText.text.clear()
        passwordEditText.text.clear()
    }

    private fun goToMainScreen() {
        view?.let {
            Navigation.findNavController(it).navigate(R.id.action_logInFragment_to_movieFragment)
        }
    }

    private fun goToRegisterScreen() {
        view?.let {
            Navigation.findNavController(it).navigate(R.id.action_logInFragment_to_registerFragment)
        }
    }

    private fun goToTestFragment() {
        view?.let {
            Navigation.findNavController(it).navigate(R.id.action_logInFragment_to_testFragment)
        }
    }

    private fun closeApp() {
        exitProcess(0)
    }

}