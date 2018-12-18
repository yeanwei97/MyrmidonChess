import java.awt.Image;
import java.util.ArrayList;

public class Chevron extends Piece{

    public Chevron(String playerID, String pieceID, int x, int y, String type, Image img) {
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
        int place = 2;
        
        //topright 
        for(int count = 1; count <= 2 ; count++) 
            {
                nextY = y - place;
                nextX = x - count;
                if (count ==2)
                {
                    nextX = x +1;
                }
                if(nextY >= 0 && nextY <= 5) 
                {
                    if (nextX >= 0 && nextX<=6)
                    {
                        upper.add(new Position(nextX, nextY));
                    }
                }
        }
        
        //bottomright
        place = 2;
        for(int count = 1; count <= 2; count++) {
            
            nextX = x - place ;
            nextY = y - count;
            if (count ==2)
            {
                nextY = y + 1;
            }
            if(nextY >= 0 && nextY <= 5) {
                if (nextX >= 0 && nextX<=6)
                {
                    bottom.add(new Position(nextX, nextY));
                }
            }
        }
      
        //topleft 
        place = 2;
        for(int count = 1; count <= 2; count++) 
        {
            nextX = x + place ;
            nextY = y - count;
            if (count ==2)
            {
                nextY = y + 1;
            }
            if(nextX >= 0 && nextX <= 6)
            {
                if (nextY >=0 && nextY <= 5)
                {
                    left.add(new Position(nextX, nextY));
                }
            }
        }
        
        //bottomleft
        place = 2;
        for(int count = 1; count <= 2 ; count++) 
        {
            nextY = y + place;
            nextX = x - count;
            if (count ==2)
            {
                nextX = x +1;
            }
            if(nextY >= 0 && nextY <= 5) 
            {
                if (nextX >= 0 && nextX<=6)
                {
                    right.add(new Position(nextX, nextY));
                }
            }
        }
        return positionList;
    }
}
