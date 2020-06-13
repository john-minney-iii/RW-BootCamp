package com.minneydev.businesscard

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
        private const val NAME_KEY = "NAME_KEY"
        const val REPO_LINK = "https://github.com/Ex0dus19/RW-BootCamp"
        private val band = RandomBand()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            band.name = savedInstanceState.getString(NAME_KEY).toString()
            bandName_textView.text = band.name
            band_logo.setImageResource(band.pictureId)
        }else {
            createBand()
        }

        getBand_button.setOnClickListener {
            createBand()
        }

    }

    // Saved Instance Functions --------------------------------------------------------------------
        override fun onSaveInstanceState(outState: Bundle) {
            super.onSaveInstanceState(outState)
            band.name = bandName_textView.text.toString()
            outState.putString(NAME_KEY, band.name)
            Log.d(TAG, "Saving bandName: ${band.name}.")
        }

        override fun onDestroy() {
            super.onDestroy()
            Log.d(TAG, "onDestroy called.")
        }

    // Menu Functions ------------------------------------------------------------------------------
        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            super.onCreateOptionsMenu(menu)
            menuInflater.inflate(R.menu.menu, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.about_item -> { showAboutInfo() }
                R.id.share_item -> { shareRepo() }
            }
            return true
        }

    // Functions -----------------------------------------------------------------------------------
        private fun shareRepo() {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("REPO LINK", MainActivity.REPO_LINK)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, getString(R.string.copy_message), Toast.LENGTH_LONG).show()
        }

        private fun showAboutInfo() {
            val dialogTitle = getString(R.string.about_title, BuildConfig.VERSION_NAME)
            val dialogMessage = getString(R.string.about_message)
            val builder = AlertDialog.Builder(this)
            builder.setTitle(dialogTitle).setMessage(dialogMessage).create().show()
        }

        private fun createBand() {
            band.refreshBand()
            bandName_textView.text = band.name.toString()
            band_logo.setImageResource(band.pictureId)
        }
}