package entitys;

import java.awt.Color;
import java.awt.Dimension;

import fundamentals.SimpleObject;
import system.MyConsole;

public class ColorSwitcher extends MobTemplate{

	public ColorSwitcher(Dimension size, float posX, float posY) {
		super(size, posX, posY, 0, Color.magenta, 0, 0);
		isEmitting = true;
		lightRange = 300;
	}
	
	
	@Override
	public void startCollision(SimpleObject ob) {
		super.onCollision(ob);
		color = Color.black;
		MyConsole.logWarning("changed Colors to: "+ color);
	}
	
	@Override
	public void stopCollision(SimpleObject ob) {
		super.stopCollision(ob);
		color = Color.magenta;
		MyConsole.logWarning("changed Colors to: "+ color);
	}
	
	
}
