# Midterm project - Ana Bermejo
## Database Diagram
![WhatsApp Image 2022-07-05 at 9 11 52 AM](https://user-images.githubusercontent.com/106668319/177270649-a3b1ca88-73ee-4dda-b44c-63c535e419e5.jpeg)
## Preview
This is a banking system which relies on a working REST API runnining on a local server. Inside this banking account there is several different divisions:
### Accounts
There are 4 different account types: StudentChecking, Checking, Savings, and CreditCard. 
They all share some common features: 
* A balance 
* A secretKey
* A PrimaryOwner
* An optional SecondaryOwner
* A penaltyFee
* A creationDate
* A status (FROZEN, ACTIVE)

Some of them have some extra features such as:
* A monthlyMaintenanceFee
* A minimumBalance
* A creditLimit
* An interestRate

During the programming of this system an extra feature was added: the date of last balance update from the interest rate.
### Users
There are 2 types of users which have a username and a password to access their different actions: Admins and AccountHolders.

Admins have been thought of as bank workers which are able to access all accounts and modify their balance. They can also delete accounts of any type.

On the other hand AccountHolders are the primary (or secondary) owners of the accounts. They are able to access only their accounts and view their balance. They can also transfer money from their accounts to any other account (checking there are sufficient available funds).

There is another user type which has no security: ThirdParty users.

## URL petitions to be inserted in Postman
GET PETITIONS:
* "/AccountHolderAccounts/{id}" : enables the account holder to search for their account using their account id
* "/AccountHolderAccounts/{id}/balance": enables the account holder to view their account balance using their account id
* "/AdminAccounts/{id}": enables the admin to search for their account using their account id
* "/AdminAccounts/{id}/balance": enables the admin to view their account balance using their account id

PATCH PETITIONS:
* "/AdminAccounts/{id}/balance": enables the admin to alter the balance
* "/AccountHolderAccounts/{id}/balance": enables the accoun holder to transfer money

POST PETITIONS:
* "/AdminAccounts/CheckingAccount": enables the admin to create a checking account (implements the 24 year old requierement)
* "/AdminAccounts/SavingAccount": enables the admin to create a saving account
* "/AdminAccounts/CreditCard": enables the admin to create a credit card

DELETE PETITIONS:
* "/AdminAccounts/{id}": enables the admin to delete any account
