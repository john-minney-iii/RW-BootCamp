package com.minneydev.movieapp.registerFragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.room.Room
import com.minneydev.movieapp.R
import com.minneydev.movieapp.savingUserData.USERDATABASE_NAME
import com.minneydev.movieapp.savingUserData.UserDataBase
import com.minneydev.movieapp.savingUserData.UserRepository
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.emailEditText
import kotlinx.android.synthetic.main.fragment_register.passwordEditText

class RegisterFragment : Fragment() {

    private lateinit var userDataBase: UserDataBase
    private val userRepository by lazy { UserRepository(userDataBase) }


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
        userDataBase = Room.databaseBuilder(context, UserDataBase::class.java, USERDATABASE_NAME)
            .allowMainThreadQueries().build()
    }

    private fun validateProfile() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        if (validateEmail(email) &&
            validatePassword(password, password2EditText.text.toString())) {
            saveUser(email, password)
            moveToMainScreen()
        }else {
            fail()
        }

    }

    private fun validateEmail(email: String) = email.contains("@gmail.com")

    private fun validatePassword(pass1: String, pass2: String)
            = ((pass1.length > 4) && (pass1 == pass2))

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

    private fun saveUser(email: String, password: String) {
        userRepository.newUser(email, password)
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