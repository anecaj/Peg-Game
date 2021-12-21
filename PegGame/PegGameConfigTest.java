package PegGame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

/**
 * A test for PegGameConfig
 * @author team 05
 */
@Testable
public class PegGameConfigTest {
    @Test
    public void testBacktrackerRect() {
        // setup
        try {
            PegGame game = new PegGameRect(PegGameFileReader.readFile("data/1_4.txt"));

            PegGameConfig testConfig = (PegGameConfig) PegGameConfig.backtracker(game);

            // invoke
            ArrayList<Move> actual = testConfig.getMovesMade();
            ArrayList<Move> expected = new ArrayList<>();
            expected.add(new Move(new Location(0,3), new Location(0,1)));
            expected.add(new Move(new Location(0,0), new Location(0, 2)));

            // analyze
            assertEquals(expected, actual);
        } catch (IOException e) {
            return;
        }
    }
}
