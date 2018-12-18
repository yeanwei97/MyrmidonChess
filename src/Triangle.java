import java.awt.Image;
import java.util.ArrayList;

public class Triangle extends Piece{

    public Triangle(String playerID, String pieceID, int x, int y, String type, Image img) {
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
        
        //topright 
        for(int count = 0; count < 2; count++) {
            nextY = y - place;
            nextX = x + place;
            if(nextY >= 0 && nextY <= 5) 
            {
                if (nextX >= 0 && nextX<=6)
                {
                    upper.add(new Position(nextX, nextY));
                }
            }
            place++;
        }
        
        //bottomright
        place = 1;
        for(int count = 0; count < 2; count++) {
            nextY = y + place;
            nextX = x + place;
            if(nextY >= 0 && nextY <= 5) {
                if (nextX >= 0 && nextX<=6)
                {
                    bottom.add(new Position(nextX, nextY));
                }
            }
            place++;
        }
      
        //topleft 
        place = 1;
        for(int count = 0; count < 2; count++) {
            nextX = x - place;
            nextY = y - place;
            if(nextX >= 0 && nextX <= 6)
            {
                if (nextY >=0 && nextY <= 5)
                {
                    left.add(new Position(nextX, nextY));
                }
            }
            place++;
        }
        
        //bottomleft
        place = 1;
        for(int count = 0; count < 2; count++) {
            nextX = x - place;
            nextY = y + place;
            if(nextX >= 0 && nextX <= 6) 
            {
                if (nextY >= 0 && nextY <=5)
                {
                    right.add(new Position(nextX, nextY));
                }
            }
            place++;
        }
        return positionList;
    }
}