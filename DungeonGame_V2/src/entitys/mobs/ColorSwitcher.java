package entitys.mobs;

import java.awt.Color;
import java.awt.Dimension;

import entitys.MobTemplate;
import fundamentals.SimpleObject;
import system.Logger;

public class ColorSwitcher extends MobTemplate{

	public ColorSwitcher(Dimension size, float posX, float posY) {
		super(size, posX, posY, 0, Color.magenta, 0, 0);
	}
	
	
	@Override
	public void startCollision(SimpleObject ob) {
		super.onCollision(ob);
		color = Color.black;
		Logger.logWarning("changed Colors to: "+ color);
	}
	
	@Override
	public void stopCollision(SimpleObject ob) {
		super.stopCollision(ob);
		color = Color.magenta;
		Logger.logWarning("changed Colors to: "+ color);
	}
	
	
}
