package yjw.myGame.tanksWar.main;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

import yjw.myGame.tanksWar.map.Map;

public class MainApp  extends JFrame{

	private static final long serialVersionUID = -4177589767011869892L;
	
	private Point point;
	private Dimension dimension;
	public Dimension relDimension;
	
	public MainApp(Point point, Dimension dimension) {
		this.point = point;
		this.dimension = dimension;
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//��ʼ��������
		this.setLayout(null);
		this.setBounds(this.point.x, this.point.y, this.dimension.width, this.dimension.height);
		this.setResizable(false);
		this.setVisible(true);
		this.relDimension = this.getGlassPane().getSize();
	}
	
	/**
	 * ������Ļ��С���������棬��ʹ�������
	 * @return
	 */
	private static MainApp getMainApp() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		Point mainPoint = new Point(screenSize.width/10, screenSize.height/10);
		Dimension mainDimension = new Dimension((int)(screenSize.width*0.8), (int)(screenSize.height*0.8));
		
		MainApp mainApp = new MainApp(mainPoint, mainDimension);
		
		mainApp.point = new Point((int)(screenSize.width - mainApp.relDimension.getHeight()/0.618)/2, mainApp.point.y);
		mainApp.dimension = new Dimension((int)(mainApp.relDimension.getHeight()/0.618), mainApp.dimension.height);
		mainApp.setBounds(mainApp.point.x, mainApp.point.y, mainApp.dimension.width, mainApp.dimension.height);
		
		return mainApp;
	}
	
	/**
	 * ��ȡ��Ϸ�ռ䣬����������������λ�ü���С
	 * @param mainAppDimension
	 * @return
	 */
	private static Map getMap(MainApp mainApp) { 
		
		int space = (mainApp.relDimension.height%10);
		space = space==0 ? 4 : space;
		Point mapPoint = new Point(space/2, space/2);
		Dimension mapDimension = new Dimension(mainApp.relDimension.height-space, mainApp.relDimension.height-space);
		
		Map map = new Map(mapPoint, mapDimension);
		return map;
	}
	
	public static void main(String[] args) {
		//����������
		MainApp mainApp = getMainApp();
		//������Ϸ��ͼ
		Map map = getMap(mainApp);
		mainApp.add(map);
	}
	
}
