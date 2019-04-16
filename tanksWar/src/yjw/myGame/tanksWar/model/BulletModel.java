package yjw.myGame.tanksWar.model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import yjw.myGame.tanksWar.map.Map;
import yjw.myGame.tanksWar.myEnum.DirectionEnum;
import yjw.myGame.tanksWar.myEnum.TypeEnum;
import yjw.myGame.tanksWar.util.MyGameUtil;

/**
 * 子弹模型
 * @author 梦有形心有伴
 *
 */
public class BulletModel extends Model implements Runnable{
	private static final long serialVersionUID = -2039805938527871867L;
	//子弹的方向
	private DirectionEnum direction;
	//是否是敌方子弹
	private boolean isEnemy;
	//子弹所属坦克
	private TankModel tank;
	
	public BulletModel(TankModel tank, Map map) {
		
		super(null, new Dimension(tank.getDimension().width/4, tank.getDimension().height/4), TypeEnum.BULLET, map, tank.getDimension().width/4);
		
		this.tank = tank;
		
		this.direction = this.tank.getDirection();
		this.setColor(this.tank.getColor());
		switch (this.direction) {
		case UP:
			this.point = new Point(this.tank.getPoint().x + this.tank.getDimension().width/2 - this.dimension.width/2, this.tank.getPoint().y);break;
		case DOWN:
			this.point = new Point(this.tank.getPoint().x + this.tank.getDimension().width/2 - this.dimension.width/2, this.tank.getPoint().y + this.tank.getDimension().height - this.dimension.height);break;
		case LEFT:
			this.point = new Point(this.tank.getPoint().x, this.tank.getPoint().y + this.tank.getDimension().height/2 - this.dimension.height/2);break;
		case RIGHT:
			this.point = new Point(this.tank.getPoint().x + this.tank.getDimension().width - this.dimension.width, this.tank.getPoint().y + this.tank.getDimension().height/2 - this.dimension.height/2);break;
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(this.getColor());
		g.fillOval(0, 0, this.dimension.width, this.dimension.height);
	}
	
	@Override
	public void run() {
		while(this.isLive()) {
			//如果子弹超出地图范围，则判定子弹死亡
			if(this.point.x < -this.dimension.width || this.point.x > this.map.getDimension().getWidth() + this.dimension.width
					|| this.point.y < -this.dimension.height || this.point.y > this.map.getDimension().getHeight() + this.dimension.height) {
				this.setLive(false);
				this.tank.setBulletNum(this.tank.getBulletNum() + 1);
			}
			Model collisionModel = MyGameUtil.collisionModel(this, this.direction);
			if(collisionModel != null) {
				this.setLive(false);
				this.tank.setBulletNum(this.tank.getBulletNum() + 1);
				if(collisionModel instanceof TankModel) {
					
				}
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			switch(direction) {
			case UP:
				this.setPoint(new Point(this.point.x, this.point.y - this.moveSize));break;
			case DOWN:
				this.setPoint(new Point(this.point.x, this.point.y + this.moveSize));break;
			case LEFT:
				this.setPoint(new Point(this.point.x - this.moveSize, this.point.y));break;
			case RIGHT:
				this.setPoint(new Point(this.point.x + this.moveSize, this.point.y));break;
			}
		}
	}
	
	public boolean isEnemy() {
		return isEnemy;
	}

	public void setEnemy(boolean isEnemy) {
		this.isEnemy = isEnemy;
	}
	
}
