import cafe.Shelter
import people.Employee
import people.Patron
import products.Cat
import products.MenuItem
import java.awt.Menu

/*
    Thanks Filip for the idea to use UUIDs for the IDs
    Also thanks for reminding me to use packages. I should have remembered
    to use those since I used to write Minecraft Mods in Java

    Also the way that classes look is what the "auto-format" did. I'm not
    a huge fan of the way it looks, but it makes it easier to read, then I'll
    keep it that way. I really hope this one looks nicer!

    For tax I just did 2.9%. That is what it is in Colorado. Figured it would be a good base.
    I really don't know if this is how everything is supposed to be printed out. I tried to make it look as nice
    as I could... but I think it is still kind of ugly. We shall see. I've read through the requirements probably
    a good 20 times just to make sure I got everything. One can only hope.

    I didn't know exactly what was wanted with the top 10 items. So for that I just made it that if an item wasn't
    Bought it wouldn't show up on the list.
 */

fun main() {

    val cafe = CafeHolder()

    // Variables -------------------------------------------------------------------------------------------------------
    val customers = setOf<Patron> (
            Patron(fName = "Chance", lName = "Falk", phoneN = "777-154-3177",
                    email = "xX_mineDim0nds_Xx@mc.net"),
            Patron(fName = "Brennan", lName = "Garcia", phoneN = "159-753-1234",
                    email = "triforceHunter@nintendo.org")
    )

    val employees = setOf<Employee> (
            Employee(fName = "Jj", lName = "Minney", phoneN = "970-555-1234", email = "b0nFireLit@darksouls.com",
                    hireDate = "4/20/99", salary = 300000.00, ssn = "716-578-7561")
    )

    val shelters = setOf<Shelter> (
            Shelter(name = "Shelter 1", address = "114 78th St", phoneN = "970-555-1564"),
            Shelter(name = "Shelter 2", address = "154 20th St", phoneN = "987-379-0007")
    )

    val menuItems = mapOf<String,MenuItem> (
            "coffee"    to MenuItem(name = "Coffee", price = 5.00),
            "bagel"     to MenuItem(name = "Bagel", price = 7.25),
            "tea"       to MenuItem(name = "Tea", price = 2.00),
            "muffin"    to MenuItem(name = "Muffin", price = 3.50),
            "sandwich"  to MenuItem(name = "Sandwich", price = 2.00),
            "soup"      to MenuItem(name = "Soup", price = 1.50),
            "salad"     to MenuItem(name = "Salad", price = 6.00),
            "donuts"    to MenuItem(name = "Donut", price = 0.50),
            "cookie"    to MenuItem(name = "Cookie", price = 1.00),
            "waffles"   to MenuItem(name = "Waffles", price = 3.00)
    )

    // Functions -------------------------------------------------------------------------------------------------------
    fun sponsorCat(catName: String, personName: String) {
        cafe.getCat(name = catName, id = null)?.let {cat ->
            cafe.getPerson(fName = personName, id = null)?.let {person ->
                cafe.addSponsorship(cat, person)
            }
        }
    }

    fun adoptCat(catName: String, personName: String) {
        cafe.getCat(name = catName, id = null)?.let {cat ->
            cafe.getPerson(fName = personName, id = null)?.let {person ->
                cafe.addAdoption(cat, person)
            }
        }
    }

    fun buyItems(list: List<MenuItem?>, personName: String) {
        cafe.getPerson(fName = personName, id = null)?. let { cafe.makeReceipt(list, it) }
    }

    // Calls -----------------------------------------------------------------------------------------------------------
    cafe.initPatrons(customers)
    cafe.initShelters(shelters)
    cafe.initMenuItems(menuItems)
    employees.forEach { cafe.employeeCheckIn(it) }

    cafe.getShelter("Shelter 1")?.let { //Is this correct? Not sure if this is bad practice or not
        cafe.initCat(shelter = it, cat = Cat(name = "Smog", breed = "Dragon-Cat", sex = 'm', shelter = it))
        cafe.initCat(shelter = it, cat = Cat(name = "Litten", breed = "Fire-Cat", sex = 'f', shelter = it))
    }

    cafe.getShelter("Shelter 2")?.let {
        cafe.initCat(shelter = it, cat = Cat(name = "Cat-Dog", breed = "Unknown", sex = 'f', shelter = it))
        cafe.initCat(shelter = it, cat = Cat(name = "Ja'hba", breed = "Khajiit", sex = 'm', shelter = it))
    }

    adoptCat("Cat-Dog", "Jj")
    adoptCat("Ja'hba", "Chance")
    sponsorCat("Smog", "Brennan")


    val itemsBrennan = listOf<MenuItem?>(menuItems["coffee"], menuItems["salad"], menuItems["salad"],
                                         menuItems["salad"], menuItems["cookie"], menuItems["salad"], menuItems["muffin"], menuItems["waffles"])

    val itemsChance = listOf<MenuItem?>(menuItems["salad"], menuItems["salad"], menuItems["salad"],
                                        menuItems["sandwich"], menuItems["sandwich"], menuItems["salad"],
                                        menuItems["donuts"])

    val itemsJj = listOf<MenuItem?>(menuItems["tea"], menuItems["tea"], menuItems["tea"], menuItems["tea"],
                                    menuItems["tea"], menuItems["bagel"], menuItems["soup"])

    buyItems(itemsJj,  "Jj")
    buyItems(itemsChance, "Chance")
    buyItems(itemsBrennan, "Brennan")

    cafe.printDaily()



}
//
//
//"coffee"    to MenuItem(name = "Coffee", price = 5.00), 1
//"bagel"     to MenuItem(name = "Bagel", price = 7.25),
//"tea"       to MenuItem(name = "Tea", price = 2.00), 5
//"muffin"    to MenuItem(name = "Muffin", price = 3.50), 1
//"sandwich"  to MenuItem(name = "Sandwich", price = 2.00), 6
//"soup"      to MenuItem(name = "Soup", price = 1.50),
//"salad"     to MenuItem(name = "Salad", price = 6.00), 4
//"donuts"    to MenuItem(name = "Donut", price = 0.50),
//"cookie"    to MenuItem(name = "Cookie", price = 1.00), 1
//"waffles"   to MenuItem(name = "Waffles", price = 3.00)
