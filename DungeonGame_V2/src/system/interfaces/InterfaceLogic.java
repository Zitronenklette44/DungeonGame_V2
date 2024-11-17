package system.interfaces;

import java.util.ArrayList;
import java.util.Iterator;

import fundamentals.SimpleObject;
import main.Main;
import system.Vector3;

public class InterfaceLogic {
	
	private static ArrayList<SimpleObject> IsEmitting= new ArrayList<SimpleObject>(); 
	
	public InterfaceLogic() {}
	
	public static void init(){
		IsEmitting.add(Main.gvStorage.player);
	}
	
	public void startLogic() {
		
		startLight();
		
		
	}
		
	
	//light
	private void startLight() {
		Iterator<SimpleObject> iterator = IsEmitting.iterator();
		while (iterator.hasNext()) {
			SimpleObject object = iterator.next();
			object.emitLight();
			object.burnsOut();
			
		}

	}
	
	
	public static void emitLight(SimpleObject ob) {
		//Removed bc of better Light calculation
//		if(ob.burnTime > 0 || ob.burnTime == -1) {
//			Iterator<SimpleObject> iterator = Main.gvStorage.screenController.getCurrentObjects().iterator();
//			while(iterator.hasNext()) {
//				SimpleObject obj = iterator.next();
//				Vector3 temp = ob.pos.copy();
//				temp.add(new Vector3(ob.size.width / 2, ob.size.height / 2, 0));
//				if(temp.getDistance(obj.pos) <= ob.lightRange || obj.isEmitting) {
//					obj.isInLight = true;
//				}else {
//					obj.isInLight = false;
//				}
//			}
//		}
		
	}

	public static void burnsOut(SimpleObject ob) {
		if(ob.burnTime == -1) {
			return;
		}
		
		if(ob.burnTime > 0) {
			ob.burnTime--;
			return;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
