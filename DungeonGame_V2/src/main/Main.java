package main;

import rendering.Screen;
import system.GVStorage;
import system.interfaces.InterfaceLogic;

public class Main {
	public static GVStorage gvStorage;

	public static void main(String[] args) {
		
		Screen.erstellen();
		gvStorage = new GVStorage();
		InterfaceLogic.init();
		gvStorage.createObjects();
		
		
	}
}
