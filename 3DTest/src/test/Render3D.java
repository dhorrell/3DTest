package test;

public class Render3D extends Render{
	
	public Render3D(int width, int height)
	{
		super(width, height);
	}
	
	public void floor(Game game)
	{
		double rotation = game.controls.rotation;
		double sine = Math.sin(rotation);
		double cosine = Math.cos(rotation);
		
		double floorPosition = 8;
		double ceilingPosition = 8;
		double forward = game.controls.z;
		double right = game.controls.x;
		
		double ceiling = 0;
		double z = 0;
		double depth = 0;
		double xx = 0;
		double yy = 0;
		
		int xPix = 0;
		int yPix = 0;
		
		for (int y = 0; y < height; y++)
		{
			ceiling = (y - height / 2.0) / height;
			z = (floorPosition / ceiling);
			if (ceiling < 0) {z = ceilingPosition / -ceiling;}
			
			for (int x = 0; x < width; x++)
			{
				depth = (x - width / 2.0) / height;
				depth *= z;
				xx = depth * cosine + z * sine;
				yy = z * cosine - depth * sine;
				xPix = (int) (xx + right);
				yPix = (int) (yy + forward);
				pixels[x+y*width] = ((xPix & 15) * 16) | ((yPix & 15) * 16) << 6;
				if (z > 200) {pixels[x+y*width] = 0;}
			}
		}
	}

}
