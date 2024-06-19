package yarvaOthello;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI implements ActionListener{
private static final int GRID_WIDTH = 8;
private static final int GRID_HEIGHT = 8;

int[] locB=new int[2];
int[] locW=new int[2];

JFrame whiteFrame = new JFrame();
JFrame blackFrame = new JFrame();

JPanel blackGrid = new JPanel();
JPanel whiteGrid = new JPanel();

Pressy[][] whiteTiles = new Pressy[GRID_WIDTH][GRID_HEIGHT];
Pressy[][] blackTiles = new Pressy[GRID_WIDTH][GRID_HEIGHT];
JButton whiteAIButton = new JButton();
JButton blackAIButton = new JButton();

JLabel whiteLabel = new JLabel();
JLabel blackLabel = new JLabel();

public GUI() {
    populateArray(blackTiles);
    populateArray(whiteTiles);

    initGUI();
}
public void changeLabel() {
	if(whiteLabel.getText()=="White Player - click place to put piece") {
		whiteLabel.setText("White player - not your turn");
		blackLabel.setText("Black Player - click place to put piece");
	}
	else {
		whiteLabel.setText("White Player - click place to put piece");
		blackLabel.setText("Black Player - not your turn");
	}
}
private void populateArray(JButton[][] btnArray) {
	
    for (int x = 0; x < btnArray.length; x++) {
        for (int y = 0; y < btnArray[0].length; y++) {
        	Pressy button=new Pressy(Color.GREEN);
        	button.addActionListener(this);
        	
            btnArray[x][y] = button;
            
        }
    }
}

private void fillGrid(JPanel grid, JButton[][] tiles) {
    grid.setLayout(new GridLayout(GRID_WIDTH, GRID_HEIGHT));

    for (int x = 0; x < GRID_WIDTH; x++) {
        for (int y = 0; y < GRID_HEIGHT; y++) {
            grid.add(tiles[x][y]);
        }
    }
}

private void initGUI() {
    whiteAIButton.setText("Greedy AI (play white)");
    whiteAIButton.addActionListener(this);
    whiteLabel.setText("White Player - click place to put piece");
   
    fillGrid(whiteGrid, whiteTiles);

    whiteFrame.setLayout(new BorderLayout());
    whiteFrame.setTitle("Reversi White Player");
    whiteFrame.add(BorderLayout.NORTH, whiteLabel);
    whiteFrame.add(BorderLayout.CENTER, whiteGrid);
    whiteFrame.add(BorderLayout.SOUTH, whiteAIButton);
    whiteFrame.setPreferredSize(new Dimension(500, 500));
    whiteFrame.pack();
    whiteFrame.setVisible(true);
    whiteFrame.setLocationRelativeTo(null);

    blackAIButton.setText("Greedy AI (play black)");
    blackAIButton.addActionListener(this);
   
    blackLabel.setText("Black player - not your turn");
    fillGrid(blackGrid, blackTiles);

    blackFrame.setTitle("Reversi Black Player");
    blackFrame.setLayout(new BorderLayout());
    blackFrame.add(BorderLayout.NORTH, blackLabel);
    blackFrame.add(BorderLayout.CENTER, blackGrid);
    blackFrame.add(BorderLayout.SOUTH, blackAIButton);
    blackFrame.setPreferredSize(new Dimension(500, 500));
    blackFrame.pack();
    blackFrame.setVisible(true);
    blackFrame.setLocationRelativeTo(null);
}



void putPeg(String c,int i, int j) {
	
	if(c=="white") {
		
		whiteTiles[7-i][7-j].changeButt(Color.WHITE);
		blackTiles[i][j].changeButt(Color.WHITE);
	}
	else if(c=="black"){
		whiteTiles[7-i][7-j].changeButt(Color.BLACK);
		blackTiles[i][j].changeButt(Color.BLACK);
		
	}
	whiteFrame.repaint();
	blackFrame.repaint();
	
}



int[] getClick(char move) {
	int[] ret=new int[2];
	if(move=='b')
	ret=locB;
	if(move=='w')
	ret=locW;
	return ret;
}

void displayMessage(String infoMessage)
{
    JOptionPane.showMessageDialog(null, infoMessage);
}
     
@Override
public void actionPerformed(ActionEvent e) {
	if(blackAIButton == e.getSource()) {
		 locB[0]=9;
         locB[1]=9;
	}
	if(whiteAIButton == e.getSource()) {
		 locW[0]=9;
         locW[1]=9;
	}
	
	for (int i = 0; i < blackTiles.length; i++) { 
	    for (int j = 0; j < blackTiles[i].length; j++) { 
	        if (blackTiles[i][j] == e.getSource()) { 
	            locB[0]=i;
	            locB[1]=j;
	            
	            break;
	        }
	        else if(whiteTiles[i][j]==e.getSource()) {
	        	locW[0]=7-i;
	        	locW[1]=7-j;
	        }
	    }
	}  
	
}
public static void main(String[] args) {
	new GUI();
}
}
