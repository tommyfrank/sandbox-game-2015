package src.world;

import src.inout.Frame;

import java.util.Random;

public class World {
	
	static int[] worldSize = {10000, 5000};
	static int[] layer = {5000, 2000, 1936};
	static int grassThickness = 10;
	public static int[][] world = new int[worldSize[0]][worldSize[1]];
	public static Random randomNumberGenerator = new Random();
	static int randomNumber;
	static int pastRandomNumber = layer[1] - layer[2];
	static boolean isRandomGenerated;
	static int secondaryRandomNumber;
	
	public static void generateDirt() {
		for (int i = 0; i < worldSize[0]; i++) {
			isRandomGenerated = false;
			randomNumber = randomNumberGenerator.nextInt(grassThickness) + layer[1] - layer[2] - grassThickness;
			while (!isRandomGenerated) {
				secondaryRandomNumber = randomNumberGenerator.nextInt(1000000);
				if (randomNumber == pastRandomNumber && !isRandomGenerated) {
					 isRandomGenerated = true;
				} if (Math.abs(randomNumber - pastRandomNumber) == 1 && secondaryRandomNumber <= 606531 && !isRandomGenerated) {
					 isRandomGenerated = true;
				} if (Math.abs(randomNumber - pastRandomNumber) == 2 && secondaryRandomNumber <= 135335 && !isRandomGenerated) {
					 isRandomGenerated = true;
				} if (Math.abs(randomNumber - pastRandomNumber) == 3 && secondaryRandomNumber <= 11109 && !isRandomGenerated) {
					 isRandomGenerated = true;
				} if (Math.abs(randomNumber - pastRandomNumber) == 4 && secondaryRandomNumber <= 335 && !isRandomGenerated) {
					 isRandomGenerated = true;
				} if (Math.abs(randomNumber - pastRandomNumber) == 5 && secondaryRandomNumber <= 3 && !isRandomGenerated) {
					 isRandomGenerated = true;
				} if (isRandomGenerated == false) {
					randomNumber = randomNumberGenerator.nextInt(grassThickness) + (layer[1] - layer[2]) - grassThickness;
				}
			}
			for (int j = 0; j < randomNumber; j++) {
				world[i][layer[1] - j] = 1;
			}

			world[i][layer[1] + 1 - randomNumber] += 65536;
			if (randomNumber > pastRandomNumber) {
				for (int k = randomNumber; k > pastRandomNumber; k--) {
					world[i][layer[1] + 1 - k] += 268435456;
				}
			} else if (i > 0) {
				for (int k = randomNumber; k < pastRandomNumber; k++) {
					world[i - 1][layer[1] - k] += 1048576;
				}
			}
			
			pastRandomNumber = randomNumber;
		}
	}
	
	public static void generateStone() {
		for (int i = 0; i < worldSize[0]; i++) {
			for (int j = 1; j < worldSize[1] - layer[1]; j++) {
				world[i][worldSize[1] - j] = 2;
			}
		}
	}
	
	public static void generateCaverns() {
		for (int i = 0; i < 50; i++) {
			randomNumber = randomNumberGenerator.nextInt(worldSize[0]);
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < layer[1]; k++) {
					world[j + randomNumber][k + layer[2]] = 0;
				}
			}
		}
	}
	
}
