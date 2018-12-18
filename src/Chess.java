import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Chess extends JFrame implements ActionListener {
    //Menubar
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("File");
    private JMenuItem menuItem1 = new JMenuItem("New");
    private JMenuItem menuItem2 = new JMenuItem("Save Game");
    private JMenuItem menuItem3 = new JMenuItem("Load Game");
    private static Board newBoard = Board.getInstance();
    
    public Chess(){
        this.setJMenuBar(menuBar);

        JPanel container = new JPanel(new GridBagLayout());
        container.add(newBoard.getGameBoard());
        container.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizePreview(newBoard.getGameBoard(), container);
            }
        });

        menuItem1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                newBoard.newGame();
            }
        });
        menu.add(menuItem1);
        menuItem2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                newBoard.saveBoard();
            }
        });
        menu.add(menuItem2);
        menuItem3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                newBoard.loadBoard();
            }
        });
        menu.add(menuItem3);
		
        menuBar.add(menu);
	
        getContentPane().add(container);
        setSize(700, 700);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        Chess c = new Chess();
    }
    
    private static void resizePreview(JPanel gameBoard, JPanel container) {
        int w = container.getWidth();
        int h = container.getHeight();
        int size =  Math.min(w, h);
        gameBoard.setPreferredSize(new Dimension(size, size-50));
        container.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
	
}//end of chess.java
