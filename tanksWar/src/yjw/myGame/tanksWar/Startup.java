package yjw.myGame.tanksWar;

import yjw.myGame.tanksWar.main.MainApp;
import yjw.myGame.tanksWar.myEnum.TypeEnum;
import yjw.myGame.tanksWar.util.MyGameUtil;

public class Startup {
	public static void main(String[] args) {
		//创建主界面并启动游戏规则
		MainApp mainApp = MainApp.getMainApp();
//		Thread mainThread = new Thread(mainApp);
//		mainThread.start();
		
		//初始化游戏界面
		mainApp.map = mainApp.getMap(mainApp);
		//将游戏界面放入主界面
		mainApp.add(mainApp.map);
		
		//初始化我方坦克
		mainApp.hero1 = MyGameUtil.getHero(mainApp.map, TypeEnum.FRIEND1);
		mainApp.hero2 = MyGameUtil.getHero(mainApp.map, TypeEnum.FRIEND2);
		//将我方坦克加入游戏界面中
		mainApp.map.add(mainApp.hero1);
		mainApp.map.add(mainApp.hero2);
		//控制我方坦克
		mainApp.controlTankListener(mainApp, mainApp.hero1.getType());
		mainApp.controlTankListener(mainApp, mainApp.hero2.getType());
		
		//初始化敌方坦克
		mainApp.enemy1 = MyGameUtil.getEnemy(mainApp.map);
		mainApp.enemy2 = MyGameUtil.getEnemy(mainApp.map);
		mainApp.enemy3 = MyGameUtil.getEnemy(mainApp.map);
		mainApp.enemy4 = MyGameUtil.getEnemy(mainApp.map);
		//将敌方坦克加入游戏界面中
		mainApp.map.add(mainApp.enemy1);
		mainApp.map.add(mainApp.enemy2);
		mainApp.map.add(mainApp.enemy3);
		mainApp.map.add(mainApp.enemy4);
		//启动敌方坦克
		new Thread(mainApp.enemy1).start();
		new Thread(mainApp.enemy2).start();
		new Thread(mainApp.enemy3).start();
		new Thread(mainApp.enemy4).start();
		
	}
}
