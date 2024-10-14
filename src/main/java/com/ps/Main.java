package com.ps;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
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
            BufferedReader br = new BufferedReader(new FileReader("transactions.csv"));
            String header = br.readLine();
            String input;
            while ((input = br.readLine()) != null) {
                System.out.println(input);
                String[] transactions = input.split("\\|");
                // System.out.println(Arrays.toString(candy));
                LocalDate date = LocalDate.parse(transactions[0]);
                LocalTime time = LocalTime.parse(transactions[1]);
                String description = transactions[2];
                String vendor = transactions[3];
                float amount = Float.parseFloat(transactions[4]);
                Ledger ledger = new Ledger(date, time, description, vendor, amount);
                int nextEmptyIndex = transactions.length - 1;
                transactions[nextEmptyIndex] = String.valueOf(ledger);
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


            } catch(Exception e){
                e.printStackTrace();
            }

        }
        public static void addDeposit() {
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

    }
    public static void displayPayments(){

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

                }

            }
            while(reportsCommand != 0);

        }
        catch(Exception e){
            e.printStackTrace();
        }


    }


    }
