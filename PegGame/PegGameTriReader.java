package PegGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reads files to make a peg game from the given file
 * @author team 05
 */
public class PegGameTriReader {
    
    public static Board readFile(String filename) throws IOException {
        FileReader fr = new FileReader(filename);
        BufferedReader reader = new BufferedReader(fr);

        // get the first line
        String line = reader.readLine();
        int x = Integer.parseInt(line); // this is the number of rows
        

        // grab the first row of the board (and determine the number of columns)
        String nextLine = reader.readLine();

        Board triboard = new Board(x); //one param makes it triangle
        
        //System.out.println(board);
        String[] tokens = nextLine.split("");
        for(int col = 0; col < nextLine.length(); col++){
            if(tokens[col].equals("o")){
                triboard.addPeg(0, col); //0,0 0,1
            }
        }
        //boolean done = false;

        // get the rest of the rows and cols in rows here
        for(int row = 0; row < x; row++){
            if(row != 0){
                nextLine = reader.readLine();
                tokens = nextLine.split("");
                for(int col = 0; col < nextLine.length(); col++){
                    if(tokens[col].equals("o")){
                        triboard.addPeg(row, col);
                    }
                }
            }
        }

        fr.close();
        reader.close();

        return triboard;

    }
}
