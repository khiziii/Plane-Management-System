/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.planemanagementprogramme;
//ID:20158789
//MuhammadKhizr

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Planemanagementprogramme {
    static String[][] seats = {
            {"A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10", "A11", "A12", "A13", "A14"},
            {"B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10", "B11", "B12"},
            {"C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "C11", "C12"},
            {"D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10", "D11", "D12", "D13", "D14"}
    };
    static String[] booked = new   String[10] ;
    static int round = 0;
    static int totalsold = 0;
    static ticketz[] ticketArray = new ticketz[seats.length * seats[0].length];
    public static void main(String[] args) {
        displayMenu();
    }

    public static void displayMenu() {
        Scanner scanner = new Scanner(System.in);


        while (true) {
            for (int i = 0; i < 25; i++) {
                System.out.print("*");
            }
            System.out.println();
            System.out.println("*   Welcome to the Menu   *");
            for (int i = 0; i < 25; i++) {
                System.out.print("*");
            }
            System.out.println();
            System.out.println("1. Buy a seat");
            System.out.println("2. Cancel a seat");
            System.out.println("3. Find first available seat");
            System.out.println("4. Show seating arrangement");
            System.out.println("5. Print ticket info and total sales");
            System.out.println("6. Search tickets");
            System.out.println("0. Exit the programme");
            for (int i = 0; i < 25; i++) {
                System.out.print("*");
            }
            System.out.println();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    buySeat();
                    break;
                case 2:
                    cancelSeat();
                    break;
                case 3:
                    findFirstAvailable();
                    break;
                case 4:
                    showSeatingArrangement();
                    break;
                case 5:
                    printinfoandsales();
                    break;
                case 6:
                    searchTicket();
                    break;
                case 0:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }

    public static void buySeat() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your desired seat number:");
        String target = input.next();
        boolean found = searchseat(seats, target);


        if (found) {
            System.out.println("Element " + target + " found in the seats.");
            Scanner booking = new Scanner(System.in);
            System.out.print("Press 1 to book the seat: ");
            int finalise = booking.nextInt();
            if(finalise == 1) {
                String row = "";
                int seat_no = 0;
                int price = 0;

                char firstLetter = target.charAt(0);
                switch (firstLetter) {
                    case 'A':
                        row = "A";
                        break;
                    case 'B':
                        row = "B";
                        break;
                    case 'C':
                        row = "C";
                        break;
                    case 'D':
                        row = "D";
                        break;
                    default:
                        System.out.println("wrong input");
                        return;
                }

                int length = target.length();
                if (length == 2) {
                    int number = Character.getNumericValue(target.charAt(1));
                    switch (number) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                            seat_no = number;
                            price = 200;
                            break;
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                            seat_no = number;
                            price = 180;
                            break;
                        default:
                            System.out.println("wrong input");
                            return;
                    }
                } else if (length == 3) {
                    int number = Character.getNumericValue(target.charAt(2));
                    switch(number) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                            seat_no = number;
                            price = 150;
                            break;
                        default:
                            System.out.println("wrong input");
                            return;
                    }
                } else {
                    System.out.println("Wrong input");
                    return;
                }

                Scanner intake2 = new Scanner(System.in);
                System.out.println("Enter your first name:");
                String firstName = intake2.next();
                System.out.println("Enter your surname:");
                String surname = intake2.next();
                System.out.println("Enter your email:");
                String email = intake2.next();
                personz personalinfo = new personz(firstName, surname, email);
                ticketz ticket = new ticketz(row, target, price, firstName, surname, email);
                ticketArray[round] = ticket;
                ticket.printticketInfo();
                System.out.println(target + " has been booked for you");
                ++round;
                booked[round]=target;
                totalsold += price;
                Bookedarray(seats,target);
                save(target, surname);
            } else {
                System.out.println("Exiting to Menu");
            }
        } else {
            System.out.println("Element " + target + " not found in the seats.");
        }
    }

    public static void cancelSeat() {
        Scanner cancel = new Scanner(System.in);
        System.out.println("Enter the seat number to cancel:");
        String cancellation = cancel.next();
        int row_cancel = 0;
        int seatnum = 0;

        char firstLetter = cancellation.charAt(0);
        switch (firstLetter) {
            case 'A':
                row_cancel = 0; // For 'A', index starts at 0
                break;
            case 'B':
                row_cancel = 1;
                break;
            case 'C':
                row_cancel = 2;
                break;
            case 'D':
                row_cancel = 3;
                break;
            default:
                System.out.println("wrong input");
                return;
        }

        int length = cancellation.length();
        if (length == 2) {
            int number = Character.getNumericValue(cancellation.charAt(1));
            switch (number) {
                case 1:
                    seatnum = 0;
                    break;
                case 2:
                    seatnum = 1;
                    break;
                case 3:
                    seatnum = 2;
                    break;
                case 4:
                    seatnum = 3;
                    break;
                case 5:
                    seatnum = 4;
                    break;
                case 6:
                    seatnum = 5;
                    break;
                case 7:
                    seatnum = 6;
                    break;
                case 8:
                    seatnum = 7;
                    break;
                case 9:
                    seatnum = 8;
                    break;
                default:
                    System.out.println("wrong input");
                    return;
            }
        } else if (length == 3) {
            int number = Character.getNumericValue(cancellation.charAt(1));
            switch (number) {
                case 0:
                    seatnum = 9;
                    break;
                case 1:
                    seatnum = 10;
                    break;
                case 2:
                    seatnum = 11;
                    break;
                case 3:
                    seatnum = 12;
                    break;
                case 4:
                    seatnum = 13;
                    break;
                default:
                    System.out.println("wrong input");
                    return;
            }
        } else {
            System.out.println("Wrong input");
            return;
        }


        seats[row_cancel][seatnum] = cancellation;
        System.out.println("Seat " + cancellation + " has been cancelled.");
    }

    public static void findFirstAvailable() {
        boolean found = false;
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                String seat = seats[i][j];
                if (!seat.equals("booked")) {
                    System.out.println("First available seat: " + seat);
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }
        if (!found) {
            System.out.println("No available seats.");
        }
    }

    public static void showSeatingArrangement() {
        System.out.println("Seating Plan:");
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                String seat = seats[i][j];
                if (seat=="booked") {
                    System.out.print("X ");
                } else {
                    System.out.print("O ");
                }
            }
            System.out.println();
        }
    }


    public static void searchTicket() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your desired seat number:");
        String target = input.next();
        boolean found = searchseat(seats, target);
        int round = 0;
        if (found) {
            System.out.println("Seat" + target + " available.");
        }else {
            System.out.println("Seat " + target + " not available.");
        }
    }

    public static boolean searchseat(String[][] seats, String target) {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j].equals(target)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void cancel_seat(String cancellation) {
        ArrayList<String> bookedList = new ArrayList<>(Arrays.asList(booked));
        bookedList.remove(cancellation);
        booked = bookedList.toArray(new String[0]);
        for (int i = 0; i < round; i++) {
            if (ticketArray[i] != null && ticketArray[i].getseat().equals(cancellation)) {
                totalsold -= ticketArray[i].getprice();
                ticketArray[i] = null;
                round--;
                System.out.println("Seat " + cancellation + " has been cancelled.");
                return;
            }
        }

    }
    public static void Bookedarray(String[][] seats , String target) {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j].equals(target)) {
                    seats[i][j] = "booked";
                    return;
                }
            }
        }
    }
    public static void printinfoandsales() {
        if (round == 0) {
            System.out.println("No tickets have been sold yet.");
        } else {
            System.out.println("Ticket Info:");
            for (int i = 0; i < round; i++) {
                System.out.println(ticketArray[i].getseat());
            }
            System.out.println("Total sales: " + totalsold + " GBP");
        }
    }
    public static void save(String target, String surname) {
        String filename = target + ".txt";
        String lastname = surname;
        try {
            File file = new File(filename);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(filename + " is booked for " + lastname);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error while writing to the file: " + e.getMessage());
            e.printStackTrace();
        }
    }

}