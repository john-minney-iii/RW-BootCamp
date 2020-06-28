package com.minneydev.movieapp.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "movie_table")
data class Movie(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "releaseDate") val releaseDate: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "summary") val summary: String,
    @ColumnInfo(name = "posterUrl") var posterUrl: String = "",
    @ColumnInfo(name = "bannerUrl") var bannerUrl: String = "",
    @ColumnInfo(name = "imdb") var imdb: String = "") : Parcelable {

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


