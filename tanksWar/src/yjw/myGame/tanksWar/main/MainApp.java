package yjw.myGame.tanksWar.main;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import yjw.myGame.tanksWar.control.ControlListener;
import yjw.myGame.tanksWar.map.Map;
import yjw.myGame.tanksWar.model.TankModel;
import yjw.myGame.tanksWar.myEnum.DirectionEnum;
import yjw.myGame.tanksWar.myEnum.TypeEnum;
import yjw.myGame.tanksWar.util.MyGameUtil;

public class MainApp  extends JFrame implements Runnable{

	private static final long serialVersionUID = -4177589767011869892L;
	//�������λ��
	private Point point;
	//������Ĵ�С
	private Dimension dimension;
	//�������ȥ�߿���Ĵ�С
	public Dimension relDimension;
	//��Ϸ����
	public Map map;
	//�ҷ�̹��
	public TankModel hero1;
	public TankModel hero2;
	//�з�̹��
	public TankModel enemy1;
	public TankModel enemy2;
	public TankModel enemy3;
	public TankModel enemy4;
	
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
	public static MainApp getMainApp() {
		//��ȡ��Ļ����
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//������Ļ���������������С��λ��
		Point mainPoint = new Point(screenSize.width/10, screenSize.height/10);
		Dimension mainDimension = new Dimension((int)(screenSize.width*0.8), (int)(screenSize.height*0.8));
		//���������沢��ʼ��
		MainApp mainApp = new MainApp(mainPoint, mainDimension);
		//�����������С��λ��
		mainApp.point = new Point((int)(screenSize.width - mainApp.relDimension.getHeight()/0.618)/2, mainApp.point.y);
		mainApp.dimension = new Dimension((int)(mainApp.relDimension.getHeight()/0.618), mainApp.dimension.height);
		mainApp.setBounds(mainApp.point.x, mainApp.point.y, mainApp.dimension.width, mainApp.dimension.height);
		
		return mainApp;
	}
	
	/**
	 * ��ȡ��Ϸ��ͼ��壬����������������λ�ü���С
	 * @param mainApp
	 * @return
	 */
	public Map getMap(MainApp mainApp) { 
		
		int space = (mainApp.relDimension.height%10);
		space = space==0 ? 4 : space;
		Point mapPoint = new Point(space/2, space/2);
		Dimension mapDimension = new Dimension(mainApp.relDimension.height-space, mainApp.relDimension.height-space);
		
		Map map = new Map(mapPoint, mapDimension);
		return map;
	}
	
	/**
	 * �����ҷ�̹��
	 * @param mainApp
	 */
	public void controlTankListener(MainApp mainApp, TypeEnum type) {
		this.addKeyListener(new KeyAdapter() {
			//�ҷ�̹�˵��ƶ�����
			ControlListener hero1MoveListrner = new ControlListener(mainApp.hero1);
			ControlListener hero2MoveListrner = new ControlListener(mainApp.hero2);
			Thread controlHero1 = new Thread(hero1MoveListrner);
			Thread controlHero2 = new Thread(hero2MoveListrner);
			@Override
			public void keyPressed(KeyEvent e) {
				if(type == TypeEnum.FRIEND1 
						&& mainApp.hero1 != null && mainApp.hero1.isLive()) {
					if(KeyEvent.VK_W == e.getKeyCode()
							|| KeyEvent.VK_S == e.getKeyCode()
							|| KeyEvent.VK_A == e.getKeyCode()   
							|| KeyEvent.VK_D == e.getKeyCode()) {
						mainApp.hero1.setConMove(true);
						hero1MoveListrner.setEvent(e);
						if(!controlHero1.isAlive()) {
							controlHero1 = new Thread(hero1MoveListrner);
							controlHero1.start();
						}
					}
					if(KeyEvent.VK_J == e.getKeyCode()) {
						//�����ӵ��������ӵ��Զ�����
						if(mainApp.hero1.getBulletNum() > 0) {
							mainApp.hero1.initBullet(mainApp.map);
							mainApp.map.add(mainApp.hero1.bullet);
							Thread hero1Bullet = new Thread(mainApp.hero1.bullet);
							hero1Bullet.start();
							mainApp.hero1.setBulletNum(mainApp.hero1.getBulletNum() - 1);
						}
					}
				}
				if(type == TypeEnum.FRIEND2 
						&& mainApp.hero2 != null && mainApp.hero2.isLive()) {
					if(KeyEvent.VK_UP == e.getKeyCode()
							|| KeyEvent.VK_DOWN == e.getKeyCode()
							|| KeyEvent.VK_LEFT == e.getKeyCode()
							|| KeyEvent.VK_RIGHT == e.getKeyCode()) {
						mainApp.hero2.setConMove(true);
						hero2MoveListrner.setEvent(e);
						if(!controlHero2.isAlive()) {
							controlHero2 = new Thread(hero2MoveListrner);
							controlHero2.start();
						}
					}
					if(KeyEvent.VK_NUMPAD0 == e.getKeyCode()) {
						if(mainApp.hero1.getBulletNum() > 0) {
							mainApp.hero2.initBullet(mainApp.map);
							mainApp.map.add(mainApp.hero2.bullet);
							Thread hero2Bullet = new Thread(mainApp.hero2.bullet);
							hero2Bullet.start();
							mainApp.hero2.setBulletNum(mainApp.hero2.getBulletNum() - 1);
						}
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if(type == TypeEnum.FRIEND1 
						&& mainApp.hero1 != null && mainApp.hero1.isLive()) {
					if(KeyEvent.VK_W == e.getKeyCode()
							|| KeyEvent.VK_S == e.getKeyCode()
							|| KeyEvent.VK_A == e.getKeyCode()
							|| KeyEvent.VK_D == e.getKeyCode()) {
						mainApp.hero1.setConMove(false);
					}
				}
				if(type == TypeEnum.FRIEND2
						&& mainApp.hero2 != null && mainApp.hero2.isLive()) {
					if(KeyEvent.VK_UP == e.getKeyCode()
							|| KeyEvent.VK_DOWN == e.getKeyCode()
							|| KeyEvent.VK_LEFT == e.getKeyCode()
							|| KeyEvent.VK_RIGHT == e.getKeyCode()) {
						mainApp.hero2.setConMove(false);
					}
				}
			}
		});
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//��ʱ�����Ϸ���棬���ػ�
			MyGameUtil.removeDeathModel(this);
			//��λ̹��ģ��
			ArrayList<TankModel> allTank = MyGameUtil.getAllTank(this);
			for(TankModel tank : allTank) {
				if(tank != null && tank.isLive()) {
					if(MyGameUtil.modelIsOuterBorder(tank, this.map.borderModel, DirectionEnum.UP)) {
						tank.setPoint(new Point(tank.getPoint().x, 0));
					} else if(MyGameUtil.modelIsOuterBorder(tank, this.map.borderModel, DirectionEnum.DOWN)) {
						tank.setPoint(new Point(tank.getPoint().x, tank.getMap().getDimension().height-tank.getDimension().height));
					} else if(MyGameUtil.modelIsOuterBorder(tank, this.map.borderModel, DirectionEnum.LEFT)) {
						tank.setPoint(new Point(0, tank.getPoint().y));
					} else if(MyGameUtil.modelIsOuterBorder(tank, this.map.borderModel, DirectionEnum.RIGHT)) {
						tank.setPoint(new Point(tank.getMap().getDimension().width-tank.getDimension().width, tank.getPoint().y));
					}
				}
			}
			this.repaint();
		}
	}
	
}
