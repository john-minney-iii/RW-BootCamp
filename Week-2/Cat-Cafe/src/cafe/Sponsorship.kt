package cafe

import people.Person
import products.Cat

class Sponsorship(
        val customer: Person?,
        val cat: Cat?) {

    override fun toString(): String {
        return "Cust: ${customer?.fName}, Cat: ${cat?.name}"
    }

}