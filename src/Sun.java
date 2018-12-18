import java.awt.Image;
import java.util.ArrayList;

public class Sun extends Piece{
	
	
    public Sun(String playerID, String pieceID, int x, int y, String type, Image img) {
        super(playerID, pieceID, x, y, type, img);
    }


    public ArrayList<ArrayList<Position>> movePattern() {
        ArrayList<ArrayList<Position>> positionList = new ArrayList<ArrayList<Position>>();
        ArrayList<Position> upper = new ArrayList<Position>();
        ArrayList<Position> bottom = new ArrayList<Position>();
        ArrayList<Position> left = new ArrayList<Position>();
        ArrayList<Position> right = new ArrayList<Position>();
        ArrayList<Position> TopRight = new ArrayList<Position>();
        ArrayList<Position> TopLeft = new ArrayList<Position>();
        ArrayList<Position> BotLeft = new ArrayList<Position>();
        ArrayList<Position> BotRight = new ArrayList<Position>();

        positionList.add(upper);
        positionList.add(bottom);
        positionList.add(left);
        positionList.add(right);
        positionList.add(TopRight);
        positionList.add(TopLeft);
        positionList.add(BotLeft);
        positionList.add(BotRight);

        int x = this.getX();
        int y = this.getY();
        int nextX;
        int nextY;
        int place = 1;

        //top
        nextY = y - place;
        if (nextY >= 0 && nextY <= 5) {
            upper.add(new Position(x, nextY));
        }

        //bottom
        place = 1;
        nextY = y + place;
        if (nextY >= 0 && nextY <= 5) {
            bottom.add(new Position(x, nextY));
        }

        //left 
        place = 1;
        nextX = x - place;
        if (nextX >= 0 && nextX <= 6) {
            left.add(new Position(nextX, y));
        }

        //right
        place = 1;
        nextX = x + place;
        if (nextX >= 0 && nextX <= 6) {
            right.add(new Position(nextX, y));
        }

        //topright 
        place = 1;
        nextY = y - place;
        nextX = x + place;
        if (nextY >= 0 && nextY <= 5) {
            if (nextX >= 0 && nextX <= 6) {
                TopRight.add(new Position(nextX, nextY));
            }
        }

        //TopLeft
        place = 1;
        nextX = x - place;
        nextY = y - place;
        if (nextX >= 0 && nextX <= 6) {
            if (nextY >= 0 && nextY <= 5) {
                TopLeft.add(new Position(nextX, nextY));
            }
        }

        //Bottom Left
        place = 1;
        nextX = x - place;
        nextY = y + place;
        if (nextX >= 0 && nextX <= 6) {
            if (nextY >= 0 && nextY <= 5) {
                BotLeft.add(new Position(nextX, nextY));
            }
        }

        //bottomright
        place = 1;
        nextY = y + place;
        nextX = x + place;
        if (nextY >= 0 && nextY <= 5) {
            if (nextX >= 0 && nextX <= 6) {
                BotRight.add(new Position(nextX, nextY));
            }
        }

        return positionList;
    }
}
