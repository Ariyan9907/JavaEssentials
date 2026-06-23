package com.src.records;

interface Person{
    void work();
}
record Empl(int id,String name,int sal) implements Person{

    static int age=22;
    //we can give methods
    void disp(){
        System.out.println("Hello From Records");
    }

    @Override
    public void work(){
        System.out.println("Work as Worker");
    }
}

public class Main{
    public static void main(String[] args) {
        Person e1 = new Empl(1, "Aryan", 10000);
        Empl e2 = new Empl(1, "Aryan", 10000);



        //Equallity check caus record automatically override tostring and hashcode and equals
        System.out.println(e1.equals(e2));  //true because it check on values


        System.out.println(e1);//To strings overriden
        System.out.println(e2.age);

//        System.out.println(e1.id()+" "+e1.name()+" "+e1.sal());//Getters
//        e1.disp();

        e1.work();

    }
}