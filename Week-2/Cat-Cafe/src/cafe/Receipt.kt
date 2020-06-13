package cafe

import CafeHolder
import people.Employee
import people.Patron
import people.Person
import products.Cat
import products.MenuItem
import java.lang.Math.round
import java.lang.String.format
import java.util.*
import kotlin.math.roundToInt
import kotlin.random.Random.Default.nextInt

class Receipt(
        val id: String = nextInt(700,999).toString(),
        val customer: Person,
        val boughtItems: List<MenuItem?>,
        var total: Double = 0.00,
        var adoptedCats: MutableSet<Cat> = mutableSetOf<Cat>(), //NullCheck since someone doesn't have to adopt/sponsor
        var sponsoredCats: MutableSet<Cat> = mutableSetOf<Cat>()) {

    private val boughtItemsSet: Set<MenuItem?> = combineItems()
    var totalItems: Int = 0

    private fun combineItems() : Set<MenuItem?>{
        boughtItems.forEach {item ->
            if (item != null) {
                item.numSold = boughtItems.count {it == item}
            }
        }
        return boughtItems.toSet()
    }

    fun getTotal()  {
        boughtItemsSet.forEach {
            if (it != null) {
                total += (it.price * it.numSold)
            }
        }
    }

    fun printReceipt() {
        println("---------------------------\n" +
                "ID: $id\n" +
                "Customer: ${customer.fullName()}\n" +
                "Email: ${customer.email}\n" +
                "$totalItems item(s) bought.")
        if (customer is Employee) {println("Employee Discount Applied for Menu Items")}
        if (customer.adoptedCats.isNotEmpty()) {
            println("${customer.adoptedCats.size} cat(s) adopted.")
        }
        if (customer.sponsoredCats.isNotEmpty()) {
            println("${customer.sponsoredCats.size} cat(s) sponsored.")
        }
        println("\t\t\tTotal: $${format("%.2f", total)}")
        println("\t\t\tTax: \$${format("%.2f", total * 0.029)}")
        println("\t\t\tAfter Tax: \$${format("%.2f", total + (total * 0.029))}")

    }

}