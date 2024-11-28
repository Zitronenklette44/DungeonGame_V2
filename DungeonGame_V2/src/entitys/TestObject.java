package entitys;

import java.awt.Color;
import java.awt.Dimension;
import main.Main;

public class TestObject extends MobTemplate{

	public TestObject(Dimension size, float posX, float posY) {
		super(size, posX, posY, 0, Color.orange.darker(), Main.gvStorage.player.maxSpeed, Main.gvStorage.player.accelerationRate);
	}
	
}
