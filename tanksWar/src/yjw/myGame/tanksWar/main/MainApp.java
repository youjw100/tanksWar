package yjw.myGame.tanksWar.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainApp  extends JFrame{

	private static final long serialVersionUID = -4177589767011869892L;
	
	private Point point;
	private Dimension dimension;
	
	public MainApp() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//获取屏幕参数，将主界面居中
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.point = new Point(screenSize.width/10, screenSize.height/10);
		this.dimension = new Dimension((int)(screenSize.width*0.8), (int)(screenSize.height*0.8));
		//初始化主界面
		this.setLayout(null);
		this.setBounds(point.x, point.y, dimension.width, dimension.height);
		this.setBackground(Color.BLACK);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		MainApp mainApp = new MainApp();
	}
	
}
