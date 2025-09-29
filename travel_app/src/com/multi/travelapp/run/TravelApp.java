package com.multi.travelapp.run;

import com.multi.travelapp.view.TravelView;

import java.util.Scanner;

public class TravelApp {
    public static void main(String[] args) {
        TravelView travelView = new TravelView();
        Scanner input = new Scanner(System.in);

        travelView.customerMainPage(input.nextLong());
    }
}
