package entitys.mobs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import entitys.MobTemplate;
import main.Main;

public class TestObject extends MobTemplate{

	public TestObject(Dimension size, float posX, float posY) {
		super(size, posX, posY, 0, Color.orange.darker(), Main.gvStorage.player.maxSpeed, Main.gvStorage.player.accelerationRate);
	}
	
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillRect((int)pos.getVecX(), (int)pos.getVecY(), size.width, size.height);
	}
	
}
