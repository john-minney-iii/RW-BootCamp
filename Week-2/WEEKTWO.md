- Cafe serves food, drinks, cats
- Cats can be adopted or sponsored:
- When adopted there is an adoption fee and papework that goes through a shelter
- When sponsored the cat will keep a collection of its sponsors
- Sponsors have recurring donations. To sponsor a cat, the patron just needs to favorite the cat

- [x] Use classes, inheritance, and polymorphism
- [ ] Dummy Data to tesT

PROGRAM MUST ALLOW THE CAFE TO PRODUCE A REPORT AT THE END OF EACH BUSINESS DAY WITH THE FOLLOWING INFORMATION

	- [ ] determine the total number of transactions (receipts) for that business day
	- [ ] determine the total number of customers (both employees who bought stuff and regular patrons) for that day
	- [ ] determine the total number of non-employee patrons for that day
	- FOR EACH RECEIPT:
		- [ ] if the purchaser is an employee, a 15% employee discount is applied, but only for food and drink items
		- [ ] if a cat is on a receipt (adoption), no discount is applied
	- [ ] determine the total number of adoptions, per shelter
	- [ ] produce a list of the unadopted, unsponsored cats staying at the cafe currently
	- [ ] produce a list of the sponsored, unadopted cats staying at the cafe
	- produce a list of the top ten selling menu items for that day (exclude cats from this), along with the gross sales of each item
	- [ ] produce a list of the most populatr cats, in order of rank (popularity is judged based on number os sponsorships)
	- [ ] whenver a cat is adopted, it should be removed from the cat inventory

- [x] People: Patrons, Employees
	- First/Last Name
	- ID, Phone Number, Email
	- (Unique to Employee)
		- Var: hire date, SSN, Salary
		- Methods: clock in/out
- [x] Menu Items: Drinks, Food
	- Price, ID

- [x] Receipts:
	- menu items (product ID, quanity)
	- customer ID
	- Receipt Total

- [x] Cats:
	- name, breed, sex
	- shelter ID, cat ID
	- sponsorships
	- Methods: adopt cat, sponsor cat (take customer ID)

- [x] Shelter
	- shelter ID
	- shelter phone number

- [x] Sponsorship
	- patron ID
	- cat ID

