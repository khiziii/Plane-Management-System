/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.planemanagementprogramme;



public class ticketz {
    private int price;



    private String seat;
    private String row;

    private personz personalinfo;

    public ticketz(String row, String seat, int price, String firstName, String surname, String email) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.personalinfo = new personz(firstName, surname, email);
    }

    public String getrow() {
        return row;
    }

    public void setrow(String row) {
        this.row = row;
    }

    public String getseat() {
        return seat;
    }

    public void setseat(String seat) {
        this.seat = seat;
    }

    public int getprice() {
        return price;
    }

    public void setprice(int price) {
        this.price = price;
    }

    // Method to print ticket information
    public void printticketInfo() {
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: \u00A3" + price);
        // Print person information
        personalinfo.printPersonInfo();
    }


}