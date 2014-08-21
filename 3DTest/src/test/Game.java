package test;

import java.awt.event.KeyEvent;

public class Game{
	
	public int time;
	public Controller controls;
	
	private boolean forward;
	private boolean back;
	private boolean left;
	private boolean right;
	private boolean turnLeft;
	private boolean turnRight;
	
	public Game()
	{
		controls = new Controller();
		this.forward = this.back = this.left = this.right = this.turnLeft = this.turnRight = false;
	}
	
	public void tick(boolean[] keys)
	{
		time++;
		
		forward = keys[KeyEvent.VK_W];
		back = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		turnLeft = keys[KeyEvent.VK_LEFT];
		turnRight = keys[KeyEvent.VK_RIGHT];
		
		controls.tick(forward, back, left, right, turnLeft, turnRight);
	}
	
}