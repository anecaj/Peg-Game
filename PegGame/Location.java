package PegGame;

/** 
 * A class to represent a location on a pegboard
 * @author team 05
 */
public class Location{
    private int row;
    private int col;

    public Location(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Location){
            Location obj = (Location) o;
            return this.row == obj.row && this.col == obj.col;
        }
        return false;
    }

    @Override
    public String toString() {
        return row + " " + col;
    }
}