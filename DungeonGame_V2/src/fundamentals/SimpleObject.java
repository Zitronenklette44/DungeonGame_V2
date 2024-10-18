package fundamentals;

import java.awt.Dimension;
import java.awt.Graphics2D;

import system.Vector3;
import system.interfaces.Collidable;

public class SimpleObject implements Collidable{
	
	public Dimension size;
	public Vector3 pos;
	public boolean isColliding = false;
	public boolean hasCollision = true;
	
	
	
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
	public void startCollision(SimpleObject ob) {};
}
