package entitys;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import fundamentals.SimpleObject;
import system.Vector3;
import system.interfaces.Damageable;

public class MobTemplate extends SimpleObject implements Damageable{

	public int HP;
	public int maxHP;
	public Color color;
	public float maxSpeed;
	public float speed;
	public float accelerationRate;
	public Vector3 movement = new Vector3(0, 0, 0);
	
	
	
	public MobTemplate(Dimension size, float posX, float posY, int maxHP, Color color, float maxSpeed, float accelerationRate) {
		super(size, posX, posY);
		this.HP = maxHP;
		this.maxHP = maxHP;
		this.color = color;
		this.maxSpeed = maxSpeed;
		this.accelerationRate = accelerationRate;
	}
	
	public MobTemplate(int Width, int Height, float posX, float posY, int maxHP, Color color,float maxSpeed, float accelerationRate) {
		this(new Dimension(Width, Height), posX, posY, maxHP, color, maxSpeed, accelerationRate);
	}
	
	@Override
	public MobTemplate clone() {
		return (MobTemplate) super.clone();
	}
	
	@Override
	public void onCollision(SimpleObject ob) {
		if(hasCollision && ob.hasCollision) {
			pos.add(movement.getReverse());
			speed = 0;
			movement = Vector3.zeroVec();
		}
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillRect((int)pos.getVecX()-size.width/2, (int)pos.getVecY()-size.height/2, size.width, size.height);
	}

	@Override
	public void takeDamage(int damage) {}

	@Override
	public void onDestruction() {}
	
}
