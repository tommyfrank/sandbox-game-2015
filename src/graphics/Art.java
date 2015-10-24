package src.graphics;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Art {
	
	public static int numberOfBlocks = 65536;
	public static int numberOfOverlays = 16;
	public static int numberOfEntities = 2;
	public static int numberOfItems = 65536;
	public static BufferedImage[] blockArt = new BufferedImage[numberOfBlocks];
	public static BufferedImage[] itemArt = new BufferedImage[numberOfItems];
	public static BufferedImage[] itemEntityArt = new BufferedImage[numberOfItems];
	public static BufferedImage[][] overlayArt = new BufferedImage[numberOfOverlays][4];
	public static BufferedImage[][] entityArt = new BufferedImage[numberOfEntities][20];
	static int x = 0;
	static int y = 0;
	static int blockArtIndex = 0;
	
	public static void registerBlock(int blockID, File artFile) {
		try {
			blockArt[blockID] = ImageIO.read(artFile);
		} catch (IOException ioexception) {
			
		}
	}
	
	public static void registerItem(int itemID, File itemFile) {
		try {
			itemArt[itemID] = ImageIO.read(itemFile);
		} catch(IOException ioexception) {
			
		}
	}
	
	public static void registerItemEntity(int itemID, File itemFile) {
		try {
			itemEntityArt[itemID] = ImageIO.read(itemFile);
		} catch(IOException ioexception) {
			
		}
	}
	
	public static void registerOverlay(int overlayID, File overlayFile) {
		try {
			overlayArt[overlayID][0] = ImageIO.read(overlayFile).getSubimage(0, 0, 16, 16);
			overlayArt[overlayID][1] = ImageIO.read(overlayFile).getSubimage(0, 16, 16, 16);
			overlayArt[overlayID][2] = ImageIO.read(overlayFile).getSubimage(0, 32, 16, 16);
			overlayArt[overlayID][3] = ImageIO.read(overlayFile).getSubimage(0, 48, 16, 16);
		} catch (IOException ioexception) {
			
		}
	}
	
	public static void registerEntity(int entityID, File entityFile) {
		try {
			entityArt[entityID][0] = ImageIO.read(entityFile).getSubimage(16, 0, 16, 32);
			entityArt[entityID][1] = ImageIO.read(entityFile).getSubimage(0, 0, 16, 32);
			entityArt[entityID][2] = ImageIO.read(entityFile).getSubimage(16, 32, 16, 32);
			entityArt[entityID][3] = ImageIO.read(entityFile).getSubimage(0, 32, 16, 32);
			entityArt[entityID][4] = ImageIO.read(entityFile).getSubimage(16, 64, 16, 32);
			entityArt[entityID][5] = ImageIO.read(entityFile).getSubimage(0, 64, 16, 32);
			entityArt[entityID][6] = ImageIO.read(entityFile).getSubimage(16, 96, 16, 32);
			entityArt[entityID][7] = ImageIO.read(entityFile).getSubimage(0, 96, 16, 32);
			entityArt[entityID][8] = ImageIO.read(entityFile).getSubimage(16, 128, 16, 32);
			entityArt[entityID][9] = ImageIO.read(entityFile).getSubimage(0, 128, 16, 32);
			entityArt[entityID][10] = ImageIO.read(entityFile).getSubimage(16, 160, 16, 32);
			entityArt[entityID][11] = ImageIO.read(entityFile).getSubimage(0, 160, 16, 32);
			entityArt[entityID][12] = ImageIO.read(entityFile).getSubimage(16, 192, 16, 32);
			entityArt[entityID][13] = ImageIO.read(entityFile).getSubimage(0, 192, 16, 32);
			entityArt[entityID][14] = ImageIO.read(entityFile).getSubimage(16, 224, 16, 32);
			entityArt[entityID][15] = ImageIO.read(entityFile).getSubimage(0, 224, 16, 32);
			entityArt[entityID][16] = ImageIO.read(entityFile).getSubimage(16, 256, 16, 32);
			entityArt[entityID][17] = ImageIO.read(entityFile).getSubimage(0, 256, 16, 32);
			entityArt[entityID][18] = ImageIO.read(entityFile).getSubimage(16, 288, 16, 32);
			entityArt[entityID][19] = ImageIO.read(entityFile).getSubimage(0, 288, 16, 32);
		} catch (IOException ioexception) {
			
		}
	}
	
	public static void main() throws IOException {
		registerBlock(0, new File("src/src/graphics/blocks/Air.png"));
		registerBlock(1, new File("src/src/graphics/blocks/Dirt.png"));
		registerBlock(2, new File("src/src/graphics/blocks/Stone.png"));
		
		registerItem(1, new File("src/src/graphics/items/ZombieFlesh.png"));
		registerItem(2, new File("src/src/graphics/items/StoneSword.png"));
		registerItem(3, new File("src/src/graphics/items/StonePickaxe.png"));
		
		registerItemEntity(1, new File("src/src/graphics/itementities/ZombieFlesh.png"));
		registerItemEntity(2, new File("src/src/graphics/itementities/StoneSword.png"));
		registerItemEntity(3, new File("src/src/graphics/itementities/StonePickaxe.png"));
		
		registerOverlay(1, new File("src/src/graphics/overlays/GrassOverlay.png"));
		
		registerEntity(0, new File("src/src/graphics/entities/Player.png"));
		registerEntity(1, new File("src/src/graphics/entities/Zombie.png"));
	}
	
}
