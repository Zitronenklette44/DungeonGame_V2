package entitys;

import java.awt.Dimension;

import entitys.mobs.TestObject;
import main.Main;

public class CreateMob {
	
	public static void createTestObject() {
		Main.gvStorage.screenController.addObject(new TestObject(new Dimension(50,100), 200, 300));

	}
	
	
	
	
}
