package src.inout;

import java.awt.event.*;

public class KeyInput implements KeyListener {
	
	public static int[] controls = {38, 37, 40, 39, 16, 45, 61};
	public static boolean[] input = new boolean[controls.length];
	
	@Override
	public void keyTyped(KeyEvent k) {
	}
	
	@Override
	public void keyPressed(KeyEvent k) {
		for (int i = 0; i < controls.length; i++) {
			if (k.getKeyCode() == controls[i]) {
				input[i] = true;
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent k) {
		for (int i = 0; i < controls.length; i++) {
			if (k.getKeyCode() == controls[i]) {
				input[i] = false;
			}
		}
	}
}
