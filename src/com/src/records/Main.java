package com.src.records;

record Empl(int id,String name,int sal){

}

public class Main{
    public static void main(String[] args) {
        Empl e1 = new Empl(1, "Aryan", 10000);
        Empl e2 = new Empl(2, "Rakesh", 10000);
        Empl e3 = new Empl(1, "Aryan", 10000);

        //Equallity check caus record automatically override tostring and hashcode and equals
        System.out.println(e1.equals(e3));  //true because it check on values
    }
}