package yjw.myGame.tanksWar.myEnum;

import java.awt.Color;

/**
 * ̹��
 * 
 * FRIEND���ҷ�̹�ˣ��������ͣ�
 * ENEMY���з�̹�ˣ��������ͣ�
 * 
 * @author ���������а�
 *
 */
public enum TankTypeEnum {
	FRIEND1(false, Color.MAGENTA), 
	FRIEND2(false, Color.BLUE), 
	ENEMY1(true, Color.CYAN), 
	ENEMY2(true, Color.GREEN), 
	ENEMY3(true, Color.LIGHT_GRAY), 
	ENEMY4(true, Color.YELLOW);
	
	//�Ƿ��ǵз�̹��
	private boolean isEnemy;
	//̹����ɫ
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
