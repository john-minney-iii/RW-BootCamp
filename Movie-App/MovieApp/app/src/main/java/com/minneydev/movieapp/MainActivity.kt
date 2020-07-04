package com.minneydev.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.room.Room
import com.minneydev.movieapp.savingUserData.USERDATABASE_NAME
import com.minneydev.movieapp.savingUserData.UserDataBase
import com.minneydev.movieapp.savingUserData.UserRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//Comment For A Test Commit
class MainActivity : AppCompatActivity() {

    private lateinit var userDataBase: UserDataBase
    private val userRepository by lazy { UserRepository(userDataBase) }
    private val currentUser by lazy { runBlocking  {
        userRepository.getLoggedInUser(true) } }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userDataBase = Room.databaseBuilder(this, UserDataBase::class.java, USERDATABASE_NAME)
            .build()
        setContentView(R.layout.activity_main)
        Navigation.findNavController(this, R.id.nav_host_fragment)

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