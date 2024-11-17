package fundamentals;

import java.awt.Dimension;
import java.awt.Graphics2D;

import system.Vector3;
import system.interfaces.Collidable;
import system.interfaces.EmitsLight;

public class SimpleObject implements Collidable, EmitsLight{
	
	public Dimension size;
	public Vector3 pos;
	public boolean isColliding = false;
	public boolean hasCollision = true;
//	public boolean isInLight = false;
	public boolean isEmitting  = false;
	public int lightRange = 0;
	public int burnTime = -1;
	
	
	
	public SimpleObject(Dimension size, float posX, float posY) {
		this.size = size;
		pos = new Vector3(posX, posY, 0);
	}
	
	public void draw(Graphics2D g) {}

	@Override
	public void onCollision(SimpleObject ob) {}

	@Override
	public void stopCollision(SimpleObject ob) {}

	@Override
	public void startCollision(SimpleObject ob) {}

	@Override
	public void emitLight() {}

	@Override
	public void burnsOut() {};
}
