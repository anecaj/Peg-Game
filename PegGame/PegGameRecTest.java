package PegGame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

/**
 * A test for pegGameRect
 * @author team 05
 */

@Testable
public class PegGameRecTest {

    @Test
    public void pegGameTestState() throws PegGameException {

        //We havent implemented make move yet but this will work later!

        //setup
        PegGameRect peg = new PegGameRect(4, 4);

        //invoke
        GameState expected = peg.getGameState();
        GameState actual = GameState.NOT_STARTED;

        //analyze
        assertEquals(expected, actual);
    }

    @Test
    public void pegGameTestPossibleMoves() {

        //setup
        Board board = new Board(5, 5);
        PegGameRect peg = new PegGameRect(board);
        board.addPeg(3, 2);
        board.addPeg(4, 2);

        //invoke
        //I dont know how to predict the moves to assert them
        Collection<Move> a = new ArrayList<>(1);
        a.add(new Move(new Location(2, 2), new Location(4, 2)));
        Collection<Move> b = peg.getPossibleMoves();

        //analyze
        assertEquals(a, b);
    }

    @Test
    public void testDeepCopy() {
        //init
        Board board = new Board(3, 3);
        board.addPeg(new Location(0, 0));
        board.addPeg(new Location(1, 1));
        PegGameRect start = new PegGameRect(board, GameState.IN_PROGRESS);

        //invoke
        PegGameRect copy = (PegGameRect) start.deepCopy();

        //analyze
        Board startBoard = start.getBoard();
        Board copyBoard = copy.getBoard();

        for (int i = 0; i < startBoard.getLength(); i++) {
            for (int j = 0; j < startBoard.getWidth(); j++) {
                Location loc = new Location(i, j);
                if (startBoard.hasPeg(loc) != copyBoard.hasPeg(loc)) {
                    assertEquals(false, true);
                }
            }
        }

        assertEquals(start.getGameState(), copy.getGameState());
    }    
}
