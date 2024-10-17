package entitys.mobs;

import java.awt.Color;
import java.awt.Graphics2D;

import entitys.MobTemplate;

public class Player extends MobTemplate{

	public Player() {
		super(50, 50, 500, 500, 50, Color.gray, 5, 0.2F);
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillRect((int)pos.getVecX(), (int)pos.getVecY(), size.width, size.height);
		
		
	}
	
	
}
