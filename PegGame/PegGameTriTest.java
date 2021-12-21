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
public class PegGameTriTest {

    @Test
    public void pegGameTestState() throws PegGameException {

        //We havent implemented make move yet but this will work later!

        //setup
        PegGameTri peg = new PegGameTri(4);

        //invoke
        GameState expected = peg.getGameState();
        GameState actual = GameState.NOT_STARTED;

        //analyze
        assertEquals(expected, actual);
    }

    @Test
    public void pegGameTestPossibleMoves() {

        //setup
        Board board = new Board(3);
        PegGameTri peg = new PegGameTri(board);
        board.addPeg(1, 1);
        board.addPeg(2, 2);
        System.out.println(board);

        //invoke
        //I dont know how to predict the moves to assert them
        Collection<Move> a = new ArrayList<>(1);
        a.add(new Move(new Location(0, 0), new Location(2, 2)));
        Collection<Move> b = peg.getPossibleMoves();

        //analyze
        assertEquals(a, b);
    }

    @Test
    public void testDeepCopy() {
        //init
        Board board = new Board(3);
        board.addPeg(new Location(0, 0));
        board.addPeg(new Location(1, 1));
        PegGameTri start = new PegGameTri(board, GameState.IN_PROGRESS);

        //invoke
        PegGameTri copy = (PegGameTri) start.deepCopy();

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
