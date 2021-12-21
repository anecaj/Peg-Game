package PegGame;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A class to play a peg game thats in the shape of a rectangle
 * @author team 05
 */
public class PegGameRect implements PegGame {
    private Board board;
    private GameState gameState;

    public PegGameRect(int length, int width) {
        this.board = new Board(length, width);
        this.gameState = GameState.NOT_STARTED;
    }

    public PegGameRect(Board board) {
        this.board = board;
        this.gameState = GameState.NOT_STARTED;
    }

    public PegGameRect(Board board, GameState state) {
        this.board = board;
        this.gameState = state;
    }

    /**
     * checks the game state of the board
     * Changes it if theres a stale mate or if you win
     */
    private void checkGameState() {
        if (getPossibleMoves().size() == 0) {
            int pegsLeft = 0;
            for (int i = 0; i < board.getLength(); i++) {
                for (int j = 0; j < board.getWidth(); j++) {
                    if (board.hasPeg(new Location(i, j))) {
                        pegsLeft++;
                    }
                }
            }

            if (pegsLeft == 1) {
                gameState = GameState.WON;
            } else {
                gameState = GameState.STALE_MATE;
            }
        }
    }

    /**
     * Gets all the possible moves
     * @return
     */
    @Override
    public Collection<Move> getPossibleMoves() {
        //This is a slightly optimized version of the algorithm we came up with.
        //It does the same thing, but instead of doing several if elses, it loops through and
        //changes the modifier based on a list. Its just easier to write than a bunch of nested
        //if elses
        //If you dont understand this PLEASE ask me and I'll be happy to explain.

        //holds all the possible moves
        Collection<Move> possibleMoves = new ArrayList<>();

        //These will modify the row and col
        //so instead of saying "if (start_row + 2 == empty_space) {} else if (start_row - 2 == empty_space)"
        //I can just say:
        // for (i in range(2)):
        //      if (start_row + rowMod[i] == empty_space) {}
        //in the first run through of the loop rowMod[i] == 2, and the second rowMod[i] is -2
        int[] rowMods = {2, -2, 0, 0, 2, 2, -2, -2};
        int[] colMods = {0, 0, 2, -2, 2, -2, 2, -2};

        //Same as above, but these will be used to see if theres a peg inbetween
        int[] pegRowMods = {1, -1, 0, 0, 1, 1, -1, -1};
        int[] pegColMods = {0, 0, 1, -1, 1, -1, 1, -1};

        //the board. It isnt and shouldnt be changed
        String[][] gameBoard = board.getBoard();

        //loops through the rows
        for (int row = 0; row < board.getLength(); row++) {
            //System.out.println("1 In first for-loop");
            //loops through the cols
            for (int col = 0; col < board.getWidth(); col++) {
                //System.out.println("2 In second for-loop");
                //if the current space on the board is empty, just break and go onto
                //the next space
                if (gameBoard[row][col].equals("-")) {
                    continue;
                } else {
                    //makes the start location obj
                    Location startLocation = new Location(row, col);

                    //goes through all 8 possible moves
                    //see above for more details
                    for (int i = 0; i < 8; i++) {
                        //the new modded coords
                        int newRow = row + rowMods[i];
                        int newCol = col + colMods[i];

                        //these if statments can be combined, but for the sake of readability
                        //Im keeping them seperate. I will combine them once everybodys had a chance
                        //to read and understand them.

                        //checks if the loaction we're trying to get is in the board
                        if ((newRow < board.getLength()) && (newCol < board.getWidth()) && (newRow >= 0) && (newCol >= 0)) {

                            //checks if the location we're trying to get to is empty
                            if (gameBoard[newRow][newCol].equals("-")) {

                                //checks if theres a peg inbetween
                                if (gameBoard[row + pegRowMods[i]][col + pegColMods[i]].equals("o")) {
                                    //makes a new move and adds it to the board
                                    Location endLocation = new Location(newRow, newCol);
                                    Move move = new Move(endLocation, startLocation);

                                    possibleMoves.add(move);
                                }
                            }
                        }
                    }
                }
            }
        }

        return possibleMoves;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public String toString(){
        return board.toString();
    }

    /**
     * Make the move on the board. Returns true if the move is valid; otherwise, returns false
     */
    public boolean makeMove(Move move){
        // if the first location has a peg, the move may be possible.
        //if(board.hasPeg(move.getFrom())){
        // search if a move is in the possible moves
        Collection<Move> moves = getPossibleMoves();
        for(Move mv : moves){
            if(mv.equals(move)){
                board.addPeg(move.getFrom());

                // if there is a peg between the new move and another peg, remove the middle peg from the board.
                board.removePeg(((move.getFrom().getRow() + move.getTo().getRow())/2) , (move.getTo().getCol() + move.getFrom().getCol())/2);
                
                board.removePeg(move.getTo());  // removes the beginning peg

                checkGameState();
                return true;
            }
        }

        checkGameState();
        return false; 
    }

    public PegGame deepCopy() {
        PegGame deepCopy = new PegGameRect(new Board(board), gameState);
        return deepCopy;
    }
}
