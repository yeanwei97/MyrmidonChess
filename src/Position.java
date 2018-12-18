public class Position{
    
    private int x;
    private int y;
    private Piece p;
    
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        p = null;
    }
    
    public Position(int x, int y, Piece p) {
        this.x = x;
        this.y = y;
        this.p = p;
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
    
    public Piece getPiece() {
        return p;
    }
	
    
    public void setPiece(Piece p) {
        this.p = p;
    }
    
    public void removePiece() {
        p = null;
    }
    
    public String toString() {
        return ("(" + x + "," + y + ")");
    }
}
