package src.world;

import java.util.Random;

public class World {
	
	static int[] worldSize = {10000, 5000};
	static int[] layer = {5000, 2000, 1936};
	static int dirtVariation = 10;
	static int stoneVariation = 10;
	static int cavernDepth = 75;
	static int airBubbleHeight;
	public static int[][] world = new int[worldSize[0]][worldSize[1]];
	public static Random randomNumberGenerator = new Random();
	static int randomNumber;
	static int pastRandomNumber = layer[0] - layer[1];
	static boolean isRandomGenerated;
	static int secondaryRandomNumber;
	
	public static void generateDirt() {
		pastRandomNumber = layer[1] - layer[2];
		for (int i = 0; i < worldSize[0]; i++) {
			isRandomGenerated = false;
			randomNumber = randomNumberGenerator.nextInt(dirtVariation) + layer[1] - layer[2] - dirtVariation;
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
				} if (!isRandomGenerated) {
					randomNumber = randomNumberGenerator.nextInt(dirtVariation) + layer[1] - layer[2] - dirtVariation;
				}
			}
			
			airBubbleHeight = 0;
			for (int j = stoneVariation; j > 0; j--) {
				if (world[i][j + layer[1]] == 0) {
					airBubbleHeight++;
				}
			}
			
			for (int j = 0; j < randomNumber + airBubbleHeight; j++) {
				world[i][layer[1] + airBubbleHeight - j] = 1;
			}
			world[i][layer[1] + 1 - randomNumber] += 65536;
			if (randomNumber > pastRandomNumber) {
				for (int j = randomNumber; j > pastRandomNumber; j--) {
					world[i][layer[1] + 1 - j] += 268435456;
				}
			} else if (i > 0) {
				for (int j = randomNumber; j < pastRandomNumber; j++) {
					world[i - 1][layer[1] - j] += 1048576;
				}
			}
			
			pastRandomNumber = randomNumber;
		}
	}
	
	public static void generateStone() {
		pastRandomNumber = layer[0] - layer[1];
		for (int i = 0; i < worldSize[0]; i++) {
			isRandomGenerated = false;
			randomNumber = randomNumberGenerator.nextInt(stoneVariation) + layer[0] - layer[1] - stoneVariation;
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
				} if (!isRandomGenerated) {
					randomNumber = randomNumberGenerator.nextInt(stoneVariation) + layer[0] - layer[1] - stoneVariation;
				}
			}
			
			for (int j = 1; j < randomNumber; j++) {
				world[i][layer[0] - j] = 2;
			}
			
			pastRandomNumber = randomNumber;
		}
	}
	
	public static void generateCaverns() {
		for (int i = 0; i < 50; i++) {
			randomNumber = randomNumberGenerator.nextInt(worldSize[0]);
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < cavernDepth; k++) {
					world[j + randomNumber][k + layer[2]] = 0;
				}
			}
		}
	}
	
}
