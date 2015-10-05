/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 *
 * @author Daniel M. Sanchez
 */
public class Library {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner read = new Scanner(System.in);
        
        Conection conection = new Conection();
        Book book = new Book();
        
        boolean state_conection = conection.conectar();
        
        int option = 7;
        int id_book = 0;
        String name_book = "";
        
        
        if(state_conection){
            while(option != 0){
                System.out.println("\t\tMENU");
                System.out.println("\t1) Insertar");
                System.out.println("\t2) Mostrar");
                System.out.println("\t3) Modificar");
                System.out.println("\t4) Eliminar");
                System.out.println("\n\t0) Salir");
                try{
                    option = read.nextInt();
                }catch(InputMismatchException | NumberFormatException e){
                    System.out.println("Key a character numeric");
                    break;
                }
                switch(option){
                    case 1:
                        System.out.println("Id Libro: ");
                        id_book = read.nextInt();
                        System.out.println("Name Book");
                        name_book = read.next();
                        
                        if(book.create(id_book, name_book) == 1)
                            System.out.println("\n\t\t--- Added Book Succesful!\n");
                        else
                            System.out.println("Can't Added Book Succesful!");
                        
                        break;
                    case 2:
                        String[] books = book.all();
                        
                        System.out.println("\n\t\tBOOKS LIST");
                        System.out.println("--------------------------------------");
                        for (String book_ : books)
                            System.out.println(book_);
                        System.out.println("--------------------------------------");
                        System.out.println("\n");
                        
                        break;
                    case 3: 
                        System.out.println("\n\t\tUPDATE BOOK\n");
                        System.out.println("Ingrese el Id del libro: ");
                        id_book = read.nextInt();

                        int data_length = book.find(id_book);

                        String[] data_book = book.data();
                        if(data_length > 0){
                            System.out.println("\nName: (current "+ data_book[1] +")");
                            name_book = read.next();

                            if(book.update(id_book, name_book) > 0)
                                System.out.println("\n\t\tBook Update Succesfull!\n");
                            else
                                System.out.println("\n\t\tCan't Book Update Succesfull!\n");
                        }else{
                            System.out.println("\n\t\tThe book isn't exists\n");
                        }

                        break;
                    case 4: 
                        System.out.println("\n\t\tDELETE BOOK\n");
                        System.out.println("Ingrese el Id del libro: ");
                        id_book = read.nextInt();

                        int res = book.find(id_book);

                        if(res > 0){                                
                            if(book.delete(id_book) > 0)
                                System.out.println("\n\t\tBook Delete Succesfull!\n");
                            else
                                System.out.println("\n\t\tCan't Book Delete Succesfull!\n");
                        }else{
                            System.out.println("\n\t\tThe book isn't exists\n");
                        }

                        break;
                }
            }
        }
        
    }
    
}
