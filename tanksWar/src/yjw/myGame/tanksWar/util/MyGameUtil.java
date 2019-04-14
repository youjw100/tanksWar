package yjw.myGame.tanksWar.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import yjw.myGame.tanksWar.main.MainApp;
import yjw.myGame.tanksWar.map.Map;
import yjw.myGame.tanksWar.model.BulletModel;
import yjw.myGame.tanksWar.model.Model;
import yjw.myGame.tanksWar.model.TankModel;
import yjw.myGame.tanksWar.myEnum.TypeEnum;

public class MyGameUtil {
	/**
	 * �̶�λ�ô����ҷ�̹��
	 * @param map
	 * @param type
	 * @return
	 */
	public static TankModel getHero(Map map, TypeEnum type) {
		Point point = null;
		Dimension dimension = null;
		//��ȡ��Ϸ����С
		int mapWidth = map.getWidth();
		int mapHeight = map.getHeight();
		//������Ϸ����С���̹�˴�С
		int tankWidth = mapWidth/20;
		int tankHeight = mapHeight/20;
		if(type == TypeEnum.FRIEND1) {
			point = new Point(mapWidth/3, mapHeight-tankHeight);
		} 
		if(type == TypeEnum.FRIEND2) {
			point = new Point(mapWidth*2/3, mapHeight-tankHeight);
		}
		dimension = new Dimension(tankWidth, tankHeight);
		TankModel hero = new TankModel(point, dimension, type, map);
		return hero;
	}

	/**
	 * ���λ�ô���������͵ĵз�̹��
	 * @param map
	 * @return
	 */
	public static TankModel getEnemy(Map map) {
		Point point = null;
		Dimension dimension = null;
		TypeEnum type = null;
		//��ȡ��Ϸ����С
		int mapWidth = map.getWidth();
		int mapHeight = map.getHeight();
		//������Ϸ����С���̹�˴�С
		int tankWidth = mapWidth/20;
		int tankHeight = mapHeight/20;
		Random r = new Random();
		//���λ��
		int pos = r.nextInt(3);
		switch(pos) {
		case 0:
			point = new Point(0, 0);break;
		case 1:
			point = new Point(mapWidth/2-tankWidth/2, 0);break;
		case 2:
			point = new Point(mapWidth-tankWidth, 0);break;
		}
		//�������
		int rType = r.nextInt(4);
		switch (rType) {
		case 0:
			type = TypeEnum.ENEMY1;break;
		case 1:
			type = TypeEnum.ENEMY2;break;
		case 2:
			type = TypeEnum.ENEMY3;break;
		case 3:
			type = TypeEnum.ENEMY4;break;
		}
		dimension = new Dimension(tankWidth, tankHeight);
		TankModel enemy = new TankModel(point, dimension, type, map);
		return enemy;
	}
	/**
	 * ��ȡ��Ϸ�ռ��е������ӵ�
	 * @param mainApp
	 * @return
	 */
	public static ArrayList<BulletModel> getAllBullet(Map map) {
		Component[] allComponent = map.getComponents();
		ArrayList<BulletModel> allBullet = new ArrayList<BulletModel>();
		for(Component component : allComponent) {
			if(component != null) {
				Model model = (Model)component;
				if(model.isLive()) {
					if(model.getType() == TypeEnum.BULLET) {
						allBullet.add((BulletModel) model);
					}
				}
			}
		}
		return allBullet;
	}
	/**
	 * ��ȡ�����ҷ�̹��
	 * @param mainApp
	 * @return
	 */
	public static ArrayList<TankModel> getAllHero(MainApp mainApp) {
		ArrayList<TankModel> allHero = new ArrayList<TankModel>();
		if(mainApp.hero1.isLive()) {
			allHero.add(mainApp.hero1);
		}
		if(mainApp.hero2.isLive()) {
			allHero.add(mainApp.hero2);
		}
		return allHero;
	}
	/**
	 * ��ȡ���ез�̹��
	 * @param mainApp
	 * @return
	 */
	public static ArrayList<TankModel> getAllEnemy(MainApp mainApp) {
		ArrayList<TankModel> allEnemy = new ArrayList<TankModel>();
		if(mainApp.enemy1.isLive()) {
			allEnemy.add(mainApp.enemy1);
		}
		if(mainApp.enemy2.isLive()) {
			allEnemy.add(mainApp.enemy2);
		}
		if(mainApp.enemy3.isLive()) {
			allEnemy.add(mainApp.enemy3);
		}
		if(mainApp.enemy4.isLive()) {
			allEnemy.add(mainApp.enemy4);
		}
		return allEnemy;
	}
	/**
	 * ��ȡ����̹��
	 * @param mainApp
	 * @return
	 */
	public static ArrayList<TankModel> getAllTank(MainApp mainApp) {
		ArrayList<TankModel> allTank = new ArrayList<TankModel>();
		if(mainApp.hero1.isLive()) {
			allTank.add(mainApp.hero1);
		}
		if(mainApp.hero2.isLive()) {
			allTank.add(mainApp.hero2);
		}
		if(mainApp.enemy1.isLive()) {
			allTank.add(mainApp.enemy1);
		}
		if(mainApp.enemy2.isLive()) {
			allTank.add(mainApp.enemy2);
		}
		if(mainApp.enemy3.isLive()) {
			allTank.add(mainApp.enemy3);
		}
		if(mainApp.enemy4.isLive()) {
			allTank.add(mainApp.enemy4);
		}
		return allTank;
	}
	/**
	 * �����Ϸ����������ģ��
	 * @param mainApp
	 */
	public static void removeDeathModel(MainApp mainApp) {
		Component[] allComponent = mainApp.map.getComponents();
		for(Component component : allComponent) {
			if(component != null) {
				Model model = (Model)component;
				if(!model.isLive()) {
					mainApp.map.remove(model);
				}
			}
		}
	}
}
