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
		//ֻҪ̹�˻��ţ���ѭ��
		while(this.tank.isLive()){
			//���������ʱ����
			int attackTime = r.nextInt(2000);
			try {
				Thread.sleep(attackTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//�����ӵ��������ӵ��Զ�����
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
