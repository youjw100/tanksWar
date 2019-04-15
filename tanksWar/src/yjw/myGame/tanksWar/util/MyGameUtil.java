package yjw.myGame.tanksWar.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import yjw.myGame.tanksWar.main.MainApp;
import yjw.myGame.tanksWar.map.Map;
import yjw.myGame.tanksWar.model.BorderModel;
import yjw.myGame.tanksWar.model.BulletModel;
import yjw.myGame.tanksWar.model.Model;
import yjw.myGame.tanksWar.model.TankModel;
import yjw.myGame.tanksWar.myEnum.DirectionEnum;
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
	/**
	 * ����Ϸ�ռ�map���ж�ĳ��model��ĳ���������Ƿ����ϰ���
	 * @param map
	 * @param model
	 * @param direction
	 * @return collisionModel �������ֵΪnull�������û���ϰ���
	 */
	public static Model collisionModel(Model model, DirectionEnum direction) {
		Model collisionModel = null;
		//��ȡ��Ϸ�ռ�map�е�������������ұ����ж�
		Component[] allComponent = model.getMap().getComponents();
		for(Component component : allComponent) {
			if(component != null) {
				Model tempModel = (Model)component;
				Dimension tempDimension = tempModel.getDimension();
				//���ݿؼ���С��λ�ã������Ͻǿ�ʼ��˳ʱ���ȡtempModel���е������
				Point tempPoint1 = tempModel.getPoint();
				Point tempPoint2 = new Point(tempPoint1.x + tempDimension.width, tempPoint1.y);
				Point tempPoint3 = new Point(tempPoint1.x + tempDimension.width, tempPoint1.y + tempDimension.height);
				Point tempPoint4 = new Point(tempPoint1.x, tempPoint1.y + tempDimension.height);
				switch(direction) {
				case UP:
					model.setPoint(new Point(model.getPoint().x, model.getPoint().y - model.getMoveSize()));
					if(isPointInModel(tempPoint3, model) || isPointInModel(tempPoint4, model)) {
						collisionModel = tempModel;
					} 
					model.setPoint(new Point(model.getPoint().x, model.getPoint().y + model.getMoveSize()));
					break;
				case DOWN:
					model.setPoint(new Point(model.getPoint().x, model.getPoint().y + model.getMoveSize()));
					if(isPointInModel(tempPoint1, model) || isPointInModel(tempPoint2, model)) {
						collisionModel = tempModel;
					} 
					model.setPoint(new Point(model.getPoint().x, model.getPoint().y - model.getMoveSize()));
					break;
				case LEFT:
					model.setPoint(new Point(model.getPoint().x - model.getMoveSize(), model.getPoint().y));
					if(isPointInModel(tempPoint2, model) || isPointInModel(tempPoint3, model)) {
						collisionModel = tempModel;
					} 
					model.setPoint(new Point(model.getPoint().x + model.getMoveSize(), model.getPoint().y));
					break;
				case RIGHT:
					model.setPoint(new Point(model.getPoint().x + model.getMoveSize(), model.getPoint().y));
					if(isPointInModel(tempPoint1, model) || isPointInModel(tempPoint4, model)) {
						collisionModel = tempModel;
					} 
					model.setPoint(new Point(model.getPoint().x - model.getMoveSize(), model.getPoint().y));
					break;
				}
			}
			if(collisionModel != null) {
				return collisionModel;
			}
		}
		return collisionModel;
	}
	/**
	 * �жϵ��Ƿ���model��
	 * @param point
	 * @param model
	 * @return
	 */
	public static boolean isPointInModel(Point point, Model model) {
		Point modelPoint = model.getPoint();
		Dimension modelDimension = model.getDimension();
		if(point.x >= modelPoint.x && point.x <= modelPoint.x + modelDimension.width
				&& point.y >= modelPoint.y && point.y <= modelPoint.y + modelDimension.height) {
			return true;
		}
		return false;
	}
	/**
	 * �ж�ģ��ĳ�������Ƿ񳬳��߽�
	 * @param model
	 * @param borderModel
	 * @return
	 */
	public static boolean modelIsOuterBorder(Model model, BorderModel borderModel, DirectionEnum direction) {
		Dimension modelDimension = model.getDimension();
		//���ݿؼ���С��λ�ã������Ͻǿ�ʼ��˳ʱ���ȡmodel���е������
		Point modelPoint1 = model.getPoint();
		Point modelPoint2 = new Point(modelPoint1.x + modelDimension.width, modelPoint1.y);
		Point modelPoint3 = new Point(modelPoint1.x + modelDimension.width, modelPoint1.y + modelDimension.height);
		Point modelPoint4 = new Point(modelPoint1.x, modelPoint1.y + modelDimension.height);
		
		switch(direction) {
		case UP:
			model.setPoint(new Point(model.getPoint().x, model.getPoint().y - model.getMoveSize()));
			if(!isPointInModel(modelPoint1, borderModel) || !isPointInModel(modelPoint2, borderModel)) {
				model.setPoint(new Point(model.getPoint().x, model.getPoint().y + model.getMoveSize()));
				return true;
			}
			model.setPoint(new Point(model.getPoint().x, model.getPoint().y + model.getMoveSize()));
			break;
		case DOWN:
			model.setPoint(new Point(model.getPoint().x, model.getPoint().y + model.getMoveSize()));
			if(!isPointInModel(modelPoint3, borderModel) || !isPointInModel(modelPoint4, borderModel)) {
				model.setPoint(new Point(model.getPoint().x, model.getPoint().y - model.getMoveSize()));
				return true;
			}
			model.setPoint(new Point(model.getPoint().x, model.getPoint().y - model.getMoveSize()));
			break;
		case LEFT:
			model.setPoint(new Point(model.getPoint().x - model.getMoveSize(), model.getPoint().y));
			if(!isPointInModel(modelPoint1, borderModel) || !isPointInModel(modelPoint4, borderModel)) {
				model.setPoint(new Point(model.getPoint().x + model.getMoveSize(), model.getPoint().y));
				return true;
			}
			model.setPoint(new Point(model.getPoint().x + model.getMoveSize(), model.getPoint().y));
			break;
		case RIGHT:
			model.setPoint(new Point(model.getPoint().x + model.getMoveSize(), model.getPoint().y));
			if(!isPointInModel(modelPoint2, borderModel) || !isPointInModel(modelPoint3, borderModel)) {
				model.setPoint(new Point(model.getPoint().x - model.getMoveSize(), model.getPoint().y));
				return true;
			}
			model.setPoint(new Point(model.getPoint().x - model.getMoveSize(), model.getPoint().y));
			break;
		}
		return false;
	}
}