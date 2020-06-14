package com.minneydev.businesscard

class RandomBand {

    private val bands = setOf<String>(
        "AJR", "NSP", "Starbomb", "Def Leppard", "Twenty-One Pilots", "Zach Williams",
        "American Authors", "Avicii", "Big Daddy Weave", "Boston", "I Fight Dragons",
        "Imagine Dragons", "Journey", "Caravan Palace", "Casting Crowns", "GameChops"
    )

    var name: String = ""

    init {
        refreshBand()
    }

    //Some of the URLs go over that 'format line'. I feel like cutting them off just for a small bit
    //Makes it even harder to read. So i just cut the longest one and called it good.
    fun getImageUrl() : String {
        return when (name) {
            "AJR" -> "https://upload.wikimedia.org/wikipedia/en/a/ae/AJR_The_Click.jpg"
            "NSP" -> "https://upload.wikimedia.org/wikipedia/en/3/3e/Ninja_Sex_Party_-_Cool_Patrol.jpg"
            "Starbomb" -> "https://external-preview.redd.it/fW-liIWIVNFCiKMPiEiIJUELWFd6" +
                    "-q7pgpxEy862AUE.jpg?auto=webp&s=35a33d64d4de7e783ee0a9b71b6fc3f879a0716b" //Long URL
            "Def Leppard" -> "https://images-na.ssl-images-amazon.com/images/I/81SqgEk5HiL._SL1200_.jpg"
            "Twenty-One Pilots" -> "https://images-na.ssl-images-amazon.com/images/I/71k-5WHYB9L._SL1425_.jpg"
            "Zach Williams" -> "https://www.zachwilliamsmusic.com/assets/img/cover-chain.jpg"
            "American Authors" -> "https://upload.wikimedia.org/wikipedia/en/6/6e/American_Authors_-_Oh%2C_What_a_Life.jpg"
            "Avicii" -> "https://upload.wikimedia.org/wikipedia/en/c/c7/Avicii-Stories-2015-1200x1200.png"
            "Big Daddy Weave" -> "https://images-na.ssl-images-amazon.com/images/I/81jc1o0CaXL._SL1425_.jpg"
            "Boston" -> "https://upload.wikimedia.org/wikipedia/en/thumb/2/23/BostonBoston.jpg/220px-BostonBoston.jpg"
            "I Fight Dragons" -> "https://upload.wikimedia.org/wikipedia/en/8/8c/KABOOM_Album_Cover.png"
            "Imagine Dragons" -> "https://upload.wikimedia.org/wikipedia/en/9/95/Origins_cover.png"
            "Journey" -> "https://upload.wikimedia.org/wikipedia/en/e/e0/JourneyEscapealbumcover.jpg"
            "Caravan Palace" -> "https://upload.wikimedia.org/wikipedia/en/2/2e/Caravan_Palace_-_Robot_Face_album_cover.png"
            "Casting Crowns" -> "https://images-na.ssl-images-amazon.com/images/I/71gY-yRY3uL._SL1500_.jpg"
            "GameChops" -> "https://yt3.ggpht.com/a/AATXAJxYEkBQRj5xXPCNMEQA1HixT6sLGpDQmJE2kg=s900-c-k-c0xffffffff-no-rj-mo"
            else -> "https://www.theyearinpictures.co.uk/images//image-placeholder.png"
        }
    }

    fun refreshBand() {
        name = bands.random().toString()
    }


}