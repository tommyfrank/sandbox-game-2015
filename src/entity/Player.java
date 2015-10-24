package src.entity;

import src.inout.Frame;
import src.inout.Inventory;
import src.inout.KeyInput;
import src.world.World;

public class Player {
	
	public static float playerX = 1272;
	public static float playerY = 175;
	public static float playerVelocityX = 0;
	public static float playerVelocityY = 0;
	static boolean isInAir = true;
	public static boolean playerDirection = false;
	public static int playerState = 0;
	public static int knockback = 0;
	public static int health = 20;
	
	public static boolean isPlayerCollidingWith(float x, float y, int length, int height) {
		boolean horizontal = false;
		boolean vertical = false;
		if (x > playerX && x < playerX + 16) {
			horizontal = true;
		} if (y > playerY && y < playerY + 32) {
			vertical = true;
		} if (x + 16 > playerX && x + 16 < playerX + 16) {
			horizontal = true;
		} if (y + 32 > playerY && y + 32 < playerY + 32 + 32) {
			vertical = true;
		}
		return horizontal && vertical;
	}
	
	public static void playerPhysics() {
		playerState /= 2;
		if (isInAir) {
			playerState = 9;
		} else if (KeyInput.input[1] || KeyInput.input[3]) {
			if (playerState > 0 && playerState < 8) {
				playerState++;
			} else {
				playerState = 1;
			}
		}
		playerState *= 2;
		if (playerDirection) {
			playerState++;
		}
		
		if (World.world[(int) ((int) (playerX + 10)/16 + Frame.x)][(int) ((int) (playerY + playerVelocityY + 32)/16 + Frame.y)] % 65536 == 0) {
			playerVelocityY += 0.25;
		} else {
			playerVelocityY = 0;
			isInAir = false;
		} if (KeyInput.input[0] && !isInAir) {
			playerVelocityY = -5;
			isInAir = true;
		} if (KeyInput.input[1] && World.world[(int) ((int) (playerX + 2)/16 + Frame.x)][(int) ((int) (playerY + 16)/16 + Frame.y)] % 65536 == 0) {
			if (playerX > 256) {
				if (playerState == 9 || playerState == 17) {
					playerX -= 4;
				} else if (playerState == 19) {
					playerX--;
				}
			} else {
				Frame.x -= 0.0625;
			}
			playerDirection = true;
		} if (KeyInput.input[3] && World.world[(int) ((int) (playerX + 12)/16 + Frame.x)][(int) ((int) (playerY + 16)/16 + Frame.y)] % 65536 == 0) {
			if (playerX < 2304) {
				if (playerState == 8 || playerState == 16) {
					playerX += 4;
				} else if (playerState == 18) {
					playerX++;
				}
			} else {
				Frame.x += 0.0625;
			}
			playerDirection = false;
		} if (Player.knockback > 0) {
			Player.knockback--;
		}
		playerX += playerVelocityX;
		if (playerY < 256 && playerVelocityY < 0) {
			Frame.y += 0.0625 * playerVelocityY;
		} else if (playerY > 1184 && playerVelocityY > 0) {
			Frame.y += 0.0625 * playerVelocityY;
		} else {
		playerY += playerVelocityY;
		}
	}
	
}
