package fundamentals;

import java.awt.Dimension;
import java.awt.Graphics2D;

import system.Vector3;

public class SimpleObject {
	
	public Dimension size;
	public Vector3 pos;
	
	
	
	public SimpleObject(Dimension size, float posX, float posY) {
		this.size = size;
		pos = new Vector3(posX, posY, 0);
	}
	
	public void draw(Graphics2D g) {};
}
