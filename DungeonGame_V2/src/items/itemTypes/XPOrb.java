package items.itemTypes;

import java.awt.Color;
import java.awt.Graphics2D;

import items.ItemTemplate;
import main.Main;

public class XPOrb extends ItemTemplate{
	
	int value = 0;

	public XPOrb(float posX, float posY, int value) {
		super(posX, posY);
		this.value = value;
	}	
	
	@Override
	public void collect() {
		Main.gvStorage.player.addXP(value);
		super.collect();
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(new Color(0, 255, 200));
		g.fillOval((int)pos.getVecX(), (int)pos.getVecY(), size.width, size.height);
		g.setColor(Color.black);
		g.drawOval((int)pos.getVecX(), (int)pos.getVecY(), size.width, size.height);
		
	}
	
	@Override
	public void setDefaultValues() {
		value = 20;
		super.setDefaultValues();
	}
}
