package src.entity;

import java.util.Random;

import src.inout.*;
import src.world.*;

public class Zombie {

	public static int[] zombieX = new int[10];
	public static int[] zombieY = new int[10];
	static float[] zombieVelocityX = new float[10];
	static float[] zombieVelocityY = new float[10];
	public static boolean[] zombieDirection = new boolean[10];
	public static int[] zombieHealth = new int[10];
	static boolean[] isInAir = new boolean[10];
	static int zombieIndex = 0;
	static Random random = new Random();
	static int randomNumber;
	static boolean spawnZombie = true;
	public static int[] zombieKnockback = new int[10];
	
	public static void zombieGenerator() {
		randomNumber = random.nextInt(100);
		if (randomNumber == 0 && zombieIndex < 10) {
			randomNumber = random.nextInt(2560);
			for (int i = 1440; i > 0; i--) {
				if (World.world[randomNumber/16 + (int) Frame.x][i/16 + (int) Frame.y] == 0 && spawnZombie == true) {
					zombieHealth[zombieIndex] = 20;
					zombieX[zombieIndex] = randomNumber;
					zombieY[zombieIndex] = i;
					zombieIndex++;
					spawnZombie = false;
				}
			}
			spawnZombie = true;
		}
	}
	
	public static void zombiePhysics() {
		for (int i = 0; i < zombieHealth.length; i++) {
			if (zombieHealth[i] > 0) {
				if (World.world[(zombieX[i] + 8)/16 + (int) Frame.x][(zombieY[i] + 32)/16 + (int) Frame.y] == 0) {
					zombieVelocityY[i] += 0.25;
				} else {
					zombieVelocityY[i] = 0;
					isInAir[i] = false;
				} if (zombieX[i] < Player.playerX) {
					if (World.world[(zombieX[i] + 12)/16 + (int) Frame.x][(zombieY[i] + 16)/16 + (int) Frame.y] == 0) {
						zombieX[i]++;
						zombieDirection[i] = true;
					} else if (!isInAir[i] && Player.health > 0) {
						zombieVelocityY[i] -= 5;
						isInAir[i] = true;
					}
				} else if (World.world[(zombieX[i] + 4)/16 + (int) Frame.x][(zombieY[i] + 16)/16 + (int) Frame.y] == 0) {
					zombieX[i]--;
					zombieDirection[i] = false;
				} else if (!isInAir[i] && Player.health > 0) {
					zombieVelocityY[i] -= 5;
					isInAir[i] = true;
				}
				zombieX[i] += zombieVelocityX[i];
				zombieY[i] += zombieVelocityY[i];
				
				if (Player.isPlayerCollidingWith(zombieX[i], zombieY[i], 16, 32) && Player.knockback == 0) {
					if (Inventory.inventory[Inventory.slotX][Inventory.slotY] != 2) {
						Player.playerVelocityY -= 5;
						Player.health--;
						Player.knockback = 100;
					} else {
						zombieVelocityY[i] -= 5;
						zombieHealth[i] -= 5;
						zombieKnockback[i] = 100;
					}
				} if (zombieKnockback[i] > 0) {
					zombieKnockback[i]--;
				}
			}
		}
	}
	
}
