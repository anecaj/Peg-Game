package PegGame;

import java.util.Collection;

/**
 * The interface for all peggames
 * @author team 05
 */
public interface PegGame {
    public default PegGame deepCopy() {
        throw new UnsupportedOperationException("deep copy not implemented");
    }   // make a deep copy of a board
    public Collection<Move> getPossibleMoves();
    public GameState getGameState();
    public default boolean makeMove(Move move) throws PegGameException {
        throw new PegGameException("make move not implemented");
    }
}
