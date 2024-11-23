package entitys.mobs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import entitys.MobTemplate;
import fundamentals.SimpleObject;

public class TestDummy extends MobTemplate {

	public TestDummy(Dimension size, float posX, float posY) {
		super(size, posX, posY, Integer.MAX_VALUE, Color.cyan, 0, 0);
	}
	
	@Override
	public void onCollision(SimpleObject ob) {
		super.onCollision(ob);
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillRect((int)pos.getVecX(), (int)pos.getVecY(), size.width, size.height);
	}

}
