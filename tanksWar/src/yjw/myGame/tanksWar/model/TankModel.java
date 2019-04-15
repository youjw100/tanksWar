package yjw.myGame.tanksWar.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import yjw.myGame.tanksWar.control.ControlListener;
import yjw.myGame.tanksWar.control.autoAttack;
import yjw.myGame.tanksWar.map.Map;
import yjw.myGame.tanksWar.myEnum.DirectionEnum;
import yjw.myGame.tanksWar.myEnum.TypeEnum;

/**
 * 坦克模型
 * @author 梦有形心有伴
 *
 */
public class TankModel extends Model implements Runnable{
	private static final long serialVersionUID = 4503796111367164433L;
	//是否是敌方坦克
	private boolean isEnemy;
	//坦克的方向
	private DirectionEnum direction;
	//坦克是否持续移动（敌方坦克自动移动时用来记录线程改变）
	private boolean isConMove;
	//坦克的子弹
	public BulletModel bullet;
	//坦克的弹夹容量
	private int bulletNum;
	
	public TankModel(Point point, Dimension dimension, TypeEnum type, Map map) {
		this.point = point;
		this.dimension = dimension;
		this.map = map;
		this.setMoveSize(dimension.width/10);
		//坦克弹夹容量设置为2发
		this.bulletNum = 2;
		this.setLive(true);
		//设置坦克类型，并根据坦克类型设置颜色
		this.setType(type);
		if(this.type == TypeEnum.FRIEND1 || this.type == TypeEnum.FRIEND2) {
			this.isEnemy = false;
		} else {
			this.isEnemy = true;
		}
		if(this.isEnemy()) {
			this.direction = DirectionEnum.DOWN;
		} else {
			this.direction = DirectionEnum.UP;
		}

		this.setLayout(null);
		this.setBounds(this.point.x, this.point.y, this.dimension.width, this.dimension.height);
		this.setBackground(Color.BLACK);
		this.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		//设置画笔颜色
		g.setColor(this.color);
		//画不同方向的坦克
		switch(this.direction) {
		case UP:
			g.fill3DRect(0, 0, this.getWidth()/3, this.getHeight(), false);
			g.fill3DRect(this.getWidth()*2/3, 0, this.getWidth()/3, this.getHeight(), false);
			g.fill3DRect(this.getWidth()/3, this.getHeight()/6, this.getWidth()/3, this.getHeight()*2/3, false);
			g.fillOval(this.getWidth()/3, this.getHeight()/3, this.getWidth()/3, this.getHeight()/3);
			g.drawLine(this.getWidth()/2, 0, this.getWidth()/2, this.getHeight()/2);
			break;
		case DOWN:
			g.fill3DRect(0, 0, this.getWidth()/3, this.getHeight(), false);
			g.fill3DRect(this.getWidth()*2/3, 0, this.getWidth()/3, this.getHeight(), false);
			g.fill3DRect(this.getWidth()/3, this.getHeight()/6, this.getWidth()/3, this.getHeight()*2/3, false);
			g.fillOval(this.getWidth()/3, this.getHeight()/3, this.getWidth()/3, this.getHeight()/3);
			g.drawLine(this.getWidth()/2, this.getHeight()/2, this.getWidth()/2, this.getHeight());
			break;
		case LEFT:
			g.fill3DRect(0, 0, this.getWidth(), this.getHeight()/3, false);
			g.fill3DRect(0, this.getHeight()*2/3, this.getWidth(), this.getHeight()/3, false);
			g.fill3DRect(this.getWidth()/6, this.getHeight()/3, this.getWidth()*2/3, this.getHeight()/3, false);
			g.fillOval(this.getWidth()/3, this.getHeight()/3, this.getWidth()/3, this.getHeight()/3);
			g.drawLine(0, this.getHeight()/2, this.getWidth()/2, this.getHeight()/2);
			break;
		case RIGHT:
			g.fill3DRect(0, 0, this.getWidth(), this.getHeight()/3, false);
			g.fill3DRect(0, this.getHeight()*2/3, this.getWidth(), this.getHeight()/3, false);
			g.fill3DRect(this.getWidth()/6, this.getHeight()/3, this.getWidth()*2/3, this.getHeight()/3, false);
			g.fillOval(this.getWidth()/3, this.getHeight()/3, this.getWidth()/3, this.getHeight()/3);
			g.drawLine(this.getWidth()/2, this.getHeight()/2, this.getWidth(), this.getHeight()/2);
			break;
		}
	}
	
	/**
	 * 初始化坦克的子弹
	 */
	public void initBullet(Map map) {
		this.bullet = new BulletModel(this, map);
	}

	@Override
	public void run() {
		//坦克自动攻击和移动，针对敌方坦克
		if(this.isEnemy()) {
			Thread enemyAutoMove = new Thread(new ControlListener(this));
			Thread autoAttack = new Thread(new autoAttack(this));
			//自动攻击
			if(!autoAttack.isAlive() && this.isLive()) {
				autoAttack.start();
			}
			Random r = new Random();
			while(this.isLive()) {
				//随机移动的时间
				int moveTime = r.nextInt(5000);
				//随机移动的方向
				int moveDirection = r.nextInt(4);
				switch (moveDirection) {
				case 0: this.direction = DirectionEnum.UP;break;
				case 1: this.direction = DirectionEnum.DOWN;break;
				case 2: this.direction = DirectionEnum.LEFT;break;
				case 3: this.direction = DirectionEnum.RIGHT;break;
				}
				this.isConMove = true;
				//朝随机方向持续移动moveTime毫秒
				if(!enemyAutoMove.isAlive()) {
					enemyAutoMove.start();
				}
				try {
					Thread.sleep(moveTime);
					this.isConMove = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public DirectionEnum getDirection() {
		return direction;
	}

	public void setDirection(DirectionEnum direction) {
		this.direction = direction;
		this.repaint();
	}

	public TypeEnum getType() {
		return type;
	}
	
	public void setType(TypeEnum type) {
		this.type = type;
		switch(type) {
		case FRIEND1: this.color = Color.MAGENTA;break;
		case FRIEND2: this.color = Color.BLUE;break;
		case ENEMY1: this.color = Color.CYAN;break;
		case ENEMY2: this.color = Color.GREEN;break;
		case ENEMY3: this.color = Color.LIGHT_GRAY;break;
		case ENEMY4: this.color = Color.YELLOW;break;
		default: 
			throw new RuntimeException("坦克初始化异常，坦克类型只能为“FRIEND”和“ENEMY”！");
		}
	}

	public boolean isConMove() {
		return isConMove;
	}

	public void setConMove(boolean isConMove) {
		this.isConMove = isConMove;
	}

	public int getBulletNum() {
		return bulletNum;
	}

	public void setBulletNum(int bulletNum) {
		this.bulletNum = bulletNum;
	}

	public boolean isEnemy() {
		return isEnemy;
	}

	public void setEnemy(boolean isEnemy) {
		this.isEnemy = isEnemy;
	}

}
