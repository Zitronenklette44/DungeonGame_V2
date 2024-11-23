package system.interfaces;

import java.util.ArrayList;
import java.util.Iterator;

import entitys.MobTemplate;
import fundamentals.SimpleObject;
import main.Main;
import system.Vector3;

public class InterfaceLogic {

	private static ArrayList<SimpleObject> IsEmitting = new ArrayList<SimpleObject>();

	public InterfaceLogic() {
	}

	public static void init() {
		IsEmitting.add(Main.gvStorage.player);
	}

	public void startLogic() {

		startLight();

	}

	// light
	private void startLight() {
		Iterator<SimpleObject> iterator = IsEmitting.iterator();
		while (iterator.hasNext()) {
			SimpleObject object = iterator.next();
			object.emitLight();
			object.burnsOut();

		}

	}

	public static void emitLight(SimpleObject ob) {
	}

	public static void burnsOut(SimpleObject ob) {
		if (ob.burnTime == -1) {
			return;
		}

		if (ob.burnTime > 0) {
			ob.burnTime--;
			return;
		}
	}

	// rooms
	public static boolean isPlayerInside(SimpleObject object, MobTemplate player) {
		return object.pos.getVecY() + object.size.height > player.pos.getVecY()
				&& object.pos.getVecY() < player.pos.getVecY() + player.size.height
				&& object.pos.getVecX() + object.size.width > player.pos.getVecX()
				&& object.pos.getVecX() < player.pos.getVecX() + player.size.width;
	}
	
	

}
