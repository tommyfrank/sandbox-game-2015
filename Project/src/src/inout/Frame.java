package src.inout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import src.entity.Player;
import src.entity.Zombie;
import src.graphics.Art;
import src.world.World;

@SuppressWarnings("serial")
public class Frame extends JPanel {
	
	public static float x = 4920;
	public static float y = 1925;
	static int blockX = 0;
	static int blockY = 0;
	static String frameName = "untitled 0.1 TEST";
		// Time in hundredths of a second.
	public static long time = 0;
	
	public Frame() {
		KeyListener keyListener = new KeyInput();
		addKeyListener(keyListener);
		MouseListener mouseListener = new MouseInput();
		addMouseListener(mouseListener);
		MouseMotionListener mouseMotionListener = new MouseInput();
		addMouseMotionListener(mouseMotionListener);
		setFocusable(true);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		while (blockX != 160) {
			g2d.drawImage(Art.blockArt[World.world[(int) (blockX + x)][(int) (blockY + y)] % 65536], blockX*16, blockY*16, this);
			g2d.drawImage(Art.overlayArt[World.world[(int) (blockX + x)][(int) (blockY + y)] / 65536 % 16][0], blockX*16, blockY*16, this);
			g2d.drawImage(Art.overlayArt[World.world[(int) (blockX + x)][(int) (blockY + y)] / 1048576 % 16][1], blockX*16, blockY*16, this);
			g2d.drawImage(Art.overlayArt[World.world[(int) (blockX + x)][(int) (blockY + y)] / 16777216 % 16][2], blockX*16, blockY*16, this);
			g2d.drawImage(Art.overlayArt[(int) (World.world[(int) (blockX + x)][(int) (blockY + y)] / 268435456
								* (Math.signum(World.world[(int) (blockX + x)][(int) (blockY + y)] / 2 + 1.5)))][3],
																	blockX*16, blockY*16, this);
			blockY++;
			if (blockY == 90) {
				blockY = 0;
				blockX++;
			}
		}
		
		blockY = 0;
		blockX = 0;
		if (Player.health > 0) {
			g2d.drawImage(Art.entityArt[0][Player.playerState], (int) Player.playerX, (int) Player.playerY, this);
		}
		
		for (int i = 0; i < Zombie.zombieHealth.length; i++) {
			if (Zombie.zombieHealth[i] > 0) {
				if (Zombie.zombieDirection[i] == true) {
					g2d.drawImage(Art.entityArt[1][0], Zombie.zombieX[i], Zombie.zombieY[i], this);
				} else {
					g2d.drawImage(Art.entityArt[1][1], Zombie.zombieX[i], Zombie.zombieY[i], this);
				}
			}
		}
		
		if (MousePhysics.mouseDragged == true) {
			g2d.setColor(Color.RED);
			g2d.drawLine(MousePhysics.dragInput[0], MousePhysics.dragInput[1], MousePhysics.dragInput[2], MousePhysics.dragInput[3]);
			MousePhysics.dragInput[0] = MousePhysics.dragInput[2];
			MousePhysics.dragInput[1] = MousePhysics.dragInput[3];
			MousePhysics.dragInput[2] = 0;
			MousePhysics.dragInput[3] = 0;
			MousePhysics.mouseDragged = false;
		}

		Hotbar.hotbar(g2d, this);
		if (KeyInput.input[4]) {
			Inventory.inventory(g2d, this);
			if (KeyInput.input[0]) {
				if (Inventory.slotY > 0) {
					Inventory.slotY--;
				} else {
					Inventory.slotY = 4;
				}
			} if (KeyInput.input[1]) {
				if (Inventory.slotX > 0) {
					Inventory.slotX--;
				} else {
					Inventory.slotX = 9;
				}
			} if (KeyInput.input[2]) {
				if (Inventory.slotY < 4) {
					Inventory.slotY++;
				} else {
					Inventory.slotY = 0;
				}
			} if (KeyInput.input[3]) {
				if (Inventory.slotX < 9) {
					Inventory.slotX++;
				} else {
					Inventory.slotX = 0;
				}
			}
		} if (KeyInput.input[5]) {
			if (Inventory.slotX > 0) {
				Inventory.slotX--;
			} else {
				Inventory.slotX = 9;
			}
		} if (KeyInput.input[6]) {
			if (Inventory.slotX < 9) {
				Inventory.slotX++;
			} else {
				Inventory.slotX = 0;
			}
		}
		
		if (Player.health > 0) {
			if (!Player.playerDirection) {
				g2d.drawImage(Art.itemEntityArt[Inventory.inventory[Inventory.slotX][Inventory.slotY]][0], (int) Player.playerX + 8, (int) Player.playerY, this);
			} else {
				g2d.drawImage(Art.itemEntityArt[Inventory.inventory[Inventory.slotX][Inventory.slotY]][1], (int) Player.playerX - 8, (int) Player.playerY, this);
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException, IOException {
		System.out.println("[SUCCESS]: Game Started Successfully.");
		System.out.println("[INFO]: Generating World...");
		World.generateStone();
		World.generateDirt();
		World.generateCaverns();
		System.out.println("[SUCCESS]: World Generated!");
		System.out.println("[INFO]: Initializing Inventory...");
		Inventory.inventory[0][0] = 2;
		Inventory.inventory[1][0] = 3;
		System.out.println("[SUCCESS]: Inventory Initialized!");
		Art.main();
		JFrame frame = new JFrame(frameName);
		Frame game = new Frame();
		frame.add(game);
		frame.setBackground(Color.WHITE);
		frame.setSize(2560, 1440);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (true) {
			if (!KeyInput.input[4]) {
				if (Player.health > 0) {
					Player.playerPhysics();
				}
				Zombie.zombieGenerator();
				Zombie.zombiePhysics();
			}
			game.repaint();
			Thread.sleep(10);
			time += 1;
		}
	}
	
}
