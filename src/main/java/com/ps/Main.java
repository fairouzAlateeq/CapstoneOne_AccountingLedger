package com.ps;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
public class Main {
    static Scanner intScanny = new Scanner(System.in);
    static Scanner stringScanny = new Scanner(System.in);

    // an array for products
    static ArrayList<Ledger> transactions = new ArrayList<>();

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

               transactions.add(ledger);

            }
            bfr.close();
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


            } catch(Exception e){
                e.printStackTrace();
            }

        }
        public static void addDeposit() throws IOException {
            // get now date, get now time
            LocalDateTime currentDateTime = LocalDateTime.now();
            LocalDate currentDate = currentDateTime.toLocalDate();
            LocalTime currentTime = currentDateTime.toLocalTime();

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
            try {
                BufferedWriter bfw = new BufferedWriter(new FileWriter("transactions.csv", true));
                bfw.write(String.format("\n%s|%s|%s|%s|%.2f",
                     currentDate,
                     currentTime,
                        newDescrption,
                        vendorName,
                        newAmount
                ));
                Ledger newTransaction = new Ledger(currentDate, currentTime, newDescrption, vendorName, newAmount);
                    transactions.add(newTransaction);


                bfw.close();

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        public static void makePayment () {
            // get now date, get now time
            LocalDateTime currentDateTime = LocalDateTime.now();
            LocalDate currentDate = currentDateTime.toLocalDate();
            LocalTime currentTime = currentDateTime.toLocalTime();
            // get Descrption
            System.out.println("Describe");
            String newDescrption = stringScanny.nextLine();
            // prompt user for vendor info
            System.out.println("Whats the vendors name?");
            String vendorName = stringScanny.nextLine();
            //prompt the user for amount
            System.out.println("How much is the payment?");
            float newAmount = intScanny.nextFloat();

            try {
                BufferedWriter bfw = new BufferedWriter(new FileWriter("transactions.csv", true));
                bfw.write(String.format("\n%s|%s|%s|%s|%.2f",
                        currentDate,
                        currentTime,
                        newDescrption,
                        vendorName,
                        newAmount
                ));
                Ledger newTransaction = new Ledger(currentDate, currentTime, newDescrption, vendorName, newAmount);
                transactions.add(newTransaction);


                bfw.close();

            } catch (Exception e) {
                e.printStackTrace();
            }




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
                System.out.println("6. search ny amount");
                System.out.println("7. search by specific date");
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
                    case 6:
                        searchByAmount();
                        break;
                    case 7:
                        searchByDate();
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

        for (Ledger ledger: transactions) {
            if ((ledger.getDate().equals(currentDate) || ledger.getDate().isBefore(currentDate)) && (ledger.getDate().isAfter(firstDayOfMonth) || ledger.getDate().equals(firstDayOfMonth)))
                System.out.println(ledger.toString());
        }

    }
    public static void previousMonth(){
        LocalDate currentDate = LocalDate.now();
        LocalDate previousMonthDate = currentDate.minusMonths(1);
        LocalDate firstDayOfPreviousMonth = previousMonthDate.withDayOfMonth(1);
        LocalDate lastDayOfPreviousMonth = previousMonthDate.withDayOfMonth(previousMonthDate.lengthOfMonth());
        for (Ledger ledger: transactions) {
            if ((ledger.getDate().isEqual(firstDayOfPreviousMonth) || ledger.getDate().isAfter(firstDayOfPreviousMonth)) && (ledger.getDate().isEqual(lastDayOfPreviousMonth) ||ledger.getDate().isBefore(lastDayOfPreviousMonth)))
                System.out.println(ledger);
        }


    }
    public static void yearToDate(){
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfYear = currentDate.withDayOfYear(1);
        for (Ledger ledger: transactions) {
            if ((ledger.getDate().equals(currentDate) || ledger.getDate().isBefore(currentDate)) && (ledger.getDate().isAfter(firstDayOfYear) || ledger.getDate().equals(firstDayOfYear)))
                System.out.println(ledger);
        }
    }
    public static void previousYear(){
        LocalDate currentDate = LocalDate.now();
        LocalDate previousYear = currentDate.minusYears(1);
        LocalDate firstDayOfPreviousYear = previousYear.withDayOfYear(1);
        LocalDate lastDayOfPreviousYear = previousYear.withDayOfYear(previousYear.lengthOfYear());
        for (Ledger ledger : transactions) {
            if (ledger != null &&
                    (ledger.getDate().isAfter(firstDayOfPreviousYear) || ledger.getDate().isEqual(firstDayOfPreviousYear)) &&
                    (ledger.getDate().isBefore(lastDayOfPreviousYear) || ledger.getDate().isEqual(lastDayOfPreviousYear)))
                System.out.println(ledger);

        }
    }
    public static void searchByVendor(){
        //for everyitem compare to user input
        //tolowercase.equals
        System.out.println("which vendor are you looking for?");
        String V = stringScanny.nextLine();

        for (Ledger ledger: transactions) {
            if (ledger != null && ledger.getVendor().equalsIgnoreCase(V))
            {
                System.out.println(ledger);
            }

        }

    }
    public static void searchByAmount(){
        System.out.println("Enter amount ");
        float comparisonAmount = intScanny.nextFloat();
        float tolerance = 0.01f;

        for (Ledger ledger: transactions) {
            if (ledger != null && ledger.getAmount() == comparisonAmount)
            {
                System.out.println(ledger);
            }

        }

    }
    public static void searchByDate(){
        System.out.println("what dat are you looking for? yyyy-mm-dd");
        String userDate = stringScanny.nextLine();
        for (Ledger ledger: transactions) {
            String ledgerDate = ledger.getDate().toString();
            if (ledger != null && ledgerDate.equals(userDate))
            {
                System.out.println(ledger);
            }

        }

    }

    }
