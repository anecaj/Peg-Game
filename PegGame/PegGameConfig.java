package PegGame;

import java.util.ArrayList;
import java.util.Collection;

import provided.backtracker.Backtracker;
import provided.backtracker.Configuration;

/**
 * A Configuration of a PegGame that helps search for a solution
 * @author: Team0505
 */

public class PegGameConfig implements Configuration{
    // Board for the current configuration
    PegGame board;
    ArrayList<Move> movesMade;


    public PegGameConfig(PegGame game) {
        this.board = game;
        movesMade = new ArrayList<Move>();
    }

    /**
     * Creates a new configuration with the current board
     * @param board
     */
    public PegGameConfig(PegGameRect board, ArrayList<Move> list){
        this.board = board;
        this.movesMade = list;
    }

    
    /**
     * Creates a new configuration with the current board
     * @param board
     */
    public PegGameConfig(PegGameRect board, Move move){
        this.board = board;
        board.makeMove(move);
    }

    public PegGameConfig(PegGameTri board, ArrayList<Move> list){
        this.board = board;
        this.movesMade = list;
    }

    public PegGameConfig(PegGameTri board, Move move){
        this.board = board;
        board.makeMove(move);
    }

    public ArrayList<Move> getMovesMade() {
        return movesMade;
    }

    /**
     * Gets a move at a given index
     */
    public Move getGivenMove(int i) {
        if (i < movesMade.size() && i >= 0) {
            return movesMade.get(i);
        }
        return null;
    }

    /**
     * Returns a collection of the possible next moves that can be made on the board
     */
    @Override
    public Collection<Configuration> getSuccessors() {
        Collection<Configuration> successors = new ArrayList<>();
        // Configuration currentConfig;
        Collection<Move> possibleMoves = board.getPossibleMoves();
        // get the successors
        // make a deep copy of the board to use as the successor

        // Collection of Configs add a new config for each moveadd the new move to the new configuration for every possible move
        for(Move move : possibleMoves) {
            // make a deep copy of the board to use as the successor
            PegGameTri deepCopy = (PegGameTri) board.deepCopy();

            deepCopy.makeMove(move);
            ArrayList<Move> moveListCopy = new ArrayList<>(movesMade);
            moveListCopy.add(move);
            //Configuration successor = new PegGameConfig(deepCopy, moveListCopy);
            successors.add(new PegGameConfig(deepCopy, moveListCopy));
        }

        return successors;
    }

    /**
     * Checks if the current configuration is valid
     */
    @Override
    public boolean isValid() {
        return true;
    }

    /**
     *  Checks if the solution has been met
     */
    @Override
    public boolean isGoal() {
        return board.getGameState() == GameState.WON;
    }

    @Override
    public String toString() {
        return movesMade.toString() + "\n" + board.toString();
    }

    /**
     * Makes a backtracker and trys to find a solution
     */
    public static Configuration backtracker(PegGame game) {
        PegGameConfig config = new PegGameConfig(game);
        Backtracker bt = new Backtracker(false);
        Configuration solution = bt.solve(config);

        return solution;
    }
}
