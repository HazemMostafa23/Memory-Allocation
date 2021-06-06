/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoryalloc;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Zemzem
 */

public class MemoryAlloc {
    Memory x=new Memory();
    MemoryAlloc() throws FileNotFoundException{
        x.start();
        
    }
    
    void menu() throws FileNotFoundException{
     Scanner input=new Scanner(System.in);
   
       int y;
       System.out.println("Choose one of the below algorithms");
       System.out.println("Enter 1 for First fit \n"+"Enter 2 for Best Fit \n"+"Enter 3 for Worst Fit \n" + "Enter 4 to compare between the 3 Algorithms and show which is the most efficent \n" + "Enter 5 to change the inputs");
         while (!input.hasNextInt()) {
        System.out.println("That's an invalid input,please enter a number from 1 to 3");
        input.next(); 
    }
         
    y = input.nextInt();
     while(y>5){
         System.out.println("That's an invalid input,please enter a number from 1 to 5");
         y=input.nextInt();
     }
        
        switch(y)
        {
            case 1: //First Come First Serve
            {  
               x.FirstFit();
//            avgscore.put("firstfit",x.FirstFit());
            System.out.println("To try different algorithms press 1 , to exit press 0");
           
              while (!input.hasNextInt()) {
        System.out.println("That's not a number,please enter a number.");
        input.next();
    }
         
    y = input.nextInt();
            while(0>y || y>1 ){
         System.out.println("That's an invalid input,please enter 1 to try different algorithms or press 0 to exit");
         y=input.nextInt();
     }
            if(y==1){
                menu();
                
            } else if(y==0){System.out.println("Goodbye! ");
            
                
            }
            break;
            }
            case 2:
            {  
                x.bestfit();
//                avgscore.put("bestfit",x.bestfit());
                System.out.println("To try different algorithms press 1 , to exit press 0");
                  while (!input.hasNextInt()) {
        System.out.println("That's not a number,please enter a number.");
        input.next();
    }
         
    y = input.nextInt();
          while(0>y || y>1 ){
         System.out.println("That's an invalid input,please enter 1 to try different algorithms or press 0 to exit");
         y=input.nextInt();
     }
            if(y==1){
                menu();
                
            } else if(y==0){System.out.println("Goodbye! ");
            
                
            }
            break;
            }
            case 3:
            {  
                x.worstfit();
//               avgscore.put("worstfit",x.worstfit());
                System.out.println("To try different algorithms press 1 , to exit press 0");
             while (!input.hasNextInt()) {
        System.out.println("That's not a number,please enter a number.");
        input.next();
    }
         
    y = input.nextInt();
      while(0>y || y>1 ){
         System.out.println("That's an invalid input,please enter 1 to try different algorithms or press 0 to exit");
         y=input.nextInt();
     }
            if(y==1){
                menu();
                
            } else if(y==0){System.out.println("Goodbye! ");
            
                
            }
            break;
            }
            case 4:
            {
                x.compare();
                System.out.println("To try different algorithms press 1 , to exit press 0");
             while (!input.hasNextInt()) {
        System.out.println("That's not a number,please enter a number.");
        input.next();
    }
         
    y = input.nextInt();
      while(0>y || y>1 ){
         System.out.println("That's an invalid input,please enter 1 to try different algorithms or press 0 to exit");
         y=input.nextInt();
     }
            if(y==1){
                menu();
                
            } else if(y==0){System.out.println("Goodbye! ");
            
                
            }
                
            break;
            }
            case 5:
            {  
                x.start();
                menu();
                
                
            }
        }
    
    }
  
    
    

        
//    void show(){
//        System.out.println(avgscore.get("firstfit"));
//        System.out.println(avgscore.get("bestfit"));
//        System.out.println(avgscore.get("worstfit"));
//    }

    
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Welcome to Memory allocation simulation application");
        
MemoryAlloc x=new MemoryAlloc();
x.menu();


}
}
