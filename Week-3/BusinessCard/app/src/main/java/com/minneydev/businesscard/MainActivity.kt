package com.minneydev.businesscard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
        private const val NAME_KEY = "NAME_KEY"
        private const val LOGO_KEY = "LOGO_KEY"
    }

    private val bands = setOf<String>(
        "AJR", "NSP", "Starbomb", "Def Leppard", "Twenty-One Pilots", "Zach Williams"
    )

    lateinit var bandName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            bandName = savedInstanceState.getString(NAME_KEY).toString()
            bandName_textView.text = bandName
            setBandPicture()
        }

        getBand_button.setOnClickListener {
            setBandName()
            setBandPicture()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        bandName = bandName_textView.text.toString()
        outState.putString(NAME_KEY, bandName)
        Log.d(TAG, "Saving bandName: $bandName.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called.")
    }

    private fun setBandPicture() {
        band_logo.setImageResource(
            when (bandName_textView.text.toString()) {
                "AJR" -> R.drawable.ajr_logo
                "NSP" -> R.drawable.nsp_logo
                "Starbomb" -> R.drawable.starbomb_logo
                "Def Leppard" -> R.drawable.def_leppard_logo
                "Twenty-One Pilots" -> R.drawable.top_logo
                "Zach Williams" -> R.drawable.zach_williams_logo
                else -> R.drawable.image_placeholder
            }
        )
    }

    private fun setBandName()  {
        bandName_textView.text = bands.random().toString()
        Log.d(TAG, "Band Name: ${bandName_textView.text.toString()}")
    }

}