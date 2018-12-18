import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.util.*;
import javax.swing.*;
import java.io.FileReader;
import java.io.BufferedReader;

public class Board implements ActionListener {

    //to apply Singleton
    private static Board singleInstance = null;
    private static boolean gameover = false;
    private final int ROWS = 6;
    private final int COLUMNS = 7;
    private JButton[][] btn = new JButton[ROWS][COLUMNS];
    private Position[][] position = new Position[ROWS][COLUMNS];
    private JPanel gameBoard = new JPanel(new GridLayout(6, 7));

    //for btn icon
    private Image plusRed;
    private Image plusBlue;
    private Image triangleRed;
    private Image triangleBlue;
    private Image chevronRed;
    private Image chevronBlue;
    private Image sunRed;
    private Image sunBlue;
	
    private String winner;
    private int oldX;
    private int newX;
    private int oldY;
    private int newY;
    private Piece movePiece;
    
    private boolean movable = true;
    private ArrayList<Position> cyanPosition = new ArrayList<Position>();

    //Player
    private PlayerContext playerTurn = new PlayerContext();
    private PlayerOneState p1 = new PlayerOneState();

    private Board() {
        initializeBoard();
    }//end of constructor

    //implement Singleton
    public static Board getInstance() {
        if (singleInstance == null) {
            singleInstance = new Board();
        }
        return singleInstance;
    }

    //done by Lee Yean Wei
    public void initializeBoard() {
        playerTurn.setState(p1); //player one will be the first player to move piece
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                btn[i][j] = new JButton();
                btn[i][j].setBackground(Color.white);
                setPiece(i, j); //set pieces to initial places
                btn[i][j].putClientProperty("row", i); //set client property to identifity row num
                btn[i][j].putClientProperty("column", j); //set client property to identifity column num
                btn[i][j].addActionListener(this); //for moving piece
                btn[i][j].addComponentListener(new ComponentAdapter() {
                    //to resize window, when window resize, image icon resize
                    @Override
                    public final void componentResized(ComponentEvent e) {
                        JButton btn = (JButton)e.getSource();
                        if(btn.getIcon() != null) {
                            int column = (int) btn.getClientProperty("column");
                            int row = (int) btn.getClientProperty("row");
                            Piece p = position[row][column].getPiece();
                            int w = btn.getWidth();
                            int h = btn.getHeight();
                            int size =  Math.min(w, h)-20;
                            Image img = p.getImgIcon();
                            img = img.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
                            btn.setIcon(new ImageIcon(img));
                        }
                    }
                });
                btn[i][j].setFocusPainted(false); //remove image icon border
                gameBoard.add(btn[i][j]); //add all the buttons
            }
        }
    }//end of initialize board

    public JPanel getGameBoard() {
        return gameBoard; //singleton, to return game board to Chess class
    }
    
    //done by Lee Yean Wei
    public void setPiece(int y, int x) {
        plusRed = new ImageIcon(this.getClass().getResource("plusRed.png")).getImage();
        plusRed = plusRed.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
        triangleRed = new ImageIcon(this.getClass().getResource("triangleRed.png")).getImage();
        triangleRed = triangleRed.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
        chevronRed = new ImageIcon(this.getClass().getResource("chevronRed.png")).getImage();
        chevronRed = chevronRed.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
        sunRed = new ImageIcon(this.getClass().getResource("sunRed.png")).getImage();
        sunRed = sunRed.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);

        plusBlue = new ImageIcon(this.getClass().getResource("plusBlue.png")).getImage();
        plusBlue = plusBlue.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
        triangleBlue = new ImageIcon(this.getClass().getResource("triangleBlue.png")).getImage();
        triangleBlue = triangleBlue.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
        chevronBlue = new ImageIcon(this.getClass().getResource("chevronBlue.png")).getImage();
        chevronBlue = chevronBlue.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
        sunBlue = new ImageIcon(this.getClass().getResource("sunBlue.png")).getImage();
        sunBlue = sunBlue.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);

        PieceFactory pieceFactory = new PieceFactory();

        if (y == 5 && x == 0) {
            Piece p = pieceFactory.getPiece("Player 1", "Plus1", 0, 5, "plus", plusRed);
            position[y][x] = new Position(x, y, p);
            setImageIconSize(x, y, p);
            //btn[y][x].setIcon(new ImageIcon(p.getImgIcon()));
        } else if (y == 5 && x == 6) {
            Piece p = pieceFactory.getPiece("Player 1", "Plus2", 6, 5, "plus", plusRed);
            position[y][x] = new Position(x, y, p);
            setImageIconSize(x, y, p);
            //btn[y][x].setIcon(new ImageIcon(p.getImgIcon()));
        } else if (y == 5 && x == 1) {
            Piece p = pieceFactory.getPiece("Player 1", "Triangle1", 1, 5, "triangle", triangleRed);
            position[y][x] = new Position(x, y, p);
            setImageIconSize(x, y, p);
            //btn[y][x].setIcon(new ImageIcon(p.getImgIcon()));
        } else if (y == 5 && x == 5) {
            Piece p = pieceFactory.getPiece("Player 1", "Triangle2", 5, 5, "triangle", triangleRed);
            position[y][x] = new Position(x, y, p);
            setImageIconSize(x, y, p);
            //btn[y][x].setIcon(new ImageIcon(p.getImgIcon()));
        } else if (y == 5 && x == 2) {
            Piece p = pieceFactory.getPiece("Player 1", "Chevron1", 2, 5, "chevron", chevronRed);
            position[y][x] = new Position(x, y, p);
            setImageIconSize(x, y, p);
            //btn[y][x].setIcon(new ImageIcon(p.getImgIcon()));
        } else if (y == 5 && x == 4) {
            Piece p = pieceFactory.getPiece("Player 1", "Chevron2", 4, 5, "chevron", chevronRed);
            position[y][x] = new Position(x, y, p);
            setImageIconSize(x, y, p);
            //btn[y][x].setIcon(new ImageIcon(p.getImgIcon()));
        } else if (y == 5 && x == 3) {
            Piece p = pieceFactory.getPiece("Player 1", "Sun1", 3, 5, "sun", sunRed);
            position[y][x] = new Position(x, y, p);
            setImageIconSize(x, y, p);
            //btn[y][x].setIcon(new ImageIcon(p.getImgIcon()));
        } else if (y == 0 && x == 0) {
            Piece p = pieceFactory.getPiece("Player 2", "Plus3", 0, 0, "plus", plusBlue);
            position[y][x] = new Position(x, y, p);
            setImageIconSize(x, y, p);
            //btn[y][x].setIcon(new ImageIcon(p.getImgIcon()));
        } else if (y == 0 && x == 6) {
            Piece p = pieceFactory.getPiece("Player 2", "Plus4", 6, 0, "plus", plusBlue);
            position[y][x] = new Position(x, y, p);
            setImageIconSize(x, y, p);
            //btn[y][x].setIcon(new ImageIcon(p.getImgIcon()));
        } else if (y == 0 && x == 1) {
            Piece p = pieceFactory.getPiece("Player 2", "Triangle3", 1, 0, "triangle", triangleBlue);
            position[y][x] = new Position(x, y, p);
            setImageIconSize(x, y, p);
            //btn[y][x].setIcon(new ImageIcon(p.getImgIcon()));
        } else if (y == 0 && x == 5) {
            Piece p = pieceFactory.getPiece("Player 2", "Triangle4", 5, 0, "triangle", triangleBlue);
            position[y][x] = new Position(x, y, p);
            setImageIconSize(x, y, p);
            //btn[y][x].setIcon(new ImageIcon(p.getImgIcon()));
        } else if (y == 0 && x == 2) {
            Piece p = pieceFactory.getPiece("Player 2", "Chevron3", 2, 0, "chevron", chevronBlue);
            position[y][x] = new Position(x, y, p);
            setImageIconSize(x, y, p);
            //btn[y][x].setIcon(new ImageIcon(p.getImgIcon()));
        } else if (y == 0 && x == 4) {
            Piece p = pieceFactory.getPiece("Player 2", "Chevron4", 4, 0, "chevron", chevronBlue);
            position[y][x] = new Position(x, y, p);
            setImageIconSize(x, y, p);
            //btn[y][x].setIcon(new ImageIcon(p.getImgIcon()));
        } else if (y == 0 && x == 3) {
            Piece p = pieceFactory.getPiece("Player 2", "Sun2", 3, 0, "sun", sunBlue);
            position[y][x] = new Position(x, y, p);
            setImageIconSize(x, y, p);
            //btn[y][x].setIcon(new ImageIcon(p.getImgIcon()));
        } else {
            position[y][x] = new Position(x, y);
        }
    }//end of set icon
    
    ////done by Lee Yean Wei, Lee Wing Shum, Chong Foong Loong
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton currentBtn = (JButton) e.getSource(); //when a button is pressed, get infomation of this button
        int column = (int) currentBtn.getClientProperty("column"); //get column of this button
        int row = (int) currentBtn.getClientProperty("row"); //get row of this column
        Piece chosenPiece = position[row][column].getPiece(); //get piece of this position

        if (gameover)
        {
            JOptionPane.showMessageDialog(gameBoard, "Game ended! Winner is "+winner+".\nYou may start new game on File -> New Game.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        else if (movable == true && chosenPiece != null) {
            //if the movable is true and the position contains piece, means the player can pick up piece now
            //check player can only pick up their own pieces
            checkPlayer(column, row, chosenPiece);
        } 
        else if (movable == true && chosenPiece == null) {
            //if the position does not have piece, alert user
            JOptionPane.showMessageDialog(gameBoard, "There is no piece here!", "Warning", JOptionPane.WARNING_MESSAGE);
        } 
	else {
            //if the movable is false, means the player can put down piece
            putDownPiece(column, row);
        }

    }//end of action performed

    //done by Lee Yean Wei
    public void filterMovablePosition(Piece movePiece, ArrayList<ArrayList<Position>> positionList) {
        ArrayList<Position> upper = positionList.get(0);
        for(Position p : upper) {
            if (position[p.getY()][p.getX()].getPiece()== null) {
                btn[p.getY()][p.getX()].setBackground(Color.CYAN);
                cyanPosition.add(p);
            } else if (position[p.getY()][p.getX()].getPiece() != null) {
                if (!position[p.getY()][p.getX()].getPiece().getPlayerID().equals(movePiece.getPlayerID())) {
                    btn[p.getY()][p.getX()].setBackground(Color.CYAN);
                    cyanPosition.add(p);
                }
                if (movePiece.getType() != "chevron") {
                    break;
                }
            }
        }
        
        ArrayList<Position> bottom = positionList.get(1);
        for(Position p : bottom) {
            if (position[p.getY()][p.getX()].getPiece()== null) {
                btn[p.getY()][p.getX()].setBackground(Color.CYAN);
                cyanPosition.add(p);
            } else if (position[p.getY()][p.getX()].getPiece() != null) {
                if (!position[p.getY()][p.getX()].getPiece().getPlayerID().equals(movePiece.getPlayerID())) {
                    btn[p.getY()][p.getX()].setBackground(Color.CYAN);
                    cyanPosition.add(p);
                }
                if (movePiece.getType() != "chevron") {
                    break;
                }
            }
        }
        
        ArrayList<Position> left = positionList.get(2);
        for(Position p : left) {
            if (position[p.getY()][p.getX()].getPiece() == null) {
                btn[p.getY()][p.getX()].setBackground(Color.CYAN);
                cyanPosition.add(p);
            } else if (position[p.getY()][p.getX()].getPiece() != null) {
                if (!position[p.getY()][p.getX()].getPiece().getPlayerID().equals(movePiece.getPlayerID())) {
                    btn[p.getY()][p.getX()].setBackground(Color.CYAN);
                    cyanPosition.add(p);
                }
                if (movePiece.getType() != "chevron") {
                    break;
                }
            }
        }
        
        ArrayList<Position> right = positionList.get(3);
        for(Position p : right) {
            if (position[p.getY()][p.getX()].getPiece() == null) {
                btn[p.getY()][p.getX()].setBackground(Color.CYAN);
                cyanPosition.add(p);
            } else if (position[p.getY()][p.getX()].getPiece() != null) {
                if (!position[p.getY()][p.getX()].getPiece().getPlayerID().equals(movePiece.getPlayerID())) {
                    btn[p.getY()][p.getX()].setBackground(Color.CYAN);
                    cyanPosition.add(p);
                }
                if (movePiece.getType() != "chevron") {
                    break;
                }
            }
        }

        if (movePiece.getType().equals("sun")) {
            ArrayList<Position> topRight = positionList.get(4);
            for(Position p : topRight) {
                if (position[p.getY()][p.getX()].getPiece() == null) {
                    btn[p.getY()][p.getX()].setBackground(Color.CYAN);
                    cyanPosition.add(p);
                } else if (position[p.getY()][p.getX()].getPiece() != null) {
                if (!position[p.getY()][p.getX()].getPiece().getPlayerID().equals(movePiece.getPlayerID())) {
                        btn[p.getY()][p.getX()].setBackground(Color.CYAN);
                        cyanPosition.add(p);
                    }
                }
            }
            
            ArrayList<Position> topLeft = positionList.get(5);
            for(Position p : topLeft) {
                if (position[p.getY()][p.getX()].getPiece() == null) {
                    btn[p.getY()][p.getX()].setBackground(Color.CYAN);
                    cyanPosition.add(p);
                } else if (position[p.getY()][p.getX()].getPiece() != null) {
                if (!position[p.getY()][p.getX()].getPiece().getPlayerID().equals(movePiece.getPlayerID())) {
                        btn[p.getY()][p.getX()].setBackground(Color.CYAN);
                        cyanPosition.add(p);
                    }
                }
            }
            
            ArrayList<Position> bottomLeft = positionList.get(6);
            for(Position p : bottomLeft) {
                if (position[p.getY()][p.getX()].getPiece() == null) {
                    btn[p.getY()][p.getX()].setBackground(Color.CYAN);
                    cyanPosition.add(p);
                } else if (position[p.getY()][p.getX()].getPiece() != null) {
                if (!position[p.getY()][p.getX()].getPiece().getPlayerID().equals(movePiece.getPlayerID())) {
                        btn[p.getY()][p.getX()].setBackground(Color.CYAN);
                        cyanPosition.add(p);
                    }
                }
            }
            
            ArrayList<Position> bottomRight = positionList.get(7);
            for(Position p : bottomRight) {
                if (position[p.getY()][p.getX()].getPiece() == null) {
                    btn[p.getY()][p.getX()].setBackground(Color.CYAN);
                    cyanPosition.add(p);
                } else if (position[p.getY()][p.getX()].getPiece() != null) {
                if (!position[p.getY()][p.getX()].getPiece().getPlayerID().equals(movePiece.getPlayerID())) {
                        btn[p.getY()][p.getX()].setBackground(Color.CYAN);
                        cyanPosition.add(p);
                    }
                }
            }
        }
    }//end of filter

    //done by Lee Yean Wei
    public void clearBtnColor() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                btn[i][j].setBackground(Color.white);
            }
        }
    }//end of clearBtnColor

    //done by Lee Yean Wei
    public void checkPlayer(int column, int row, Piece chosenPiece) {
        //get player id for the chosen piece
        String playerPiece = chosenPiece.getPlayerID();
        //get player id for current player
        String currentPlayer = playerTurn.getStateString();
        //compare
        if (currentPlayer.equalsIgnoreCase(playerPiece)) {
            //if player choose their own piece, they can pick up the pieve
            pickUpPiece(column, row, chosenPiece);
        } else {
            //if they choose other player's piece, show error
            JOptionPane.showMessageDialog(gameBoard, "Now is " + currentPlayer + " turn!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    //done by Lee Yean Wei
    public void pickUpPiece(int column, int row, Piece chosenPiece) {
        //clear available position everytime pick up piece since the piece movement is always different
        cyanPosition.clear();

        oldX = column;
        oldY = row;
        movePiece = chosenPiece;
        //get all the possible movement of this piece
        ArrayList<ArrayList<Position>> positionList = chosenPiece.movePattern();

        //filter out the movement that can actually go, filter out eating own piece
        filterMovablePosition(movePiece, positionList);
        //now movable is false, user can only put down piece
        movable = false;
    }//end of pick up piece

    //done by Lee Yean Wei, Lee Wing Shum, Chong Foong Loong
    public void putDownPiece(int column, int row) {
        newX = column;
        newY = row;

        if ((newX == movePiece.getX()) && (newY == movePiece.getY())) {
            //if they put the piece back to the same place, they can pick another piece again
            movable = true;
            //clear the cyan color
            clearBtnColor();
        } else {
            for(int i = 0; i < cyanPosition.size(); i++) {
                if ((newX == cyanPosition.get(i).getX()) && (newY == cyanPosition.get(i).getY())) {
                    //if they choose to put down on an available position, change the piece
                    chesschange();
                    break;
                } else if ((i == cyanPosition.size()-1) && movable == false) {
                    //if user move to position that is not in the move pattern, alert them
                    JOptionPane.showMessageDialog(gameBoard, "You cannot move here!", "Warning", JOptionPane.WARNING_MESSAGE);
                    movable = false;
                }
            }
        }   
    }//end of put down piece
	
	//done by Lee Wing Shum
    public void chesschange(){
        //if the piece had move less than 3 times
        Piece eatenPiece = null;
        Piece beforeChange = null;

        if (movePiece.getCountStep()<2)
        {
            int tempcounter = movePiece.getCountStep();
            tempcounter++;

            if (position[newY][newX].getPiece()!=null)
            {
                eatenPiece = position[newY][newX].getPiece();
                if(position[newY][newX].getPiece().getType().equals("sun"))
                {
                    //if the position they choose to put on its others players sun, then gameover
                    gameover = true;
                }
            }
            //move the chosen piece to the new position
            position[newY][newX].setPiece(movePiece);
            position[oldY][oldX].removePiece();
            movePiece.setX(newX);
            movePiece.setY(newY);
            movePiece.setCountStep(tempcounter);//add count step
            btn[oldY][oldX].setIcon(null);
            setImageIconSize(newX, newY, movePiece);
            movable = true;
            clearBtnColor();    
        }
        else if (movePiece.getCountStep()==2)
        {
            //if the piece reach third step
            PieceFactory pieceFactory = new PieceFactory();
            if (position[newY][newX].getPiece()!=null)
            {
                eatenPiece = position[newY][newX].getPiece();
                if(position[newY][newX].getPiece().getType().equals("sun"))
                {
                    //if the position they choose to put on its others players sun, then gameover
                    gameover = true;
                }
            }

            movePiece.setX(newX);
            movePiece.setY(newY);
            //change the piece
            if (movePiece.getType().equals("plus"))
            {
                //plus will change to triangle
                if(movePiece.getPlayerID().equals("Player 1"))
                {
                    beforeChange = movePiece;
                    movePiece = pieceFactory.getPiece("Player 1", movePiece.getPieceID(), movePiece.getX(), movePiece.getY(), "triangle", triangleRed);
                    movePiece.setImgIcon(triangleRed);
                }
                else
                {
                    beforeChange = movePiece;
                    movePiece = pieceFactory.getPiece("Player 2", movePiece.getPieceID(), movePiece.getX(), movePiece.getY(), "triangle", triangleBlue);
                    movePiece.setImgIcon(triangleBlue);
                }
            }
            else if (movePiece.getType().equals("triangle"))
            {
                //triangle will change to chevron
                if(movePiece.getPlayerID().equals("Player 1"))
                {
                    beforeChange = movePiece;
                    movePiece = pieceFactory.getPiece("Player 1", movePiece.getPieceID(), movePiece.getX(), movePiece.getY(), "chevron", chevronRed);
                    movePiece.setImgIcon(chevronRed);
                }
                else
                {
                    beforeChange = movePiece;
                    movePiece = pieceFactory.getPiece("Player 2", movePiece.getPieceID(), movePiece.getX(), movePiece.getY(), "chevron", chevronBlue);
                    movePiece.setImgIcon(chevronBlue);
                }
            }
            else if (movePiece.getType().equals("chevron"))
            {
                //chevron will change to plus
                if(movePiece.getPlayerID().equals("Player 1"))
                {
                    beforeChange = movePiece;
                    movePiece = pieceFactory.getPiece("Player 1", movePiece.getPieceID(), movePiece.getX(), movePiece.getY(), "plus", plusRed);
                    movePiece.setImgIcon(plusRed);
                }
                else
                {
                    beforeChange = movePiece;
                    movePiece = pieceFactory.getPiece("Player 2", movePiece.getPieceID(), movePiece.getX(), movePiece.getY(), "plus", plusBlue);
                    movePiece.setImgIcon(plusBlue);
                }
            }
            //set the new piece to new position
            movePiece.setCountStep(0);
            position[newY][newX].setPiece(movePiece);
            position[oldY][oldX].removePiece();
            btn[oldY][oldX].setIcon(null);
            setImageIconSize(newX, newY, movePiece);
            movable = true;
            clearBtnColor();
        }
        checkMate(eatenPiece, beforeChange);
    }

    //done by Lee Wing Shum
    public void newGame(){
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (position[i][j].getPiece()!=null)
                {
                    position[i][j].removePiece();
                    btn[i][j].setIcon(null);
                }
            }
        }

        for (int i =0; i< ROWS; i++)
        {
            for (int j=0; j< COLUMNS; j++)
            {
                setPiece(i,j);
            }
        }
        playerTurn.setState(p1);
        gameover = false;
    }
	
	//done by Lee Wing Shum
    public void checkgameover(){
        if (gameover)
        {
            winner = movePiece.getPlayerID();
            JOptionPane.showMessageDialog(gameBoard, "Game Over! \n"+winner+" has won the game ! Good game.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        else {
            playerTurn.getState().changePlayer(playerTurn); //change player
            turnBoard();
        }
    }

    //done by Chong Foong Loong
    public void turnBoard() {
        ArrayList<Piece> changePiece = new ArrayList<Piece>();
        int newX;
        int newY;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (position[i][j].getPiece() != null) {
                    newX = 6 - position[i][j].getPiece().getX();
                    newY = 5 - position[i][j].getPiece().getY();

                    position[i][j].getPiece().setX(newX);
                    position[i][j].getPiece().setY(newY);
                    changePiece.add(position[i][j].getPiece());
                    position[i][j].removePiece();
                    btn[i][j].setIcon(null);
                }
            }
        }

        for (Piece P : changePiece) {
            newY = P.getY();
            newX = P.getX();
            position[newY][newX].setPiece(P);
            Image img = P.getImgIcon();
            int w = btn[newY][newX].getWidth();
            int h = btn[newY][newX].getHeight();
            int size =  Math.min(w, h)-20;
            img = img.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
            btn[newY][newX].setIcon(new ImageIcon(img));
        }
    }

    //done by Lee Wing Shum
    public void saveBoard(){
        int savex,savey,savecount;
        String saveid,savepid,savetype;
        JFileChooser fc = new JFileChooser ();
        fc.setDialogType(JFileChooser.SAVE_DIALOG);
        int fcState = fc.showSaveDialog(null);
        if (fcState == JFileChooser.APPROVE_OPTION){
            try{
                File fileToSave = fc.getSelectedFile(); //get filename to save to
                FileWriter fw = new FileWriter(fileToSave+".txt",true); //the true will append the new data
                String currentPlayer = playerTurn.getStateString();
                fw.write(currentPlayer+System.getProperty("line.separator"));
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 7; j++) {
                        if (position[i][j].getPiece() != null) {
                            saveid = position[i][j].getPiece().getPlayerID();
                            savepid = position[i][j].getPiece().getPieceID();
                            savex = position[i][j].getX();
                            savey = position[i][j].getY();
                            savetype = position[i][j].getPiece().getType();
                            savecount = position[i][j].getPiece().getCountStep();

                            fw.write(saveid+"|"+savepid+"|"+savex+"|"+savey+"|"+savetype+"|"+savecount+System.getProperty( "line.separator" ));
                        }
                    }
                }
                fw.close();
            }
            catch  (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    //done by Lee Wing Shum
    public void loadBoard(){
        JFileChooser fc = new JFileChooser();
        gameover = false;
        winner = null;
        int fcState = fc.showOpenDialog(null);
        if (fcState == JFileChooser.APPROVE_OPTION){
            try{
                File selectedFile = fc.getSelectedFile();
                FileReader fw = new FileReader(selectedFile); 
                BufferedReader br = new BufferedReader(fw);
                String currentPlayer = br.readLine();
                if (currentPlayer .equals("Player 1"))
                {
                    movable = true;
                    playerTurn.setState(p1);
                }
                else
                {
                    playerTurn.setState(p1);
                    movable = true;
                    playerTurn.getState().changePlayer(playerTurn); //change player				
                }

                for (int i = 0; i < ROWS; i++) {
                    for (int j = 0; j < COLUMNS; j++) {
                        if (position[i][j].getPiece()!=null)
                        {
                            position[i][j].removePiece();
                            btn[i][j].setIcon(null);
                        }
                    }
                }
                String line;
                PieceFactory pieceFactory = new PieceFactory();
                Image image = null;
                while((line=br.readLine())!=null)
                {
                    String[]spliter = line.split("\\|");
                    if(spliter[4].equals("plus")&& spliter[0].equals("Player 1"))
                    {
			image = plusRed;
                    }
                    else if(spliter[4].equals("triangle") && spliter[0].equals("Player 1"))
                    {
			image = triangleRed;
                    }

                    else if (spliter[4].equals("chevron") && spliter[0].equals("Player 1"))
                    {
			image = chevronRed;
                    }

                    else if (spliter[4].equals("sun") && spliter[0].equals("Player 1"))
                    {
			image = sunRed;
                    }
                    else if (spliter[4].equals("plus") && spliter[0].equals("Player 2"))
                    {
			image = plusBlue;
                    }

                    else if (spliter[4].equals("triangle") && spliter[0].equals("Player 2"))
                    {
			image = triangleBlue;
                    }

                    else if (spliter[4].equals("chevron") && spliter[0].equals("Player 2"))
                    {
			image = chevronBlue;
                    }

                    else if (spliter[4].equals("sun") && spliter[0].equals("Player 2"))
                    {
			image = sunBlue;
                    }
                    int x = Integer.parseInt(spliter[2]);
                    int y = Integer.parseInt(spliter[3]);
                    int h = btn[y][x].getHeight();
                    int w = btn[y][x].getWidth();
                    int size =  Math.min(w, h)-20;
                    image = image.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
                    Piece p = pieceFactory.getPiece(spliter[0],spliter[1],Integer.parseInt(spliter[2]),Integer.parseInt(spliter[3]),spliter[4], image);
                    position[Integer.parseInt(spliter[3])][Integer.parseInt(spliter[2])] = new Position(Integer.parseInt(spliter[2]), Integer.parseInt(spliter[3]), p);
                    position[Integer.parseInt(spliter[3])][Integer.parseInt(spliter[2])].getPiece().setCountStep(Integer.parseInt(spliter[5]));
                    btn[Integer.parseInt(spliter[3])][Integer.parseInt(spliter[2])].setIcon(new ImageIcon(p.getImgIcon()));					
                }
            }
            catch  (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    //done by Lee Wing Shum, Lee Yean Wei
    public void checkMate(Piece eatenPiece, Piece beforeChange){
        HashSet<String> checkSunPiece = new HashSet<String>();
        if(eatenPiece != null && eatenPiece.getType().equals("sun")) {
            checkgameover();
        }
        else {
            Piece chosenPiece = null;
            Piece checkedSun = null;
            boolean cmate = false;
            
            //check every condition of being checked
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLUMNS; j++) {
                    if (position[i][j].getPiece()!=null)
                    {
                        chosenPiece = position[i][j].getPiece();
                        ArrayList<ArrayList<Position>> positionList = chosenPiece.movePattern();

                        cyanPosition.clear();
                        filterMovablePosition(chosenPiece, positionList);
                        clearBtnColor();

                        for(Position p : cyanPosition) {
                            if(position[p.getY()][p.getX()].getPiece() != null) {
                                if(position[p.getY()][p.getX()].getPiece().getType().equals("sun")) {
                                    checkedSun = position[p.getY()][p.getX()].getPiece();
                                    checkSunPiece.add(chosenPiece.getType());
                                    cmate = true;
                                }
                            }
                        }
                    }
                }
            }

            boolean condition1 = cmate && !(checkedSun.getPlayerID()).equals(playerTurn.getStateString());
            boolean condition2 = cmate && (checkedSun.getPlayerID()).equals(playerTurn.getStateString());
            
            //next available moves, check checkmate
            if(checkedSun != null) {
                if(condition1) {
                    ArrayList<ArrayList<Position>> positionList = checkedSun.movePattern();
                    cyanPosition.clear();
                    filterMovablePosition(checkedSun, positionList);
                    clearBtnColor();
                    
                    ArrayList<Position> checkCheckMate = new ArrayList<Position>();
                    for(Position p : cyanPosition) {
                        checkCheckMate.add(p);
                    }
                    HashSet<Position> checkedPosition = new HashSet<Position>();
                    
                    Iterator<Position> checkCheckMateIterator = checkCheckMate.iterator();
                    while(checkCheckMateIterator.hasNext()) {
                        Position p = checkCheckMateIterator.next();
                        for(int i = 0; i < ROWS; i++) {
                            for(int j = 0; j < COLUMNS; j++) {
                                if(position[i][j].getPiece()!=null && !position[i][j].getPiece().getPlayerID().equals(checkedSun.getPlayerID())) {
                                    chosenPiece = position[i][j].getPiece();
                                    ArrayList<ArrayList<Position>> checkList = chosenPiece.movePattern();
                                    
                                    ArrayList<Position> allPossiblePosition = new ArrayList<Position>();
                                    for(int k = 0; k < checkList.size(); k++) {
                                        for(int l = 0; l < checkList.get(k).size(); l++) {
                                            allPossiblePosition.add(checkList.get(k).get(l));
                                        }
                                    }

                                    Iterator<Position> allPossiblePositionIterator = allPossiblePosition.iterator();
                                    while(allPossiblePositionIterator.hasNext()){
                                        Position compareP = allPossiblePositionIterator.next();
                                        if(compareP.getX() == p.getX() && compareP.getY() == p.getY()) {
                                            checkedPosition.add(p);
                                        }
                                    }
                                }
                            }
                        }     
                    }
                    if(checkedPosition.size() == checkCheckMate.size()) {
                        gameover = true;
                    }
                }
            }

            System.out.println(checkSunPiece);
            boolean gotSun = checkSunPiece.contains("sun");
            
            if(cmate == false) {
                checkgameover();
            }
            else if (condition2|| gotSun)
            {
                //if player make a move that will check themselves, undo their move
                JOptionPane.showMessageDialog(gameBoard, "You are checked!", "Warning", JOptionPane.WARNING_MESSAGE);
                if(beforeChange != null) {
                    beforeChange.setX(oldX);
                    beforeChange.setY(oldY);
                    position[oldY][oldX].setPiece(beforeChange);
                    position[newY][newX].removePiece();
                    btn[newY][newX].setIcon(null);
                    setImageIconSize(oldX, oldY, beforeChange);
                    beforeChange = null;
                }
                else {
                    movePiece.setX(oldX);
                    movePiece.setY(oldY);
                    movePiece.setCountStep(movePiece.getCountStep()-1);
                    position[oldY][oldX].setPiece(movePiece);
                    position[newY][newX].removePiece();
                    btn[newY][newX].setIcon(null);
                    setImageIconSize(oldX, oldY, movePiece);
                }

                //bring back the eaten piece
                if(eatenPiece != null) {
                    int reviveX = eatenPiece.getX();
                    int reviveY = eatenPiece.getY();
                    position[reviveY][reviveX].setPiece(eatenPiece);
                    setImageIconSize(reviveX, reviveY, eatenPiece);
                    eatenPiece = null; //set it back to null to prevent bring back several times
                }
            }
            else if(condition1)
            {
                //if player check the other player, procedd
                JOptionPane.showMessageDialog(gameBoard, "Check " + checkedSun.getPlayerID(), "Warning", JOptionPane.WARNING_MESSAGE);
                checkgameover(); 
            }
        }
    }
    
    //done by Lee Yean Wei
    public void setImageIconSize(int x, int y, Piece p) {
        Image img = p.getImgIcon();
        int w = btn[y][x].getWidth();
        int h = btn[y][x].getHeight();
        int size =  Math.min(w, h)-20;
        img = img.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
        btn[y][x].setIcon(new ImageIcon(img));
    }
}//end of class
