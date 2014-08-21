package test;

public class Controller{
	
	public double x, z, xa, za, rotation, rotationa;
	public double rotationSpeed, walkSpeed, xMove, zMove;
	
	public void tick(boolean forward, boolean back, boolean left, boolean right, boolean turnLeft, boolean turnRight)
	{
		rotationSpeed = 0.025;
		walkSpeed = 1;
		xMove = 0;
		zMove = 0;
		
		if (forward) {zMove++;}
		if (back) {zMove--;}
		if (left) {xMove--;}
		if (right) {xMove++;}
		if (turnLeft) {rotationa -= rotationSpeed;}
		if (turnRight) {rotationa += rotationSpeed;}
		
		xa += (xMove * Math.cos(rotation) + zMove * Math.sin(rotation)) * walkSpeed;
		za += (zMove * Math.cos(rotation) - xMove * Math.sin(rotation)) * walkSpeed;
		
		x += xa;
		z += za;
		xa *= 0.1;
		za *= 0.1;
		rotation += rotationa;
		rotation *= 0.5;
	}
	
}