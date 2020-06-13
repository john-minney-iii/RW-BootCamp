package com.minneydev.businesscard

class RandomBand {

    private val bands = setOf<String>(
        "AJR", "NSP", "Starbomb", "Def Leppard", "Twenty-One Pilots", "Zach Williams"
    )

    var name: String = ""
    var pictureId: Int = setPicture()

    init { refreshBand() }

    private fun setPicture() : Int {
        return when (name) {
            "AJR" -> R.drawable.ajr_logo
            "NSP" -> R.drawable.nsp_logo
            "Starbomb" -> R.drawable.starbomb_logo
            "Def Leppard" -> R.drawable.def_leppard_logo
            "Twenty-One Pilots" -> R.drawable.top_logo
            "Zach Williams" -> R.drawable.zach_williams_logo
            else -> R.drawable.image_placeholder
        }
    }

    fun refreshBand() {
        name = bands.random().toString()
        pictureId = setPicture()
    }


}