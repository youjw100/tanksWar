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
public class Model extends JPanel{

	private static final long serialVersionUID = 8056762727232848866L;

	//模型位置
	protected Point point;
	//模型大小
	protected Dimension dimension;
	//模型颜色
	protected Color color;
	//模型类型
	protected TypeEnum type;
	//模型是否存活
	protected boolean isLive;
	//模型所属的游戏空间
	protected Map map;
	//模型一次移动距离（根据模型大小确定）
	protected int moveSize;
	
	public Model(Point point, Dimension dimension, TypeEnum type, Map map, int moveSize) {
		this.point = point;
		this.dimension = dimension;
		this.type = type;
		this.map = map;
		this.moveSize = moveSize;
		this.isLive = true;
		
		this.setLayout(null);
		this.setBounds(this.point.x, this.point.y, this.dimension.width, this.dimension.height);
		this.setBackground(Color.BLACK);
		this.setVisible(true);
	}
	
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

	public int getMoveSize() {
		return moveSize;
	}

	public void setMoveSize(int moveSize) {
		this.moveSize = moveSize;
	}

}
