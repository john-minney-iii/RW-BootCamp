package com.minneydev.movieapp.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.minneydev.movieapp.R
import com.minneydev.movieapp.manager.UserDataManager
import kotlinx.android.synthetic.main.fragment_log_in.*

class  LogInFragment : Fragment() {

    private lateinit var userDataManager: UserDataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("CREATE", "$savedInstanceState")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userDataManager.readIsLoggedIn().let {
            if (it!!) { goToMainScreen() }
        }

        loginBtn.setOnClickListener {
            validateLogin()
        }

        registerTextView.setOnClickListener {
            goToRegisterScreen()
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        userDataManager = UserDataManager(context)
    }

    private fun validateLogin() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        if (email == userDataManager.readUserEmail()
            && password == userDataManager.readUserPassword()) { pass() }
        else { fail() }
    }

    private fun pass() {
        goToMainScreen()
        userDataManager.userLoggedIn()
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

//    private fun saveUser(email: String, password: String, isLoggedIn: Boolean) {
//        userDataManager.saveUserData(email, password, isLoggedIn)
//    }


}