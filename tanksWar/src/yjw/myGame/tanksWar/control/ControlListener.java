package yjw.myGame.tanksWar.control;

import java.awt.Point;
import java.awt.event.KeyEvent;

import yjw.myGame.tanksWar.model.BorderModel;
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
	 * ����̹���ƶ�
	 */
	public void controlTank() {
		Point point = tank.getPoint();
		int moveSize = tank.getMoveSize();
		DirectionEnum direction = tank.getDirection();
		TypeEnum type = tank.getType();
		if(type == TypeEnum.FRIEND1) {
			if(KeyEvent.VK_W == event.getKeyCode()) {
				direction = DirectionEnum.UP;
				//̹���Ƿ���ײ������model
				if(!MyGameUtil.modelIsOuterBorder(tank, new BorderModel(tank.getMap()), DirectionEnum.UP)
						&& MyGameUtil.collisionModel(tank, DirectionEnum.UP) == null) {
					point.y = point.y - moveSize;
				}
			} else if(KeyEvent.VK_S == event.getKeyCode()) {
				direction = DirectionEnum.DOWN;
				if(!MyGameUtil.modelIsOuterBorder(tank, new BorderModel(tank.getMap()), DirectionEnum.DOWN)
						&& MyGameUtil.collisionModel(tank, DirectionEnum.DOWN) == null) {
					point.y = point.y + moveSize;
				}
			} else if(KeyEvent.VK_A == event.getKeyCode()) {
				direction = DirectionEnum.LEFT;
				if(!MyGameUtil.modelIsOuterBorder(tank, new BorderModel(tank.getMap()), DirectionEnum.LEFT)
						&& MyGameUtil.collisionModel(tank, DirectionEnum.LEFT) == null) {
					point.x = point.x - moveSize;
				}
			} else if(KeyEvent.VK_D == event.getKeyCode()) {
				direction = DirectionEnum.RIGHT;
				if(!MyGameUtil.modelIsOuterBorder(tank, new BorderModel(tank.getMap()), DirectionEnum.RIGHT)
						&& MyGameUtil.collisionModel(tank, DirectionEnum.RIGHT) == null) {
					point.x = point.x + moveSize;
				}
			}
		} else {
			if(KeyEvent.VK_UP == event.getKeyCode()) {
				direction = DirectionEnum.UP;
				if(!MyGameUtil.modelIsOuterBorder(tank, new BorderModel(tank.getMap()), DirectionEnum.UP)
						&& MyGameUtil.collisionModel(tank, DirectionEnum.UP) == null) {
					point.y = point.y - moveSize;
				}
			} else if(KeyEvent.VK_DOWN == event.getKeyCode()) {
				direction = DirectionEnum.DOWN;
				if(!MyGameUtil.modelIsOuterBorder(tank, new BorderModel(tank.getMap()), DirectionEnum.DOWN)
						&& MyGameUtil.collisionModel(tank, DirectionEnum.DOWN) == null) {
					point.y = point.y + moveSize;
				}
			} else if(KeyEvent.VK_LEFT == event.getKeyCode()) {
				direction = DirectionEnum.LEFT;
				if(!MyGameUtil.modelIsOuterBorder(tank, new BorderModel(tank.getMap()), DirectionEnum.LEFT)
						&& MyGameUtil.collisionModel(tank, DirectionEnum.LEFT) == null) {
					point.x = point.x - moveSize;
				}
			} else if(KeyEvent.VK_RIGHT == event.getKeyCode()) {
				direction = DirectionEnum.RIGHT;
				if(!MyGameUtil.modelIsOuterBorder(tank, new BorderModel(tank.getMap()), DirectionEnum.RIGHT)
						&& MyGameUtil.collisionModel(tank, DirectionEnum.RIGHT) == null) {
					point.x = point.x + moveSize;
				}
			}
		}
		tank.setPoint(point);
		tank.setDirection(direction);
	}

	/**
	 * ̹���Զ��ƶ�
	 */
	public void autoMove() {
		Point point = tank.getPoint();
		int moveSize = tank.getMoveSize();
		DirectionEnum direction = tank.getDirection();
		if(tank.isEnemy()) {
			switch (direction) {
			case UP:
				if(MyGameUtil.collisionModel(tank, DirectionEnum.UP) == null) {
					point.y = point.y - moveSize;
				} break;
			case DOWN:
				if(MyGameUtil.collisionModel(tank, DirectionEnum.DOWN) == null) {
					point.y = point.y + moveSize;
				} break;
			case LEFT:
				if(MyGameUtil.collisionModel(tank, DirectionEnum.LEFT) == null) {
					point.x = point.x - moveSize;
				} break;
			case RIGHT:
				if(MyGameUtil.collisionModel(tank, DirectionEnum.RIGHT) == null) {
					point.x = point.x + moveSize;
				} break;
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
