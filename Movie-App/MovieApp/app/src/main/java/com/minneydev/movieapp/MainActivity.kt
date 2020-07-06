package com.minneydev.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.minneydev.movieapp.data.User
import com.minneydev.movieapp.savingUserData.UserRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//Comment For A Test Commit
class MainActivity : AppCompatActivity() {

    private val userRepository by lazy { UserRepository(App.userDatabase) }

    private var currentUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Navigation.findNavController(this, R.id.nav_host_fragment)

        lifecycle.coroutineScope.launch {
            currentUser = userRepository.getLoggedInUser(true)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuInflater.inflate(R.menu.app_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about -> { showInfo() }
            R.id.logOut -> { logOut() }
            R.id.aboutAccount -> { showAccount() }
        }
        return true
    }

    private fun showInfo() {
        AlertDialog.Builder(this)
            .setTitle(R.string.alert_title)
            .setMessage(R.string.alert_message)
            .create().show()
    }

    private fun showAccount() {
        AlertDialog.Builder(this)
            .setTitle("${currentUser?.email}")
            .setMessage("${currentUser?.password}")
            .create().show()
    }

    private fun logOut() {
        currentUser?.isLoggedIn = false
        lifecycleScope.launch {
            currentUser.let {
                userRepository.logOutUser(it)
            }
        }

        Navigation.findNavController(this, R.id.nav_host_fragment)
            .navigate(R.id.logInFragment)
    }

}