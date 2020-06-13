package products

import cafe.Adoption
import cafe.Shelter
import cafe.Sponsorship
import java.util.*

data class Cat(
        val id: String = UUID.randomUUID().toString(),
        val name: String,
        val breed: String,
        val sex: Char,
        var adopted: Boolean = false,
        var shelter: Shelter?,
        val sponsorships: MutableList<Sponsorship> = mutableListOf()) {

    fun adopt() {
        if (!adopted) {
            adopted = true
        }else {
            println("$name has already been adopted!")
        }
    }

    fun sponsor(sponsor: Sponsorship) {
        sponsorships.add(sponsor)
    }
}