package items;

import java.awt.Dimension;
import java.awt.Graphics2D;

import fundamentals.SimpleObject;
import main.Main;
import system.MyConsole;
import system.interfaces.Collectable;

public class ItemTemplate extends SimpleObject implements Collectable{
	
	int count = 1;
	
	public ItemTemplate(float posX, float posY) {
		super(new Dimension(25, 25), posX, posY);
		hasCollision = false;
	}
	
	@Override
	public ItemTemplate clone() {
		return (ItemTemplate) super.clone();
	}

	@Override
	public void collect() {
		MyConsole.logInfo("collecting");
		remove();
	}
	
	@Override
	public void remove() {
		Main.gvStorage.itemManager.removeFloorItem(this);
		
	}
	
	@Override
	public void onCollision(SimpleObject ob) {
		collect();
		super.onCollision(ob);
	}
	
	
	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
	}
	
	public void merge(ItemTemplate item) {
		count += item.count;
	}
	
}
