package src.inout;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {
	

	@Override
	public void mouseDragged(MouseEvent m) {
		MousePhysics.dragInput[2] = m.getXOnScreen();
		MousePhysics.dragInput[3] = m.getYOnScreen();
		MousePhysics.mouseDragged = true;
	}

	@Override
	public void mouseMoved(MouseEvent m) {
		MousePhysics.dragInput[0] = m.getXOnScreen();
		MousePhysics.dragInput[1] = m.getYOnScreen();
	}

	@Override
	public void mouseClicked(MouseEvent m) {
		
	}

	@Override
	public void mouseEntered(MouseEvent m) {
	}

	@Override
	public void mouseExited(MouseEvent m) {
	}

	@Override
	public void mousePressed(MouseEvent m) {
	}

	@Override
	public void mouseReleased(MouseEvent m) {
		MousePhysics.mouseReleased = true;
	}
	
}
