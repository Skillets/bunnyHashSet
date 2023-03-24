package edu.frostburg.cosc310.p01;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


/**
 * This Program is built to help the BOBCAT initiative and to practice HashSets for COSC-310 
 * This program reads from a text file and performs various operations, including adding, removing, searching, mass extinction, etc.
 * Additionally, this program makes decisions based on user inputs, which are constantly checked as time goes on.
 * 
 * @author Joshua Prathios
 *
 */

public class Jmpratchios0HashSet {
    
    public static void main(String[] args) { 
        
        JPHashSet<String> JPBunnySet = new JPHashSet();
        double loadFactor = .8;
        
        System.out.println("Greetings, BOBCAT agent. This catalogue was created and compiled by " + JPBunnySet.myName());
        System.out.println("At your fingertips is the name of every single known bunny.");
        System.out.println("Lets get rolling\n");
        
        try {
            String fileName = "BunnyNames.txt";
            FileReader fileReader = new FileReader(fileName);
            
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            
            while((line = bufferedReader.readLine()) != null) {
               
                JPBunnySet.add(line);
            }
            bufferedReader.close();
            
        } catch(IOException e) {}
        System.out.println("Available Commands:");
        System.out.println("Add");
        System.out.println("Delete");
        System.out.println("Search");
        System.out.println("Stats");
        System.out.println("Commands");
        System.out.println("Type exit to end the program");
        
        Scanner uInput = new Scanner(System.in);
        
        System.out.println("\nPlease Enter a Command:");
        String choice;
        boolean end = false;
    
        do { 
            
            
            /*
            Below are conditionals for the user input.
            contains some version of STOP or END -> ends the program.
            contains ADD -> asks the user to add a new name to database. Returns true if successful, false if name exists.
            contains DEL -> asks the user for a name to delete, and then removes that bunny from existence. returns false if bunny is not in database.
            contains SEARCH or NAME -> searches for the given name. If found in database, returns true. returns false otherwise.
            Contains STATS -> returns generic stats for the table.
            */
            choice = uInput.nextLine();
            boolean success = false;
            
            if(choice.toUpperCase().contains("EXIT") || choice.toUpperCase().contains("END") || choice.toUpperCase().contains("STOP")) { 
                end = true;
                
                
            } else if(choice.toUpperCase().contains("ADD")) {
                System.out.println("Enter name of the bunny you would like to add to the database");
                choice = uInput.nextLine();
                success = JPBunnySet.add(choice);
                if(success) {
                    System.out.println("The name " + choice + " has been successfully added");
                } else {
                    System.out.println("The name " + choice + " is already in the database!");
                }
                
                
            } else if(choice.toUpperCase().contains("DEL")) {
                System.out.println("Enter the bunny you wish to purge");
                choice = uInput.nextLine();
                success = JPBunnySet.remove(choice);
                if(success) {
                    System.out.println("Bunny entry for " + choice + " has been successfully purged");
                } else {
                    System.out.println("The name " + choice + " does not exist in this database");
                }
                
                
            } else if(choice.toUpperCase().contains("SEARCH") || choice.toUpperCase().contains("NAME") || choice.toUpperCase().contains("FIND")) {
                System.out.println("Enter the name you would like to check for");
                choice = uInput.nextLine();
                success = JPBunnySet.find(choice);
                if(success) {
                    System.out.println("the name " + choice + " exists in the database");
                } else {
                    System.out.println("The name " + choice + " is not in the database");
                }
                
                
            } else if(choice.toUpperCase().contains("STATS")) {
                System.out.println("The size of the table is: " + JPBunnySet.tableSize());
                System.out.println("The number of elements is: " + JPBunnySet.size() );
                System.out.println("The load factor is: " + loadFactor);
                
            } else if(choice.toUpperCase().contains("?") || choice.toUpperCase().contains("COMMAND") ){
                System.out.println("Available Commands:");
                System.out.println("Add");
                System.out.println("Delete");
                System.out.println("Search");
                System.out.println("Stats");
                System.out.println("Commands");
                System.out.println("Type exit to end the program");
            }
            
            System.out.println("Enter a command"); //simple pop up for the next user choice.
            
        } while(!end);
        
       
    }
}

