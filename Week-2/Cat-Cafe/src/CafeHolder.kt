import cafe.Adoption
import cafe.Receipt
import cafe.Shelter
import cafe.Sponsorship
import people.Employee
import people.Patron
import people.Person
import products.Cat
import products.MenuItem

/*
    The plan here is to hold all the 'cafe' information here. Maybe?
 */

open class CafeHolder {

    // Variables -------------------------------------------------------------
    private val receipts = mutableSetOf<Receipt>()
    private val people = mutableSetOf<Person>()
    private val adoptions = mutableSetOf<Adoption>()
    private val shelters = mutableSetOf<Shelter>()
    private val sponsorships = mutableListOf<Sponsorship>()
    private val menuItems = mutableSetOf<MenuItem>()
    private var boughtToday = mutableListOf<MenuItem>()
    private val priceForCat = 30.00          //I have no idea how much it costs to adopt a cat.
    private val priceForSponsor = 10.00
    //-----------------------------------------------------------------------

    // Init Methods
    fun initShelters(shelter: Set<Shelter>) {
        shelter.forEach {
            shelters.add(it)
        }
    }

    fun initPatrons(customers: Set<Patron>) {
        customers.forEach {
            people.add(it)
        }
    }

    fun employeeCheckIn(employee: Employee) {
        people.add(employee)
    }

    fun employeeCheckOut(employee: Employee) {
        people.remove(employee)
    }

    fun initMenuItems(menuItem: Map<String,MenuItem>) {
        menuItem.forEach { (_,v) ->
            menuItems.add(v)
        }
    }

    fun initCat(shelter: Shelter, cat: Cat) {
        shelter.cats.add(Cat(name = cat.name, breed = cat.breed, sex = cat.sex, shelter = shelter))
    }


    // Get Methods -----------------------------------------------------------

    fun getShelter(name: String): Shelter? {
        shelters.forEach {
            if (it.name == name) return it
        }
        return null
    }

    fun getCat(name: String?, id: String?): Cat? {
        shelters.forEach {
            it.cats.forEach {cat ->
                if (cat.name == name || cat.id == id) { return cat }
            }
        }
        return null
    }

    fun getPerson(fName: String?, id: String?): Person? {
        people.forEach {
            if (it.fName == fName || it.id == id) return it
        }
        return null
    }

    fun getMenuItem(name: String?, id: String?): MenuItem? {
        menuItems.forEach {
            if (it.name == name || it.id == id) return it
        }
        return null
    }

    // Methods ---------------------------------------------------------------
    // This method has a lot of code. I should probably split this up in a few methods
    fun makeReceipt(items: List<MenuItem?>, customer: Person) {
        items.forEach {
            if (it != null) {
                boughtToday.add(it)
            }
        }
        val tempReceipt = Receipt(customer = customer, boughtItems = items)
        tempReceipt.getTotal()
        customer.receipts.add(tempReceipt)
        if (customer is Employee) {
            tempReceipt.total -= (tempReceipt.total * 0.15)
        }
        if (customer.adoptedCats.isNotEmpty()) {
            tempReceipt.adoptedCats = customer.adoptedCats
            tempReceipt.total += (priceForCat * customer.adoptedCats.size)
        }
        if (customer.sponsoredCats.isNotEmpty()) {
            tempReceipt.sponsoredCats = customer.sponsoredCats
            tempReceipt.total += (priceForSponsor * customer.sponsoredCats.size)
        }
        receipts.add(tempReceipt)
    }

        fun addSponsorship(cat: Cat, person: Person) {
            val tempSponsor = Sponsorship(customer = person, cat = cat)
            cat.sponsor(tempSponsor)
            sponsorships.add(tempSponsor)
            person.sponsoredCats.add(cat)
        }

        fun addAdoption(cat: Cat, person: Person) {
            cat.shelter?.cats?.remove(cat)
            cat.adopt()
            adoptions.add(Adoption(customer = person, cat = cat))
            person.adoptedCats.add(cat)
        }

        private fun compressList(list: List<MenuItem>) : MutableList<MenuItem> {
            list.forEach {item ->
                item.numSold = list.count { it == item }
            }
            return list.toSet().toMutableList()
        }

        // Methods for Printing --------------------------------------------------------------------------------------------

        fun printDaily() {
            println("\nDaily Print-Out\n" +
                    "-------------------------------------------------------------------------------------------------")
            println("There was a total of ${receipts.size} transactions today!")
            println("There was a total of ${people.filter { it.receipts.isNotEmpty() }.size} people that bought something today.")
            println("There was a total of ${people.filterIsInstance<Patron>().size} customers today!")
            println("Adoptions Today: ")
            adoptionsPerShelter()
            println("Lonely Cats: (un-adopted and un-sponsored cats)")
            printLonelyCats()
            println("Sponsored Cats: ")
            printSponsored()
            println("Best Selling Items: ")
            printPopularItems()
            println("The most popular cats today were: ${printPopularCat()}")
            println("-----------------------------------------------------------------------------------------------------")
            println("Receipts")
            receipts.forEach {it.printReceipt()}
        }

        private fun adoptionsPerShelter() {
            shelters.forEach { shelter ->
                val adoptions: List<Adoption> = adoptions.filter { it.cat.shelter?.name == shelter.name }
                println("    - ${shelter.name}  (${adoptions.size})")
                if (adoptions.isNotEmpty()) {
                    adoptions.forEach {
                        println("        - ${it.cat.name}")
                    }
                } else {
                    println("        - No Adoptions")
                }
            }
        }

        private fun printLonelyCats() {
            shelterLoop@ for (shelter in shelters) {
                val lonely: List<Cat> = shelter.cats.filter { !it.adopted && it.sponsorships.isEmpty() }
                if (lonely.isNotEmpty()) {
                    lonely.forEach {
                        println("        - ${it.name}")
                    }
                    break@shelterLoop
                } else {
                    println("        - No Lonely Cats!")
                    break@shelterLoop
                }
            }
        }

        private fun printSponsored() {
            if (sponsorships.isNotEmpty()) {
                sponsorships.forEach {
                    println("    - ${it.cat?.name} was sponsored by ${it.customer?.fName}")
                }
            }else {
                println("   - No sponsored cats")
            }
        }

        private fun printPopularItems() { //If there is a tie it will just be at the will of the sort
            boughtToday = compressList(boughtToday)
            boughtToday.sortByDescending { it.numSold }
            popItems@ for (i in boughtToday) {
                if (boughtToday.indexOf(i) < 10) {
                    println("    - ${boughtToday.indexOf(i) + 1}. ${i.name}    (\$${i.price * i.numSold})")
                }else {
                    break@popItems
                }
            }
        }

        private fun printPopularCat() : String? {
            var popCat: Cat? = null
            shelters.forEach { shelter ->
                shelter.cats.forEach { cat ->
                    if (popCat != null) {
                        if (cat.sponsorships.size > popCat!!.sponsorships.size) {
                            popCat = cat
                        }
                    } else {
                        popCat = cat
                    }
                }
            }
            return "${popCat?.name}"
        }


        //--------------------------------------------------------------------------------------------------------------

}
