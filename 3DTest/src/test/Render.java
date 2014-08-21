package test;

public class Render{
	
	public int width, height;
	public int[] pixels;
	
	private int xPix;
	private int yPix;
	private int alpha;
	
	public Render(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		this.xPix = this.yPix = this.alpha = 0;
		
		pixels = new int[width * height];
	}
	
	public void draw(Render render, int xOffset, int yOffset)
	{
		for (int y = 0; y < render.height; y++)
		{
			yPix = y + yOffset;
			if (yPix <= 0 || yPix >= height) {continue;}
			for (int x = 0; x < render.width; x++)
			{
				xPix = x + xOffset;
				if (xPix <= 0 || xPix >= width) {continue;}
				alpha = render.pixels[x+y*render.width];
				if (alpha > 0) {pixels[xPix+yPix*width] = alpha;}
			}
		}
	}
	
}