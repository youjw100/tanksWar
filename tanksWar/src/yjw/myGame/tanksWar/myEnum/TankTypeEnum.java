package yjw.myGame.tanksWar.myEnum;

import java.awt.Color;

/**
 * 坦克
 * 
 * FRIEND：我方坦克（两种类型）
 * ENEMY：敌方坦克（四种类型）
 * 
 * @author 梦有形心有伴
 *
 */
public enum TankTypeEnum {
	FRIEND1(false, Color.MAGENTA), 
	FRIEND2(false, Color.BLUE), 
	ENEMY1(true, Color.CYAN), 
	ENEMY2(true, Color.GREEN), 
	ENEMY3(true, Color.LIGHT_GRAY), 
	ENEMY4(true, Color.YELLOW);
	
	//是否是敌方坦克
	private boolean isEnemy;
	//坦克颜色
	private Color color;
	
	private TankTypeEnum(boolean isEnemy, Color color) {
		this.isEnemy = isEnemy;
		this.color = color;
	}
	
	public boolean isEnemy() {
		return this.isEnemy;
	}
	
	public Color getTankColor() {
		return this.color;
	}
}
