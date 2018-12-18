import java.awt.Image;
import java.util.ArrayList;

public abstract class Piece{
    protected String playerID;
    protected String pieceID;
    protected int x; //need for movable
    protected int y;
    protected String type; //need for piece factory
    protected int countStep = 0;
    protected Image imgIcon;
    
    public Piece(String playerID, String pieceID, int x, int y, String type, Image img) {
        this.playerID = playerID;
        this.pieceID = pieceID;
        this.x = x;
        this.y = y;
        this.type = type;
        this.imgIcon = img;
    }
    
    public String getPlayerID() {
        return playerID;
    }
    
    public void setPlayerID(String id) {
        playerID = id;
    }
    
    public String getPieceID() {
        return pieceID;
    }
    
    public void setPieceID(String id) {
        pieceID = id;
    }
    
    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public int getCountStep() {
        return countStep;
    }
    
    public void setCountStep(int c) {
        countStep = c;
    }
    
    public Image getImgIcon() {
        return imgIcon;
    }
    
    public void setImgIcon(Image img) {
        this.imgIcon = img;
    }
    
    public abstract ArrayList<ArrayList<Position>> movePattern();
    
}
