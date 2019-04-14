package yjw.myGame.tanksWar.control;

import java.awt.Point;
import java.awt.event.KeyEvent;

import yjw.myGame.tanksWar.model.TankModel;
import yjw.myGame.tanksWar.myEnum.DirectionEnum;
import yjw.myGame.tanksWar.myEnum.TypeEnum;

public class ControlListener implements Runnable{
	
	private KeyEvent event;
	private TankModel tank;
	
	public ControlListener(TankModel tank) {
		this.tank = tank;
	}
	
	/**
	 * 控制坦克移动
	 */
	public void controlTank() {
		Point point = tank.getPoint();
		int moveSize = tank.getMoveSize();
		DirectionEnum direction = tank.getDirection();
		TypeEnum type = tank.getType();
		if(type == TypeEnum.FRIEND1) {
			if(KeyEvent.VK_W == event.getKeyCode()) {
				direction = DirectionEnum.UP;
				point.y = point.y - moveSize;
			} else if(KeyEvent.VK_S == event.getKeyCode()) {
				direction = DirectionEnum.DOWN;
				point.y = point.y + moveSize;
			} else if(KeyEvent.VK_A == event.getKeyCode()) {
				direction = DirectionEnum.LEFT;
				point.x = point.x - moveSize;
			} else if(KeyEvent.VK_D == event.getKeyCode()) {
				direction = DirectionEnum.RIGHT;
				point.x = point.x + moveSize;
			}
		} else {
			if(KeyEvent.VK_UP == event.getKeyCode()) {
				direction = DirectionEnum.UP;
				point.y = point.y - moveSize;
			} else if(KeyEvent.VK_DOWN == event.getKeyCode()) {
				direction = DirectionEnum.DOWN;
				point.y = point.y + moveSize;
			} else if(KeyEvent.VK_LEFT == event.getKeyCode()) {
				direction = DirectionEnum.LEFT;
				point.x = point.x - moveSize;
			} else if(KeyEvent.VK_RIGHT == event.getKeyCode()) {
				direction = DirectionEnum.RIGHT;
				point.x = point.x + moveSize;
			}
		}
		tank.setPoint(point);
		tank.setDirection(direction);
	}

	/**
	 * 坦克自动移动
	 */
	public void autoMove() {
		Point point = tank.getPoint();
		int moveSize = tank.getMoveSize();
		DirectionEnum direction = tank.getDirection();
		if(tank.isEnemy()) {
			switch (direction) {
			case UP: point.y = point.y - moveSize; break;
			case DOWN: point.y = point.y + moveSize; break;
			case LEFT: point.x = point.x - moveSize; break;
			case RIGHT: point.x = point.x + moveSize; break;
			}
			tank.setPoint(point);
		}
	}
	
	@Override
	public void run() {
		synchronized (this) {
			while (tank.isConMove()) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if(tank.isEnemy()) {
					this.autoMove();
				} else {
					this.controlTank();
				}
			}
		}
	}

	public void setEvent(KeyEvent event) {
		this.event = event;
	}
	
}
