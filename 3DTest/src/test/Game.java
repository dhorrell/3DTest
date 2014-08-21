package test;

import java.awt.event.*;

public class Game{
	
	public int time;
	public Controller controls;
	
	public Game()
	{
		controls = new Controller();
	}
	
	public void tick(boolean[] keys)
	{
		time++;
		
		boolean forward = keys[KeyEvent.VK_W];
		boolean back = keys[KeyEvent.VK_S];
		boolean left  = keys[KeyEvent.VK_A];
		boolean right  = keys[KeyEvent.VK_D];
		boolean turnLeft = keys[KeyEvent.VK_LEFT];
		boolean turnRight = keys[KeyEvent.VK_RIGHT];
		
		controls.tick(forward, back, left, right, turnLeft, turnRight);
	}
	
}