package com.minneydev.businesscard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val bands = setOf<String>(
        "AJR", "NSP", "Starbomb", "Def Leppard", "Twenty-One Pilots", "Zach Williams"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        getBand_button.setOnClickListener {
            setBandName()
            setBandPicture()
        }

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

    private fun setBandName() {
        bandName_textView.text = bands.random().toString()
    }
}