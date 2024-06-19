package yarvaOthello;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Pressy extends JButton {
	Color color=Color.GREEN;
	
	@Override
    public void paintComponent(Graphics g) {  
       super.paintComponent(g);    
       setBackground(Color.GREEN);
       setSize(60,52);
       setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
     g.setColor(color);
     g.fillOval(13, 11, 30, 30);
     if(this.color==Color.WHITE) {
    	 g.setColor(Color.BLACK);
    	 g.drawOval(13, 11, 30, 30);
     }
     else if(this.color==Color.BLACK) {
    	 g.setColor(Color.WHITE);
    	 g.drawOval(13, 11, 30, 30);
     }
}
	public Pressy(Color color) {
		this.color=color;
	}
	
	
	public void changeButt(Color color) {
		this.color=color;
	}
 }