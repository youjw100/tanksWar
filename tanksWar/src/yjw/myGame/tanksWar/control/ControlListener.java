package yjw.myGame.tanksWar.control;

import java.awt.Point;
import java.awt.event.KeyEvent;

import yjw.myGame.tanksWar.model.BulletModel;
import yjw.myGame.tanksWar.model.Model;
import yjw.myGame.tanksWar.model.TankModel;
import yjw.myGame.tanksWar.myEnum.DirectionEnum;
import yjw.myGame.tanksWar.myEnum.TypeEnum;
import yjw.myGame.tanksWar.util.MyGameUtil;

public class ControlListener implements Runnable{
	
	private KeyEvent event;
	private TankModel tank;
	
	public ControlListener(TankModel tank) {
		this.tank = tank;
	}
	
	/**
	 * 控制坦克移动
	 */
	public void controlTankMove() {
		DirectionEnum direction = this.tank.getDirection();
		if(KeyEvent.VK_W == this.event.getKeyCode()
				|| KeyEvent.VK_UP == this.event.getKeyCode()) {
			direction = DirectionEnum.UP;
		} else if(KeyEvent.VK_S == this.event.getKeyCode()
				|| KeyEvent.VK_DOWN == this.event.getKeyCode()) {
			direction = DirectionEnum.DOWN;
		} else if(KeyEvent.VK_A == this.event.getKeyCode()
				|| KeyEvent.VK_LEFT == this.event.getKeyCode()) {
			direction = DirectionEnum.LEFT;
		} else if(KeyEvent.VK_D == this.event.getKeyCode()
				|| KeyEvent.VK_RIGHT == this.event.getKeyCode()) {
			direction = DirectionEnum.RIGHT;
		}
		this.tank.setDirection(direction);
		move(direction);
	}
	
	@Override
	public void run() {
		synchronized(this){
			while (this.tank.isConMove() && this.tank.isLive()) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if(this.tank.isEnemy()) {
					move(this.tank.getDirection());
				} else {
					this.controlTankMove();
				}
			}
		}
	}

	/**
	 * 坦克朝指定方向移动
	 * @param tank
	 * @param direction
	 */
	private void move(DirectionEnum direction) {
		Point point = this.tank.getPoint();
		int moveSize = this.tank.getMoveSize();
		boolean isoutBorder = MyGameUtil.modelIsOuterBorder(this.tank, direction);
		Model collisionModel = MyGameUtil.collisionModel(this.tank, direction);
		if(!isoutBorder && collisionModel == null) {
			switch (direction) {
			case UP:
				this.tank.setPoint(new Point(point.x, point.y - moveSize));break;
			case DOWN:
				this.tank.setPoint(new Point(point.x, point.y + moveSize));break;
			case LEFT:
				this.tank.setPoint(new Point(point.x - moveSize, point.y));break;
			case RIGHT:
				this.tank.setPoint(new Point(point.x + moveSize, point.y));break;
			}
		} else if(collisionModel instanceof BulletModel){
			BulletModel bullet = (BulletModel) collisionModel;
			if(this.tank.isEnemy()) {
				if(tankIsDeath(bullet, this.tank)) {
					switch(tank.getType()) {
					case ENEMY1:
						this.tank.setLive(!tankIsDeath(bullet, this.tank));break;
					case ENEMY2:
						this.tank.setType(TypeEnum.ENEMY1);break;
					case ENEMY3:
						this.tank.setType(TypeEnum.ENEMY2);break;
					case ENEMY4:
						this.tank.setType(TypeEnum.ENEMY3);break;
					default:break;
					}
				}
			} else {
				this.tank.setLive(tankIsDeath(bullet, this.tank));
			}
		}
	}
	
	private boolean tankIsDeath(BulletModel bullet, TankModel tank) {
		if((!bullet.isEnemy() && tank.isEnemy())
				|| (bullet.isEnemy() && !tank.isEnemy())) {
			return true;
		}
		return false;
	}
	
	public void setEvent(KeyEvent event) {
		this.event = event;
	}
	
}
