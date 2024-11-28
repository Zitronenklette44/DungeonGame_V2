package fundamentals;

import java.awt.Dimension;
import java.util.HashMap;

import dungeon.DungeonManager;
import entitys.MobTemplate;
import entitys.mobs.TestDummy;
import items.ItemTemplate;
import items.itemTypes.XPOrb;
import main.Main;
import system.GameLogic;
import system.MyConsole;
import system.Vector3;

public class CommandInterpretor {
	String[] command;
	static HashMap<String, ItemTemplate> itemList = new HashMap<String, ItemTemplate>();
	static HashMap<String, MobTemplate> entityList = new HashMap<String, MobTemplate>();
	
	public static void initCommands() {
		//items
		itemList.put("xpOrb", new XPOrb(0, 0, 0));
		
		
		
		//mobs
		entityList.put("testDummy", new TestDummy(new Dimension(50, 50), 0, 0));
	}
	
	
	public void startInterpretation(String input) {
		MyConsole.logInfo("New Command input -->"+ input);
		command = splitCommand(input);
		
		
		switch (command[0]) {
		case "generate": {
			if(command[1].equals("object")) {
				ItemTemplate temp = itemList.get(command[2]).clone();
				temp.relativePosition = new Vector3(Float.parseFloat(command[3]), Float.parseFloat(command[4]), 0);
				temp.setDefaultValues();
				Main.gvStorage.screenController.addObject(temp);
				MyConsole.logInfo("command sucessful");
				
			}else if(command[1].equals("entity")) {
				MobTemplate temp = entityList.get(command[2]).clone();
				temp.relativePosition = new Vector3(Float.parseFloat(command[3]), Float.parseFloat(command[4]), 0);
				temp.setDefaultValues();
				Main.gvStorage.screenController.addObject(temp);
				MyConsole.logInfo("command sucessful");
			}
			
		
		} break;
		
		case "dungeon": {
			if(command[1].equals("generate")) {
				Vector3 temp = Main.gvStorage.player.relativePosition.copy();
				temp.add(new Vector3(0, 0, Main.gvStorage.currentLvl));
				Main.gvStorage.generateNewDungeon(temp, 50, 40, false);
				MyConsole.logInfo("command sucessful");	
			}else if(command[1].equals("delete")) {
				Main.gvStorage.dungeons.remove(Integer.parseInt(command[2])-1);
				
				MyConsole.logInfo("command sucessful");
			}
			
		
		} break;
		
		default:
			MyConsole.logWarning("Invalid Command -->" + input);
		} 
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	private String[] splitCommand(String input) {
		return input.split(" ");
	}

}
