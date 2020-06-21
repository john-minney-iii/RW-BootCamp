package com.minneydev.movieapp.data

import android.os.Parcel
import android.os.Parcelable

data class Movie(
    val id: Int,
    val releaseDate: String,
    val title: String,
    val summary: String,
    var posterUrl: String = "",
    var bannerUrl: String = "",
    var imdb: String = "") : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(dest: android.os.Parcel, flags: kotlin.Int) {
        dest.writeInt(id)
        dest.writeString(releaseDate)
        dest.writeString(title)
        dest.writeString(summary)
        dest.writeString(posterUrl)
        dest.writeString(bannerUrl)
        dest.writeString(imdb)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(source: Parcel) = Movie(source)

        override fun newArray(size: Int): Array<Movie?> = arrayOfNulls(size)
    }


}


