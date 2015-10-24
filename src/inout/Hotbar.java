package src.inout;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import src.graphics.Art;

public class Hotbar {
	
	public static short[] hotbar = new short[Inventory.inventoryDimensions[0]];
	
	public static void hotbar(Graphics2D g2d, ImageObserver imageObserver) {
		try {
			g2d.drawImage(ImageIO.read(new File("src/src/graphics/guis/Hotbar.png")), 1098, 0, imageObserver);
			if (!KeyInput.input[4]) {
				g2d.drawImage(ImageIO.read(new File("src/src/graphics/guis/SlotHighlight.png")), 1102 + 36 * Inventory.slotX, 4, imageObserver);
			}
			for (int i = 0; i < Inventory.inventoryDimensions[0]; i++) {
				hotbar[i] = Inventory.inventory[i][0];
				g2d.drawImage(Art.itemArt[hotbar[i]], 1102 + 36 * i, 4, imageObserver);
			}
		} catch (IOException ioexception) {
			
		}
	}
	
}
