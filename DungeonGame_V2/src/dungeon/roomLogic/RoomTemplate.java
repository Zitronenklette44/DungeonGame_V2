package dungeon.roomLogic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import fundamentals.SimpleObject;
import system.interfaces.Enterable;

public class RoomTemplate extends SimpleObject implements Enterable{

	public int roomID;
	public Color color;
	public ArrayList<SimpleObject> features = new ArrayList<SimpleObject>();
	
	public RoomTemplate(Dimension size, float posX, float posY) {
		super(size, posX, posY);
		hasCollision = false;
		
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillRect((int)pos.getVecX(), (int)pos.getVecY(), size.width, size.height);
		
		Iterator<SimpleObject> iterator = features.iterator();
		while(iterator.hasNext()) {
			SimpleObject object = iterator.next();
			object.draw(g);
		}
	}

	@Override
	public void enter() { 
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}
	
	public void addFeature(SimpleObject ob) {
		features.add(ob);

	}
	
	@Override
	public RoomTemplate clone() {
		// TODO Auto-generated method stub
		return (RoomTemplate) super.clone();
	}
	
}
