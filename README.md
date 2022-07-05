# midterm_project
## Database Diagram
![WhatsApp Image 2022-07-05 at 9 11 52 AM](https://user-images.githubusercontent.com/106668319/177270649-a3b1ca88-73ee-4dda-b44c-63c535e419e5.jpeg)
## Preview
This is a banking system which relies on a working REST API runnining on a local server. Inside this banking account there is several different divisions:
### Accounts
There are 4 different account types: StudentChecking, Checking, Savings, and CreditCard. 
They all share some common features: 
A balance 
A secretKey
A PrimaryOwner
An optional SecondaryOwner
A penaltyFee
A creationDate
A status (FROZEN, ACTIVE)

Some of them have some extra features such as:
A monthlyMaintenanceFee
A minimumBalance
A creditLimit
An interestRate

During the programming of this system an extra feature was added: the date of last balance update from the interest rate.
### Users
There are 2 types of users which have a username and a password to access their different actions: Admins and AccountHolders.
Admins have been thought of as bank workers which are able to access all accounts and modify their balance. They can also delete accounts of any type.
On the other hand AccountHolders are the primary (or secondary) owners of the accounts. They are able to access only their accounts and view their balance
