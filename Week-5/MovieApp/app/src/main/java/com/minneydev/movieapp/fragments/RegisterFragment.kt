package com.minneydev.movieapp.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.minneydev.movieapp.R
import com.minneydev.movieapp.manager.UserDataManager
import kotlinx.android.synthetic.main.fragment_log_in.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.emailEditText
import kotlinx.android.synthetic.main.fragment_register.passwordEditText

class RegisterFragment : Fragment() {

    private lateinit var userDataManager: UserDataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerBtn.setOnClickListener { validateProfile() }
        helpTextView.setOnClickListener { showHelp() }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        userDataManager = UserDataManager(context)
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {}
    }

    private fun validateProfile() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        if (validateEmail(email) &&
            validatePassword(password, password2EditText.text.toString())) {
            saveUser(email, password, true)
            moveToMainScreen()
        }else {
            fail()
        }

    }

    private fun validateEmail(email: String) : Boolean {
        if (email.contains("@gmail.com")) {
            return true
        }
        return false
    }

    private fun validatePassword(pass1: String, pass2: String) : Boolean {
        if ((pass1.length > 4) && (pass1 == pass2)) {
            return true
        }
        return false
    }

    private fun fail() {
        activity?.let {
            AlertDialog.Builder(it)
                .setTitle(R.string.failed_reg_title)
                .setMessage(R.string.failed_reg_message)
                .show()
        }
        emailEditText.text.clear()
        passwordEditText.text.clear()
        password2EditText.text.clear()
    }

    private fun moveToMainScreen() {
        view?.let {
            Navigation.findNavController(it)
                .navigate(R.id.action_registerFragment_to_movieFragment)
        }
    }

    private fun saveUser(email: String, password: String, isLoggedIn: Boolean) {
        userDataManager.saveUserData(email, password, isLoggedIn)
    }

    private fun showHelp() {
        activity?.let {
            AlertDialog.Builder(it)
                .setTitle(R.string.help_title)
                .setMessage(R.string.help_message)
                .show()
        }
    }
}