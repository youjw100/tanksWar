package yjw.myGame.tanksWar.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JPanel;

import yjw.myGame.tanksWar.map.Map;
import yjw.myGame.tanksWar.myEnum.TypeEnum;

/**
 * 游戏实体模型
 * @author 梦有形心有伴
 *
 */
public abstract class Model extends JPanel{

	private static final long serialVersionUID = 8056762727232848866L;

	//位置
	protected Point point;
	//大小
	protected Dimension dimension;
	//颜色
	protected Color color;
	//类型
	protected TypeEnum type;
	//是否存活
	protected boolean isLive;
	//所属的游戏空间
	protected Map map;
	
	public Model() {
		
	}
	
	public Model(Point point, Dimension dimension, Color color, boolean isLive, Map map) {
		this.point = point;
		this.dimension = dimension;
		this.color = color;
		this.isLive = isLive;
		this.map = map;
	}
	
	protected abstract boolean allowMove(Model model);
	
	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
		this.setBounds(this.point.x, this.point.y, this.dimension.width, this.dimension.height);
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public TypeEnum getType() {
		return type;
	}

	public void setType(TypeEnum type) {
		this.type = type;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
	
}
