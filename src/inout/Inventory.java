package src.inout;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import src.graphics.Art;

public class Inventory {
	
	public static int[] inventoryDimensions = {10, 5};
	public static short[][] inventory = new short[inventoryDimensions[0]][inventoryDimensions[1]];
	public static int slotX = 0;
	public static int slotY = 0;
	
	public static void inventory(Graphics2D g2d, ImageObserver imageObserver) {
		try {
			g2d.drawImage(ImageIO.read(new File("src/src/graphics/guis/Inventory.png")), 1098, 0, imageObserver);
			g2d.drawImage(ImageIO.read(new File("src/src/graphics/guis/SlotHighlight.png")), 1102 + 36 * slotX, 4 + 36 * slotY, imageObserver);
			for (int i = 0; i < inventoryDimensions[0]; i++) {
				for (int j = 0; j < inventoryDimensions[1]; j++) {
					g2d.drawImage(Art.itemArt[inventory[i][j]], 1102 + 36 * i, 4 + 36 * j, imageObserver);
				}
			}
		} catch (IOException ioexception) {
			
		}
	}
	
}
