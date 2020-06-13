package people

import cafe.Receipt
import products.Cat
import java.util.*

open class Person(
        val id: String = UUID.randomUUID().toString(),
        val fName: String,
        val lName: String,
        val phoneN: String,
        val email: String,
        val adoptedCats: MutableSet<Cat> = mutableSetOf(),
        val sponsoredCats: MutableSet<Cat> = mutableSetOf(),
        val receipts: MutableSet<Receipt> = mutableSetOf()) {

    override fun toString(): String {
        return "Name: $fName $lName\n" +
                "ID: $id\n" +
                "Phone Number: $phoneN\n" +
                "Email: $email\n" +
                "Cats: $adoptedCats\n"
    }

    fun fullName() : String {
        return "$fName $lName"
    }

}