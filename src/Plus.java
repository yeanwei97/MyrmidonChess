import java.awt.Image;
import java.util.ArrayList;
import java.io.Serializable;

public class Plus extends Piece{

    public Plus(String playerID, String pieceID, int x, int y, String type, Image img) {
        super(playerID, pieceID, x, y, type, img);
    }
    
    public ArrayList<ArrayList<Position>> movePattern() {
        ArrayList<ArrayList<Position>> positionList = new ArrayList<ArrayList<Position>>();
        ArrayList<Position> upper = new ArrayList<Position>();
        ArrayList<Position> bottom = new ArrayList<Position>();
        ArrayList<Position> left = new ArrayList<Position>();
        ArrayList<Position> right = new ArrayList<Position>();
        positionList.add(upper);
        positionList.add(bottom);
        positionList.add(left);
        positionList.add(right);
        
        int x = this.getX();
        int y = this.getY();
        int nextX;
        int nextY;
        int place = 1;
        
        //upper 
        for(int count = 0; count < 2; count++) {
            nextY = y - place;
            if(nextY >= 0 && nextY <= 5) 
			{
                upper.add(new Position(x, nextY));
            }
            place++;
        }
        
        //bottom
        place = 1;
        for(int count = 0; count < 2; count++) {
            nextY = y + place;
            if(nextY >= 0 && nextY <= 5) {
                bottom.add(new Position(x, nextY));
            }
            place++;
        }
        
        //left 
        place = 1;
        for(int count = 0; count < 2; count++) {
            nextX = x - place;
            if(nextX >= 0 && nextX <= 6) {
                left.add(new Position(nextX, y));
            }
            place++;
        }
        
        //right
        place = 1;
        for(int count = 0; count < 2; count++) {
            nextX = x + place;
            if(nextX >= 0 && nextX <= 6) {
                right.add(new Position(nextX, y));
            }
            place++;
        }
        
        return positionList;
    }
}
