package yjw.myGame.tanksWar.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import yjw.myGame.tanksWar.map.Map;
import yjw.myGame.tanksWar.myEnum.DirectionEnum;
import yjw.myGame.tanksWar.myEnum.TypeEnum;

/**
 * �ӵ�ģ��
 * @author ���������а�
 *
 */
public class BulletModel extends Model implements Runnable{
	private static final long serialVersionUID = -2039805938527871867L;
	//�ӵ��ķ���
	private DirectionEnum direction;
	//�Ƿ��ǵз��ӵ�
	private boolean isEnemy;
	//�ӵ�����̹��
	private TankModel tank;
	
	public BulletModel(TankModel tank, Map map) {
		this.tank = tank;
		this.map = map;
		this.setType(TypeEnum.BULLET);
		
		this.dimension = new Dimension(this.tank.getDimension().width/4, this.tank.getDimension().height/4);
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
		
		this.isLive = true;
		this.setLayout(null);
		this.setBounds(this.point.x, this.point.y, this.dimension.width, this.dimension.height);
		this.setBackground(Color.BLACK);
		this.setVisible(true);
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
			//����ӵ�������ͼ��Χ�����ж��ӵ�����
			if(this.point.x < -this.dimension.width || this.point.x > this.map.getDimension().getWidth() + this.dimension.width
					|| this.point.y < -this.dimension.height || this.point.y > this.map.getDimension().getHeight() + this.dimension.height) {
				this.setLive(false);
				this.tank.setBulletNum(this.tank.getBulletNum() + 1);
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			switch(direction) {
			case UP:
				this.point = new Point(this.point.x, this.point.y - this.dimension.height);break;
			case DOWN:
				this.point = new Point(this.point.x, this.point.y + this.dimension.height);break;
			case LEFT:
				this.point = new Point(this.point.x - this.dimension.width, this.point.y);break;
			case RIGHT:
				this.point = new Point(this.point.x + this.dimension.width, this.point.y);break;
			}
			this.setBounds(this.point.x, this.point.y, this.dimension.width, this.dimension.height);
		}
	}

	@Override
	protected boolean allowMove(Model model) {
		
		return false;
	}
	
	public boolean isEnemy() {
		return isEnemy;
	}

	public void setEnemy(boolean isEnemy) {
		this.isEnemy = isEnemy;
	}
	
}