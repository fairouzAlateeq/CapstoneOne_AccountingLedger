package com.ps;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
public class Main {
    static Scanner intScanny = new Scanner(System.in);
    static Scanner stringScanny = new Scanner(System.in);

    // an array for products
    static Ledger[] transactions = new Ledger[100];

    public static void main(String[] args) {
        int mainMenuChoice;

        try {
            BufferedReader bfr = new BufferedReader(new FileReader("transactions.csv"));
            String header = bfr.readLine();
            String input;
            int nextEmptyIndex = 0;
            while ((input = bfr.readLine()) != null) {
                // System.out.println(input);
                String[] transactionData = input.split("\\|");
                // System.out.println(Arrays.toString(candy));
                LocalDate date = LocalDate.parse(transactionData[0]);
                LocalTime time = LocalTime.parse(transactionData[1]);
                String description = transactionData[2];
                String vendor = transactionData[3];
                float amount = Float.parseFloat(transactionData[4]);
                Ledger ledger = new Ledger(date, time, description, vendor, amount);
                     //   transactions.length ;
               // transactionData[nextEmptyIndex] = String.valueOf(ledger);
                if (nextEmptyIndex < transactions.length) {
                    transactions[nextEmptyIndex] = ledger;
                    nextEmptyIndex++;  // Increment the index for the next transaction
                } else {
                    System.out.println("Transaction array is full!");
                    break;
                }

            }
            do {
                System.out.println("1. Add deposit");
                System.out.println("2. Make payment (debit)");
                System.out.println("3. Ledger");
                System.out.println("4. Exit");

                mainMenuChoice = intScanny.nextInt();

                switch (mainMenuChoice) {
                    case 1:
                        addDeposit();
                        break;
                    case 2:
                        makePayment();
                        break;
                    case 3:
                        displayLedger();
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Please choose a number between 1 and 4.");
                }

            }

                while (mainMenuChoice != 4) ;
            bfr.close();

            } catch(Exception e){
                e.printStackTrace();
            }

        }
        public static void addDeposit() throws IOException {
            // get now date, get now time
            LocalDateTime currentDateTime = LocalDateTime.now();
            LocalDate date = currentDateTime.toLocalDate();
            LocalTime time = currentDateTime.toLocalTime();

            // get Descrption
            System.out.println("Describe");
            String newDescrption = stringScanny.nextLine();
            // prompt user for vendor info
            System.out.println("Whats the vendors name?");
            String vendorName = stringScanny.nextLine();
            //prompt the user for amount
            System.out.println("How much is the deposit?");
            float newAmount = intScanny.nextFloat();
            // write them into the file
            BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true));
            Ledger newTransaction = new Ledger(date, time, newDescrption, vendorName, newAmount);

        }
        public static void makePayment () {
            // get now date, get now time
            LocalDateTime currentDateTime = LocalDateTime.now();
            LocalDate date = currentDateTime.toLocalDate();
            LocalTime time = currentDateTime.toLocalTime();
            // get Descrption
            System.out.println("Describe");
            String newDescrption = stringScanny.nextLine();
            // prompt user for vendor info
            System.out.println("Whats the vendors name?");
            String vendorName = stringScanny.nextLine();
            //prompt the user for amount
            System.out.println("How much is the payment?");
            float newAmount = intScanny.nextFloat();
            // write them into the file


        }
        public static void displayLedger() {
        int ledgerCommand;
            try {
                do{
                    System.out.println("1. display all transactions");
                    // for <0;
                    System.out.println("2. display payments only");
                    System.out.println("3. display Reports");
                    System.out.println("4. Home Screen");
                    ledgerCommand = intScanny.nextInt();
                    switch (ledgerCommand){
                        case 1:
                            displayAll();
                            break;
                        case 2:
                            displayPayments();
                            break;
                        case 3:
                            reports();
                            break;
                        case 4:
                            //figure it out ;
                        default:
                            System.out.println("please enter a number between 1 and 4");

                    }


                }
                while(ledgerCommand != 4);

            } catch (Exception e) {
               e.printStackTrace();
            }

        }

    public static void displayAll(){
        for (Ledger ledger: transactions) {
            if (ledger != null)
                System.out.println(ledger.toString());
        }


    }
    public static void displayPayments(){
        for (Ledger ledger: transactions) {
            if (ledger != null && ledger.getAmount() < 0)
                System.out.println(ledger.toString());
        }

    }
    public static void reports() {
        int reportsCommand;
        try{
            do{
                System.out.println("1. Month to date");
                System.out.println("2. Previous Month");
                System.out.println("3. Year to date");
                System.out.println("4. Previous Year");
                System.out.println("5. search by vendor");
                System.out.println("0. Back");
                reportsCommand = intScanny.nextInt();
                switch(reportsCommand){
                    case 1:
                        monthToDate();
                        break;
                    case 2:
                        previousMonth();
                        break;
                    case 3:
                        yearToDate();
                        break;
                    case 4:
                        previousYear();
                        break;
                    case 5:
                        searchByVendor();
                        break;
                    case 0:
                        reports();
                        break;
                    default:
                        System.out.println("Please pick a nummberrrr bruh");

                }

            }
            while(reportsCommand != 0);

        }
        catch(Exception e){
            e.printStackTrace();
        }


    }

    public static void monthToDate(){
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
        System.out.println("Month-to-Date:");
        System.out.println("Start of the month: " + firstDayOfMonth);
        System.out.println("Current date: " + currentDate);
    }
    public static void previousMonth(){
        LocalDate currentDate = LocalDate.now();
        LocalDate previousMonthDate = currentDate.minusMonths(1);
        System.out.println("Previous Month:");
        System.out.println(previousMonthDate.getMonth());


    }
    public static void yearToDate(){
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfYear = currentDate.withDayOfYear(1);
        System.out.println("Year-to-Date:");
        System.out.println("Start of the year: " + firstDayOfYear);
        System.out.println("Current date: " + currentDate);
    }
    public static void previousYear(){
        LocalDate currentDate = LocalDate.now();
        LocalDate previousYear = currentDate.minusYears(1);
        System.out.println("Previous Year:");
        System.out.println(previousYear.getYear());


    }
    public static void searchByVendor(){
        //for everyitem compare to user input
        //tolowercase.equals
        System.out.println("which vendor are you looking for?");
        String V = stringScanny.nextLine();

        for (Ledger ledger: transactions) {
            if (ledger != null && ledger.getVendor().equalsIgnoreCase(V))
            {
                System.out.println(ledger.toString());
            }

        }

    }



    }
