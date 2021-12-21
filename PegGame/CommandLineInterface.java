package PegGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import provided.backtracker.Configuration;

/**
 * Main part of playing a game
 * @author team 05
 */
public class CommandLineInterface {
    /**
     * Plays a peg board game with a given PegGame class
     * @param board
     * @throws PegGameException
     */
    static void Play(PegGame board, Scanner scan) throws PegGameException {
        Scanner scanner = scan;
        String input;

        System.out.println("Hello, welcome to PegGame!");
        System.out.println("You can type help for a list of commands!");
        System.out.println("\n"); //2 blank lines for neat spacing
        System.out.println(board);

        while (true) {
            input = scanner.nextLine();
            input = input.toLowerCase(); //makes it lowercase to accept a larger range of inputs

            if(input.equals("quit")) {
                System.out.println("Are you sure (y/n): ");
                String answer = scanner.nextLine();
                if(answer.equals("y") || answer.equals("Y")){
                    System.out.println("Goodbye!");
                    return;
                }
            } else if(input.equals("help")) {
                System.out.println("If you would like to move a peg: Type move r1 c1 r2 c2 where r stands for row and c statnds for column" );
                System.out.println("If you would like a hint, type hint for an available move");
                System.out.println("If you want to solve the board, type solve");
                System.out.println("Type quit if you would like to exit!");
            
            } else if(input.equals("hint")) {
                //System.out.print("Possible moves are: ");
                //System.out.println(board.getPossibleMoves());

                Configuration moves = PegGameConfig.backtracker(board);
                if (moves != null) {
                    PegGameConfig newMoves = (PegGameConfig) moves;
                    System.out.println(newMoves.getGivenMove(0));
                } else {
                    System.out.println("This board may not be possible...");
                }

            } else if(input.contains("move")){
                List<String> list = new ArrayList<>();
                for(String i : input.split(" ")) {
                    list.add(i);
                }

                // ignore the first argument ("move")
                list.remove(0);

                // if there are four tokens after "move", the input is possible valid
                if(list.size() == 4){
                    // if all the arguments after the "move" token are integers; move the peg from location 1 to location 2
                    try{
                        // cast all the items in the list to ints
                        List<Integer> intList = new ArrayList<>();
                        for(String s : list){
                            int temp = Integer.parseInt(s); // temporary place holder for the current string to int cast
                            intList.add(temp);
                        }

                        // create a to and from location
                        Location from = new Location(intList.get(0), intList.get(1));
                        Location to = new Location(intList.get(2), intList.get(3));

                        // create a new move using the from and to locations
                        Move move = new Move(to, from);

                        boolean didMove = board.makeMove(move);    // perform the move on the board

                        if(didMove){
                            System.out.println(board);  // print the game board after a move has been made
                        } else {
                            System.out.println("Invalid Move!");  // the move is invalid if a move cannot be made
                        }
                    } catch (NumberFormatException E) {
                        // if a token after "move" is not an integer, the move is invalid
                        System.err.println("Invalid Move!");
                    }

                } else {
                    System.out.println("Invalid Move!");    // if there are not four tokens after "move"; the input is invalid
                }
            } else if (input.contains("solve")) {
                PegGame boardCopy = board.deepCopy();
                Configuration config = PegGameConfig.backtracker(board);
                PegGameConfig betterConfig = (PegGameConfig) config;

                if (config != null) {
                    ArrayList<Move> moves = betterConfig.getMovesMade();
                    for (Move nextMove : moves) {
                        System.out.println(nextMove);
                        boardCopy.makeMove(nextMove);
                        System.out.println(boardCopy);
                    }
                    System.out.println("You, well the computer, won!");
                    return;
                } else {
                    System.out.println("This board cannot be solved");
                }

            } else {
                System.out.println("Please type a proper command!");
            }

            if (board.getGameState() == GameState.STALE_MATE) {
                System.out.println("Stale Mate!");
                return;
            } else if (board.getGameState() == GameState.WON) {
                System.out.println("You Win!");
                return;
            }
        } 
    }

    //Game should be played through Project1Main.java!
    //Remember, every time you call a class, even to create it, it executes the main() function
}
