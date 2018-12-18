import java.awt.*;

public class PieceFactory{
   
    public static Piece getPiece(String playerID, String pieceID, int x, int y, String type, Image img) {
        if("plus".equalsIgnoreCase(type)) {
            return new Plus(playerID, pieceID, x, y, type, img);
        }
        else if("triangle".equalsIgnoreCase(type)) {
			return new Triangle(playerID, pieceID, x, y, type, img);
        }
        else if("chevron".equalsIgnoreCase(type)) {
            return new Chevron(playerID, pieceID, x, y, type, img);
        }
        else if("sun".equalsIgnoreCase(type)) {
            return new Sun(playerID, pieceID, x, y, type, img);
        }
        return null;
    }
}
