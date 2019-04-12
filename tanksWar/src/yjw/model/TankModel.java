package yjw.model;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import yjw.myEnum.DirectionEnum;
import yjw.myEnum.TankTypeEnum;

public class TankModel extends JPanel{
	
	private static final long serialVersionUID = 4503796111367164433L;

	protected TankTypeEnum type;
	
	protected Color color;

	protected DirectionEnum direction;
	
	public TankModel(TankTypeEnum type) {
		this.type = type;
		this.color = type.getTankColor();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
	
	public static void main(String[] args) {

	}
}
