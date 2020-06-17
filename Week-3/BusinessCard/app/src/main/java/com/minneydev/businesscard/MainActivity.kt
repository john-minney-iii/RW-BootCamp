package com.minneydev.businesscard

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = MainActivity::class.java.simpleName
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
            getPicture()
        }else {
            createBand()
        }

        getBand_button.setOnClickListener { v ->
            val bounceAnimation = AnimationUtils.loadAnimation(this,
                R.anim.bounce)
            v.startAnimation(bounceAnimation)
            createBand()
        }
    }

    //--------------------------------------------------------------------------------------------

        override fun onSaveInstanceState(outState: Bundle) {
            super.onSaveInstanceState(outState)
            band.name = bandName_textView.text.toString()
            outState.putString(NAME_KEY, band.name)
        }

        override fun onDestroy() {
            super.onDestroy()
        }

    //-------------------------------------------------------------------------------------------

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

     //---------------------------------------------------------------------------------------------

        private fun createBand() {
            band.refreshBand()
            bandName_textView.text = band.name
            getPicture()
        }

        private fun getPicture() {
            Picasso.get()
                .load(band.getImageUrl())
                .resize(200,200)
                .placeholder(R.drawable.image_placeholder)
                .into(band_logo)
        }

        private fun shareRepo() {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("REPO LINK", REPO_LINK)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, getString(R.string.copy_message), Toast.LENGTH_LONG).show()
        }

        private fun showAboutInfo() {
            val dialogTitle = getString(R.string.about_title, BuildConfig.VERSION_NAME)
            val dialogMessage = getString(R.string.about_message)
            val builder = AlertDialog.Builder(this)
            builder.setTitle(dialogTitle).setMessage(dialogMessage).create().show()
        }

}