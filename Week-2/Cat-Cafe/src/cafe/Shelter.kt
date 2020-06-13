package cafe

import products.Cat
import java.util.*

class Shelter(
        val name: String,
        val id: String = UUID.randomUUID().toString(),
        val address: String,
        val phoneN: String,
        val cats: MutableSet<Cat> = mutableSetOf<Cat>()) {


}

