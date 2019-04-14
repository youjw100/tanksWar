package yjw.myGame.tanksWar.control;

import java.util.Random;

import yjw.myGame.tanksWar.model.TankModel;

public class autoAttack implements Runnable {

	private TankModel tank;
	
	public autoAttack(TankModel tank) {
		this.tank = tank;
	}
	
	@Override
	public void run() {
		Random r = new Random();
		//只要坦克活着，就循环
		while(this.tank.isLive()){
			//随机攻击的时间间隔
			int attackTime = r.nextInt(2000);
			try {
				Thread.sleep(attackTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//发射子弹，并让子弹自动运行
			if(this.tank.getBulletNum() > 0) {
				this.tank.initBullet(this.tank.getMap());
				this.tank.getMap().add(this.tank.bullet);
				Thread tankBullet = new Thread(this.tank.bullet);
				tankBullet.start();
				this.tank.setBulletNum(this.tank.getBulletNum() - 1);
			}
		}
	}

}
