package entitys;

import java.awt.Dimension;

import entitys.mobs.ColorSwitcher;
import entitys.mobs.TestObject;
import main.Main;

public class CreateMob {
	
	public static void testObject(Dimension size, float posX, float posY) {
		Main.gvStorage.screenController.addObject(new TestObject(size, posX, posY));
	}
	
	public static void colorSwitcher(Dimension size, float posX, float posY) {
		Main.gvStorage.screenController.addObject(new ColorSwitcher(size, posX, posY));
	}
	
	
	
	
}
