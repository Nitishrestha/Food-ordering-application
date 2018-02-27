package com.foodorderingapp.controller;

public class Test {
    public static void main(String[] args) {
        try{
            int i=1;
            int j=0;

          //  int s =i/j;
            throw new RuntimeException("runtime Exception Occured");

        }
        catch (Exception e){
            System.out.println("Exception called.."+e.getMessage());
        }
    }
}
