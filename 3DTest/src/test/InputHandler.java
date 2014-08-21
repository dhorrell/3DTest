package test;

import java.awt.event.*;

public class InputHandler implements KeyListener, FocusListener{
	
	public boolean[] keys = new boolean[68836];
	
	private int pressCode = 0;
	private int relCode = 0;
	
	public void focusGained(FocusEvent e){}
	public void keyTyped(KeyEvent e) {}
	
	public void focusLost(FocusEvent e)
	{
		for (int i = 0; i < keys.length; i++) {keys[i] = false;}
	}
	
	public void keyPressed(KeyEvent e)
	{
		pressCode = e.getKeyCode();
		if (pressCode == KeyEvent.VK_ESCAPE) {System.exit(0);}
		if (pressCode >= 0 && pressCode <= keys.length) {keys[pressCode] = true;}
	}
	
	public void keyReleased(KeyEvent e)
	{
		relCode = e.getKeyCode();
		if (relCode >= 0 && relCode <= keys.length) {keys[relCode] = false;}
	}
	
}