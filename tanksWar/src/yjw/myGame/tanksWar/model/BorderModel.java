package yjw.myGame.tanksWar.model;

import java.awt.Color;
import java.awt.Point;

import yjw.myGame.tanksWar.map.Map;
import yjw.myGame.tanksWar.myEnum.TypeEnum;

/**
 * 游戏边界模型（实际不存在，仅储存数据）
 * @author Administrator
 *
 */
public class BorderModel extends Model{
	
	private static final long serialVersionUID = -294512486727908260L;
	
	public BorderModel(Map map) {
		this.map = map;
		this.point = new Point(0, 0);
		this.dimension = this.map.getDimension();
		this.type = TypeEnum.BORDER;
		this.color = Color.BLACK;
		this.isLive = true;
		this.moveSize = 0;
	}
}
