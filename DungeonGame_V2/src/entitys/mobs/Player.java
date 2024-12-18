package entitys.mobs;

import java.awt.Color;
import java.awt.Graphics2D;
import entitys.MobTemplate;
import system.interfaces.InterfaceLogic;

public class Player extends MobTemplate{

	public int currentXP = 0;
	public int currentLvl = 0;
	public int nextLvlThreashold = 100;

	public Player() {
		super(50, 50, 0, 0, 50, Color.gray, 5, 0.2F);
		isEmitting = true;
		lightRange = 300;
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillRect((int)pos.getVecX() - size.width/2, (int)pos.getVecY() - size.height/2, size.width, size.height);
		
		
	}

	@Override
	public void emitLight() {
		InterfaceLogic.emitLight(this);
	}

	@Override
	public void burnsOut() {
		InterfaceLogic.burnsOut(this);
	}
	
	public void addXP(int value) {
		currentXP += value;
		if(currentXP >= nextLvlThreashold) {
			currentLvl++;
			currentXP -= nextLvlThreashold;
			nextLvlThreashold = currentLvl * 100;
		}

	}
	
	
}
