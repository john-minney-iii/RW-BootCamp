package com.minneydev.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import com.minneydev.movieapp.data.User
import com.minneydev.movieapp.manager.UserDataManager

class MainActivity : AppCompatActivity() {

    private lateinit var currentUser: User

    val dataManager = UserDataManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Navigation.findNavController(this, R.id.nav_host_fragment)

        dataManager.readUserData()?.let {
            currentUser = it
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.app_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about -> { showInfo() }
            R.id.logOut -> { logOut() }
            R.id.aboutAccount -> { showAccount() }
        }

        if (item.itemId == R.id.about) {
            showInfo()
        }
        return true
    }

    private fun showInfo() {
        AlertDialog.Builder(this)
            .setTitle(R.string.alert_title)
            .setMessage(R.string.alert_message)
            .create().show()
    }

    private fun logOut() {
        dataManager.deleteUserData()
        Navigation.findNavController(this, R.id.nav_host_fragment)
            .navigate(R.id.logInFragment)
    }

    private fun showAccount() {
        val tempString = getString(R.string.account_message,
            currentUser.email, currentUser.password
        )
        AlertDialog.Builder(this)
            .setTitle(R.string.account_title)
            .setMessage(tempString)
            .create().show()
    }




}