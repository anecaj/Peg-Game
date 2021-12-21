package PegGame;

/**
 * A class to represent a move on a pegboard
 * @author team 05
 */
public class Move {
    private Location from;
    private Location to;

    public Move(Location from, Location to){
        this.from = from;
        this.to = to;
    }

    public Location getFrom(){
        return from;
    }

    public Location getTo(){
        return to;
    }

    @Override
    public String toString(){
        return "[" + to.getRow() + " " + to.getCol() + "]" + "[" + from.getRow() + " " + from.getCol() + "]";
    }

    @Override 
    public boolean equals(Object o){
        if(o instanceof Move){
            Move obj = (Move) o;
            return this.from.equals(obj.from) && this.to.equals(obj.to);
        }
        return false;
    }

    // We will need a move method that removes a piece if it is imbetween a pieces to and from
    // if a piece is imbetween to and from (row+|move row| and col + |move col|), then remove the piece between the to and from
    // can determine the direction with (move direction % -1); if 0, negative, else positive
    // the imbetween piece will be determined by (row + (direction row) 1, col + (direction col) 1) 
}
