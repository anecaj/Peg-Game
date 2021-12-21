package PegGame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

/**
 * A test for the board
 * @author team 05
 */

@Testable
public class BoardTest {

    @Test
    public void boardTestLength() {
        // setup
        Board board = new Board(4, 4);
        board.makeRectBoard(4, 4);

        Board emptyBoard = new Board(4, 4);
        emptyBoard.makeRectBoard(4, 4);

        // invoke
        int a = board.getLength();
        int b = emptyBoard.getLength();

        // analyze
        assertEquals(a, b);
    }
    
    @Test
    public void notEqualBoard() {
        // setup
        Board board = new Board(5, 5);
        board.makeRectBoard(5, 5);
        
        // invoke
        board.addPeg(4, 1);
        String a = board.toString();

        Board emptyBoard = new Board(5, 5);
        String b = emptyBoard.toString();

        // analyze
        Assert.assertNotSame(a, b);
    }


}
