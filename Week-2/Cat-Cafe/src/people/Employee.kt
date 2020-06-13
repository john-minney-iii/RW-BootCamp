package people

class Employee(
        fName: String,
        lName: String,
        phoneN: String,
        email: String,
        val hireDate: String, //NTS: Look into using a 'date' type instead of a string for hireDate...
        val salary: Double,
        val ssn: String) : Person (fName = fName, lName = lName, phoneN = phoneN, email = email) {

    override fun toString(): String {
        return "Employee Name: $fName $lName\n" +
                "Employee Phone: $phoneN\n" +
                "Employee Email: $email\n" +
                "HireDate: $hireDate\n" +
                "Salary: \$$salary\n" +
                "SSN: ***-***-****\n"
    }

    fun clockIn() {}
    fun clockOut() {}


}