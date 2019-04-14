package yjw.myGame.tanksWar.map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JPanel;

public class Map extends JPanel{

	private static final long serialVersionUID = 6998589850664286325L;
	
	private Point point;
	private Dimension dimension;
	
	public Map(Point point, Dimension dimension) {
		this.point = point;
		this.dimension = dimension;
		
		this.setLayout(null);
		this.setBounds(this.point.x, this.point.y, this.dimension.width, this.dimension.height);
		this.setBackground(Color.BLACK);
		this.setVisible(true);
	}
	
	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

}
