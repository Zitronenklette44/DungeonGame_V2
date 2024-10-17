package entitys;

import java.awt.Color;
import java.awt.Dimension;
import fundamentals.SimpleObject;
import system.Vector3;

public class MobTemplate extends SimpleObject{

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
		
	
	
	
	
}
