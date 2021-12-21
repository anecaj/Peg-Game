package PegGame;

import java.io.IOException;
import java.util.Scanner;

/**
 * The first part of the project! Plays a peg game from a given filename
 * @author team 05
 */
public class Project1Main {
    public static void main(String args[]) throws PegGameException{
        Scanner scan = new Scanner(System.in);
        boolean Ready = false;  // used for the file input. 

        // Create the board and game rectangle, both intialized with null values
        Board gameBoard = null;
        PegGameRect gameRect = null;
        // Prompt user for a file to use as the game board
        System.out.println("Enter a filename: ");
        while(!Ready){
            String filename = scan.nextLine();
            
            // if the file exists; initialize the game board with the data in the file
            try{
                gameBoard = PegGameFileReader.readFile(filename);
                Ready = true;
                //scan.close();   // close the scanner
            } catch (IOException E){
                System.err.println("Enter a valid board to load in...");
            }
            // repeat until a valid filename has been entered
        }
        //scan.close();

        gameRect = new PegGameRect(gameBoard);

        // play the game using the command line interface
        CommandLineInterface.Play(gameRect, scan);
        scan.close();
    }   
}
