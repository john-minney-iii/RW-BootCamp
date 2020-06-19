package com.minneydev.movieapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minneydev.movieapp.R
import com.squareup.picasso.Picasso

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private val movieNames = arrayOf(
        Pair("Hackers", "https://fffmovieposters.com/wp-content/uploads/69090.jpg"),
        Pair("War Games", "https://i5.walmartimages.com/asr/76aa356c-458d-489b-a7b1-489e73c85cfb_1.2188c20155f7a2077724d3ffaf93cf19.jpeg"),
        Pair("The Sandlot", "https://images-na.ssl-images-amazon.com/images/I/71spjhASbtL._AC_SY879_.jpg"),
        Pair("Back to the Future", "https://images-na.ssl-images-amazon.com/images/I/51FgX0ONoML._AC_.jpg"),
        Pair("The Never Ending Story","https://images-na.ssl-images-amazon.com/images/I/51IullvU8gL._AC_.jpg"),
        Pair("Lord of the Rings","https://images-na.ssl-images-amazon.com/images/I/51MpUFlkuFL._AC_.jpg"),
        Pair("The Social Network","https://images-na.ssl-images-amazon.com/images/I/518zV7F39qL._AC_.jpg"),
        Pair("Ferris Bueller's Day Off","https://images-na.ssl-images-amazon.com/images/I/81ZQOcEnGDL._AC_SY606_.jpg"),
        Pair("Almost Famous","https://images-na.ssl-images-amazon.com/images/I/51Lr0ZuCtxL._AC_.jpg"),
        Pair("The Dirt","https://i.ebayimg.com/images/g/8nMAAOSw-6BclDUV/s-l300.jpg"),
        Pair("God's Not Dead","https://collegian.com/wp-content/uploads/2014/09/gods_not_dead_xlg.jpg"),
        Pair("The Perks of Being a Wallflower","https://images-na.ssl-images-amazon.com/images/I/51VnN-M3ljL._AC_.jpg"),
        Pair("Spiderman","https://images-na.ssl-images-amazon.com/images/I/A1CcbJfKqJL._AC_SY606_.jpg"),
        Pair("Deadpool","https://images-na.ssl-images-amazon.com/images/I/51Gh9OaWVcL._AC_.jpg")
    )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_card, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieNames.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.movieTitle.text = movieNames[position].first
        Picasso.get()
            .load(movieNames[position].second)
            .placeholder(R.drawable.image_placeholder)
            .resize(339,500)
            .into(holder.moviePoster)
    }

}