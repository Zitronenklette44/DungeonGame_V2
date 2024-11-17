package items;

import java.util.ArrayList;

import items.itemTypes.XPOrb;
import main.Main;
import system.MyConsole;

public class ItemManager {
	
	private ArrayList<ItemTemplate> floorItems;
	private ItemTemplate[] itemID = {new XPOrb(0, 0, 0)};
	
	public ItemManager() {
		floorItems = new ArrayList<ItemTemplate>();
		
	}
	
	public void removeFloorItem(ItemTemplate item) {
		for(int i = 0; i<floorItems.size();i++) {
			if(floorItems.get(i) == item) {
				floorItems.remove(i);
			}
		}
		Main.gvStorage.screenController.removeObject(item);

	}
	
	public int getItemID(ItemTemplate item) {
		for (int i = 0; i < itemID.length; i++) {
			if(item.getClass() == itemID[i].getClass()) {
				return i;
			}
		}
		
		MyConsole.logError("Invalid Item-->"+item.toString());
		return -1;
	}
	
	public ArrayList<ItemTemplate> getFloorItems(){
		return floorItems;
	}
	
	
	
	
	//Generate Items
	public void generateXPOrb(float posX, float posY, int value) {
		XPOrb tempOrb = new XPOrb(posX, posY, value);
		floorItems.add(tempOrb);
		Main.gvStorage.screenController.addObject(tempOrb);
	}
	

}
