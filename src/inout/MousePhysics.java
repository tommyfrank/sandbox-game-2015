package src.inout;

import java.awt.Point;

public class MousePhysics {

	public static int[] dragInput = new int[4];
	public static boolean mouseReleased = false;
	public static boolean mouseDragged = false;
	static Point[] dragPoint = new Point[Math.max(dragInput[2] - dragInput[0], dragInput[3] - dragInput[1])];
	
	public static void HitEntities() {
		for (int i = 0; i < dragPoint.length; i++) {
			for (int j = 0; j < 10; j++) {
				
			}
		}
	}
	
}
