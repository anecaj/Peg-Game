package PegGame;

import java.io.IOException;
import java.util.Scanner;

/**
 * The first part of the project! Plays a peg game from a given filename
 * @author team 05
 */
public class Project3Main {
    public static void main(String args[]) throws PegGameException{
        Scanner scan = new Scanner(System.in);
        boolean Ready = false;  // used for the file input. 

        String boardType = "o";

        System.out.println("What type of board do you want? T for triangle, R for rectangle");
        boardType = scan.nextLine().toLowerCase();

        // Create the board and game triangle, both intialized with null values
        Board gameBoard = null;
        // Prompt user for a file to use as the game board
        System.out.println("Enter a filename: ");
        while(!Ready){
            String filename = scan.nextLine();
            
            // if the file exists; initialize the game board with the data in the file
            try{
                if (boardType.equals("t")) {
                    gameBoard = PegGameTriReader.readFile(filename);
                } else {
                    gameBoard = PegGameFileReader.readFile(filename);
                }
                Ready = true;
                //scan.close();   // close the scanner
            } catch (IOException E){
                System.err.println("Enter a valid board to load in...");
            }
            // repeat until a valid filename has been entered
        }
        //scan.close();

        if (boardType.equals("t")) {
            PegGameTri game_Tri = new PegGameTri(gameBoard);
            CommandLineInterface.Play(game_Tri, scan);
        } else {
            PegGameRect game_sq = new PegGameRect(gameBoard);
            CommandLineInterface.Play(game_sq, scan);
        }

        // play the game using the command line interface
        scan.close();
    }   
}
