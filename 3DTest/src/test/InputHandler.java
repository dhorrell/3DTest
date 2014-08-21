package test;

import java.awt.event.*;

public class InputHandler implements KeyListener, FocusListener{
	
	public boolean[] keys = new boolean[68836];
	
	public void focusGained(FocusEvent e){}
	public void keyTyped(KeyEvent e) {}
	
	public void focusLost(FocusEvent e)
	{
		for (int i = 0; i < keys.length; i++) {keys[i] = false;}
	}
	
	public void keyPressed(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_ESCAPE) {System.exit(0);}
		if (keyCode >= 0 && keyCode <= keys.length) {keys[keyCode] = true;}
	}
	
	public void keyReleased(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		if (keyCode >= 0 && keyCode <= keys.length) {keys[keyCode] = false;}
	}
	
}