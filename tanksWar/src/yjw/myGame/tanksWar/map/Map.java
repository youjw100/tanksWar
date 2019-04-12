package yjw.myGame.tanksWar.map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Map extends JFrame{

	private static final long serialVersionUID = 6998589850664286325L;
	
	private Point point;
	private Dimension dimension;
	
	public Map() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.point = new Point(screenSize.width/10, screenSize.height/10);
		this.dimension = new Dimension((int)(screenSize.width*0.8), (int)(screenSize.height*0.8));
		
		this.setLayout(null);
		this.setBounds(point, dimension);
		this.setBackground(Color.BLACK);
		this.setVisible(true);
	}
	
	private void setBounds(Point point, Dimension dimension) {
		this.setBounds(point.x, point.y, dimension.width, dimension.height);
	}
	
	public static void main(String[] args) {
		Map a = new Map();
	}
}
