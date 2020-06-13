package products

import CafeHolder
import java.util.*
import kotlin.random.Random.Default.nextInt

class MenuItem (
        val name: String,
        val id: String = nextInt(100,299).toString(),
        val price: Double,
        var numSold: Int = 0) {

    override fun toString(): String {
        return "Menu Item: $name\n" +
                "ID: $id\n" +
                "Price: $price\n" +
                "Sold: $numSold\n"
    }


}