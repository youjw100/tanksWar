package yjw.myGame.tanksWar.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import yjw.myGame.tanksWar.map.Map;
import yjw.myGame.tanksWar.model.BulletModel;
import yjw.myGame.tanksWar.model.Model;
import yjw.myGame.tanksWar.model.TankModel;
import yjw.myGame.tanksWar.myEnum.DirectionEnum;
import yjw.myGame.tanksWar.myEnum.TypeEnum;

public class MyGameUtil {
	/**
	 * 固定位置创建我方坦克
	 * @param map
	 * @param type
	 * @return
	 */
	public static TankModel getHero(Map map, TypeEnum type) {
		Point point = null;
		Dimension dimension = null;
		//获取游戏区大小
		int mapWidth = map.getWidth();
		int mapHeight = map.getHeight();
		//根据游戏区大小算出坦克大小
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
	 * 随机位置创建随机类型的敌方坦克
	 * @param map
	 * @return
	 */
	public static TankModel getEnemy(Map map) {
		Point point = null;
		Dimension dimension = null;
		TypeEnum type = null;
		//获取游戏区大小
		int mapWidth = map.getWidth();
		int mapHeight = map.getHeight();
		//根据游戏区大小算出坦克大小
		int tankWidth = mapWidth/20;
		int tankHeight = mapHeight/20;
		Random r = new Random();
		//随机位置
		int pos = r.nextInt(3);
		switch(pos) {
		case 0:
			point = new Point(0, 0);break;
		case 1:
			point = new Point(mapWidth/2-tankWidth/2, 0);break;
		case 2:
			point = new Point(mapWidth-tankWidth, 0);break;
		}
		//随机类型
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
	 * 获取所有坦克
	 * @param mainApp
	 * @return
	 */
	public static ArrayList<TankModel> getAllTank(Map map) {
		ArrayList<TankModel> allTank = new ArrayList<TankModel>();
		Component[] allComponent = map.getComponents();
		for(Component component : allComponent) {
			if(component != null && component instanceof TankModel) {
				TankModel tank = (TankModel)component;
				if(tank.isLive()) {
					allTank.add(tank);
				}
			}
		}
		return allTank;
	}
	/**
	 * 获取游戏空间中的所有子弹
	 * @param mainApp
	 * @return
	 */
	public static ArrayList<BulletModel> getAllBullet(Map map) {
		ArrayList<BulletModel> allBullet = new ArrayList<BulletModel>();
		Component[] allComponent = map.getComponents();
		for(Component component : allComponent) {
			if(component != null && component instanceof BulletModel) {
				BulletModel bullet = (BulletModel)component;
				if(bullet.isLive()) {
					allBullet.add(bullet);
				}
			}
		}
		return allBullet;
	}
	/**
	 * 获取所有我方坦克
	 * @param mainApp
	 * @return
	 */
	public static ArrayList<TankModel> getAllHero(Map map) {
		ArrayList<TankModel> allHero = new ArrayList<TankModel>();
		ArrayList<TankModel> allTank = getAllTank(map);
		for(TankModel hero : allTank) {
			if(hero != null && hero.isLive() && !hero.isEnemy()) {
				allHero.add(hero);
			}
		}
		return allHero;
	}
	/**
	 * 获取所有敌方坦克
	 * @param mainApp
	 * @return
	 */
	public static ArrayList<TankModel> getAllEnemy(Map map) {
		ArrayList<TankModel> allEnemy = new ArrayList<TankModel>();
		ArrayList<TankModel> allTank = getAllTank(map);
		for(TankModel enemy : allTank) {
			if(enemy != null && enemy.isLive() && enemy.isEnemy()) {
				allEnemy.add(enemy);
			}
		}
		return allEnemy;
	}
	/**
	 * 清除游戏界面死亡的模型
	 * @param mainApp
	 */
	public static void removeDeathModel(Map map) {
		Component[] allComponent = map.getComponents();
		for(Component component : allComponent) {
			if(component != null && component instanceof Model) {
				Model model = (Model)component;
				if(!model.isLive()) {
					map.remove(model);
				}
			}
		}
	}
	/**
	 * 在游戏空间map内判断某个checkModel在某个方向上是否有model
	 * @param map
	 * @param model
	 * @param direction
	 * @return collisionModel 如果返回值为null，则代表没有障碍物
	 */
	public static Model collisionModel(Model checkModel, DirectionEnum direction) {
		Dimension modelDimension = checkModel.getDimension();
		Model collisionModel = null;
		//获取游戏空间map中的所有组件，并且遍历判断
		Component[] allComponent = checkModel.getMap().getComponents();
		for(Component component : allComponent) {
			if(component != null && component instanceof Model) {
				Model tempModel = (Model)component;
				if(isSameModel(checkModel, tempModel)) {
					continue;
				}
				//根据checkModel大小和位置，从左上角开始，顺时针获取checkModel所有点的坐标
				int moveSize = checkModel.getMoveSize();
				Point checkModelPoint1 = checkModel.getPoint();
				Point checkModelPoint2 = new Point(checkModelPoint1.x + modelDimension.width, checkModelPoint1.y);
				Point checkModelPoint3 = new Point(checkModelPoint1.x + modelDimension.width, checkModelPoint1.y + modelDimension.height);
				Point checkModelPoint4 = new Point(checkModelPoint1.x, checkModelPoint1.y + modelDimension.height);
				switch(direction) {
				case UP:
					if(isPointInModel(new Point(checkModelPoint1.x, checkModelPoint1.y - moveSize), tempModel) 
							|| isPointInModel(new Point(checkModelPoint2.x, checkModelPoint2.y - moveSize), tempModel)) {
						collisionModel = tempModel;
						checkModel.setPoint(new Point(checkModel.getPoint().x, collisionModel.getPoint().y + collisionModel.getDimension().height + 1));
					} 
					break;
				case DOWN:
					if(isPointInModel(new Point(checkModelPoint3.x, checkModelPoint3.y + moveSize), tempModel) 
							|| isPointInModel(new Point(checkModelPoint4.x, checkModelPoint4.y + moveSize), tempModel)) {
						collisionModel = tempModel;
						checkModel.setPoint(new Point(checkModel.getPoint().x, collisionModel.getPoint().y - checkModel.getDimension().height - 1));
					} 
					break;
				case LEFT:
					if(isPointInModel(new Point(checkModelPoint1.x - moveSize, checkModelPoint1.y), tempModel) 
							|| isPointInModel(new Point(checkModelPoint4.x - moveSize, checkModelPoint4.y), tempModel)) {
						collisionModel = tempModel;
						checkModel.setPoint(new Point(collisionModel.getPoint().x + collisionModel.getDimension().width + 1, checkModel.getPoint().y));
					} 
					break;
				case RIGHT:
					if(isPointInModel(new Point(checkModelPoint2.x + moveSize, checkModelPoint2.y), tempModel) 
							|| isPointInModel(new Point(checkModelPoint3.x + moveSize, checkModelPoint3.y), tempModel)) {
						collisionModel = tempModel;
						checkModel.setPoint(new Point(collisionModel.getPoint().x - checkModel.getDimension().width - 1, checkModel.getPoint().y));
					} 
					break;
				}
			}
		}
		return collisionModel;
	}
	/**
	 * 判断点是否在model内
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
	 * 判断模型是否超出边界，如果超出，则复位
	 * @param model
	 * @param borderModel
	 * @return
	 */
	public static boolean modelIsOuterBorder(Model model, DirectionEnum direction) {
		 if(direction == DirectionEnum.UP
				&& model.getPoint().y - model.getMoveSize() < 0) {
			model.setPoint(new Point(model.getPoint().x, 0));
			return true;
		} else if(direction == DirectionEnum.DOWN
				&& model.getPoint().y + model.getMoveSize() > model.getMap().getDimension().height - model.getDimension().height) {
			model.setPoint(new Point(model.getPoint().x, model.getMap().getDimension().height - model.getDimension().height));
			return true;
		} else if(direction == DirectionEnum.LEFT
				&& model.getPoint().x - model.getMoveSize() < 0) {
			return true;
		} else if(direction == DirectionEnum.RIGHT
				&& model.getPoint().x + model.getMoveSize() > model.getMap().getDimension().width - model.getDimension().width) {
			model.setPoint(new Point(model.getMap().getDimension().width - model.getDimension().width, model.getPoint().y));
			return true;
		}
		return false;
	}
	/**
	 * 根据模型的大小、位置、类型判断两个模型是否为同一个模型
	 * @param model1
	 * @param model2
	 * @return
	 */
	public static boolean isSameModel(Model model1, Model model2) {
		if(model1.getPoint() == model2.getPoint() 
				&& model1.getDimension() == model2.getDimension()
				&& model1.getType() == model2.getType()) {
			return true;
		}
		return false;
	}
}