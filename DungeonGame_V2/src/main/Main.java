package main;

import rendering.Screen;
import system.GVStorage;

public class Main {
	public static GVStorage gvStorage;

	public static void main(String[] args) {
		
		Screen.erstellen();
		gvStorage = new GVStorage();
		gvStorage.createObjects();
	}
}
