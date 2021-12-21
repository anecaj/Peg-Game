package PegGame;

/**
 * A class for  board
 * Add new methods as needed, but this should be generally what we need
 * @author team 05
 */
public class Board {
    private String[][] board;
    private int length; //rows
    private int width; //cols

    /**
     * It default makes a rectangle board with the given dimentions.
     */
    public Board(int length, int width) {
        board = makeRectBoard(length, width);
        this.length = length;
        this.width = width;
    }

    public Board(int length) {
        board = makeTriBoard(length);
        this.length = length; //height
        this.width = 0;
    }

    public Board(Board board) {
        length = board.getLength();
        width = board.getWidth();
        this.board = makeRectBoard(this.length, this.width);

        for (int i = 0; i < board.getLength(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                Location loc = new Location(i, j); 
                if (board.hasPeg(loc)) {
                    addPeg(i, j);
                }
            }
        }
    }

    /**
     * Makes a rectangle board.
     * @param length
     * @param width
     * @return
     */
    public String[][] makeRectBoard(int length, int width) {
        String[][] board = new String[length][width];

        //Fills the board with empty slots (-)
        for (int row = 0; row < length; row++) {
            for (int col = 0; col < width; col++) {
                board[row][col] = "-";
            }
        }

        return board;
    }

    /**
     * Makes a triangle board
     */
    public String[][] makeTriBoard(int height) {
        String[][] board = new String[height][];
        for (int i = 0; i < height; i++) {
            String[] row = new String[i + 1];
            for (int j = 0; j < row.length; j++) {
                row[j] = "-";
            }
            board[i] = row;
        }

        return board;
    }

    public String[][] getBoard() {
        return board;
    }

    public int getLength() {
        return length;
    }

    public int getRowLength(int row){
        return board[row].length;
    }

    public int getWidth() {
        return width;
    }

    /**
     * Checks if the board has a peg at the location board[row][col]. Returns true if a peg is there; otherwise, returns false.
     * @param row
     * @param col
     * @return
     */
    public boolean hasPeg(Location location){
        int row = location.getRow();
        int col = location.getCol();

        // there is no peg if board[row][col] has a "-"
        if(board[row][col].equals("-")){
            return false;
        }
        return true;
    }

    /**
     * Adds a peg to a given location
     * @param row
     * @param col
     */
    public void addPeg(int row, int col) {
        board[row][col] = "o";
    }

    public void addPeg(Location loc) {
        int row = loc.getRow();
        int col = loc.getCol();

        board[row][col] = "o";
    }

    /**
     * Removes a peg from the board
     */
    public void removePeg(int row, int col) {
        board[row][col] = "-";
    }

    public void removePeg(Location loc) {
        int row = loc.getRow();
        int col = loc.getCol();

        board[row][col] = "-";
    }

    @Override
    public String toString() {
        if (width != 0) {
            StringBuilder builder = new StringBuilder();

            //loops through the board (assuming its a rectangular board) to
            //build a nice looking output.
            //should look like (for a 3x3 empty board):
            //   ---
            //   ---
            //   ---
            for(int row = 0; row < length; row++) {
                for(int col = 0; col < width; col++) {
                    builder.append(board[row][col]);
                }
                builder.append("\n");
            }

            return builder.toString();

        //triangle
        } else {
            StringBuilder builder = new StringBuilder();

            //loops through the board (assuming its a rectangular board) to
            //build a nice looking output.
            //should look like (for a 3x3 empty board):
            //     -
            //    - -
            //   - - -
            for(int row = 0; row < length; row++) {
                //makes spaces to make it look triangly
                for (int i = 0; i < (length - 1 - row); i++) {
                    builder.append(" ");
                }

                for(int col = 0; col < board[row].length; col++) {
                    builder.append(board[row][col] + " ");

                }
                builder.append("\n");
            }

            return builder.toString();
        }
    }
}
