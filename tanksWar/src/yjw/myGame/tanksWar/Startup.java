package yjw.myGame.tanksWar;

import yjw.myGame.tanksWar.main.MainApp;
import yjw.myGame.tanksWar.myEnum.TypeEnum;
import yjw.myGame.tanksWar.util.MyGameUtil;

public class Startup {
	public static void main(String[] args) {
		//���������沢������Ϸ����
		MainApp mainApp = MainApp.getMainApp();
//		Thread mainThread = new Thread(mainApp);
//		mainThread.start();
		
		//��ʼ����Ϸ����
		mainApp.map = mainApp.getMap(mainApp);
		//����Ϸ�������������
		mainApp.add(mainApp.map);
		
		//��ʼ���ҷ�̹��
		mainApp.hero1 = MyGameUtil.getHero(mainApp.map, TypeEnum.FRIEND1);
		mainApp.hero2 = MyGameUtil.getHero(mainApp.map, TypeEnum.FRIEND2);
		//���ҷ�̹�˼�����Ϸ������
		mainApp.map.add(mainApp.hero1);
		mainApp.map.add(mainApp.hero2);
		//�����ҷ�̹��
		mainApp.controlTankListener(mainApp, mainApp.hero1.getType());
		mainApp.controlTankListener(mainApp, mainApp.hero2.getType());
		
		//��ʼ���з�̹��
		mainApp.enemy1 = MyGameUtil.getEnemy(mainApp.map);
		mainApp.enemy2 = MyGameUtil.getEnemy(mainApp.map);
		mainApp.enemy3 = MyGameUtil.getEnemy(mainApp.map);
		mainApp.enemy4 = MyGameUtil.getEnemy(mainApp.map);
		//���з�̹�˼�����Ϸ������
		mainApp.map.add(mainApp.enemy1);
		mainApp.map.add(mainApp.enemy2);
		mainApp.map.add(mainApp.enemy3);
		mainApp.map.add(mainApp.enemy4);
		//�����з�̹��
		new Thread(mainApp.enemy1).start();
		new Thread(mainApp.enemy2).start();
		new Thread(mainApp.enemy3).start();
		new Thread(mainApp.enemy4).start();
		
	}
}
