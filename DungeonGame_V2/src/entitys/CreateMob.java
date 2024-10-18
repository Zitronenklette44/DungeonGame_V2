package entitys;

import java.awt.Dimension;

import entitys.mobs.TestObject;
import main.Main;

public class CreateMob {
	
	public static void createTestObject(Dimension size, float posX, float posY) {
		Main.gvStorage.screenController.addObject(new TestObject(size, posX, posY));

	}
	
	
	
	
}
