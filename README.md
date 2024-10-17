# Accounting Ledger - Capstone One
### Project Description:
    Accounting Ledger is the first java project I have built to test my skills in java
       after one month of learning it. 
    Accounting Ledger has the ability to display a company's transactions, add a new deposit or payment
       and also display or search based on a sepcific attribute the user is looking for.
Now we will view the main screens of this project.

## Home Screen
![Main Menu Screen](images/MainMenuScreen.png)

   <p> This is the first menu the user sees when they start the program </p>

## Deposit/Payment 
![Deposit/Payment](images/DepositScreen.png)

   <p> The addDeposit and addPayment function in similar manner. <br>
The user is asked to describe the transaction, then enter the vendor's name and at last
enter the amount
</p>

<p> for payment however as the following piece of code demonstrates;
the amount is going to be multiplied by minus one for it to be saved 
into the transaction file as a payment.

</p>

``` java
bfw.write(String.format("\n%s|%s|%s|%s|%.2f",
                        currentDate,
                        currentTime,
                        newDescrption,
                        vendorName,
                        newAmount *= -1 // now it saves it to a nagative amount even if the user didn't put it hehe
                ));
```
## Ledger Menu
![Ledger Menu](images/LedgerMenu.png)


<p> 
the Ledger holds all the display properties starting with
the displayment of all transactions, payments only or reports which is demonstrated in details next.
</p>

## Reports Menu
![Reports Menu](images/ReportsScreen.png)

The reports menu has displayment abilities beyond just the depositis and payments.
<br> 
<ul>
<li> Month to date displays all transactions from the beginning for the month </li>
<li> Previous Month displays all transactions from the previous month </li>
<li> Year to date displays all transactions from the beginning for the year </li>
<li> Previous Year displays all transactions from the previous year </li>
<li> search by vendor allows you to enter the name of the vendor then displays all transaction to and from that vendor </li>
<li>search by specific date will display all transactions happened that day </li>
</ul>

<img src="images/Logo.png" width =150px height=150px style="margin:100px">
