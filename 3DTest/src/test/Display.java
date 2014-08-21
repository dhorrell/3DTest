package test;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class Display extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	public static final String TITLE = "3D Test";
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static boolean running = false;
	private int[] pixels;
	private BufferedImage image;
	private Thread thread;
	
	private Game game;
	private Screen screen;
	@SuppressWarnings("unused")
	private Render render;
	private InputHandler input;
	
	public Display()
	{
		game = new Game();
		screen = new Screen(WIDTH, HEIGHT);
		render = new Render(WIDTH, HEIGHT);
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
		
		input = new InputHandler();
		addKeyListener(input);
		addFocusListener(input);
	}
	
	public synchronized void start()
	{
		if (running) {return;}
		running = true;
		if (thread == null)
		{
			thread = new Thread(this, "Game");
			thread.start();
		}
	}
	
	public synchronized void stop()
	{
		if (!running) {return;}
		running = false;
		try{
			thread.join();
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public void run()
	{
		int frames = 0;
		double unprocessedSeconds = 0;
		long previousTime = System.nanoTime();
		double secondsPerTick = 1 / 60.0;
		boolean ticked = false;
		int tickCount = 0;
		long currentTime = 0, passedTime = 0;
		
		requestFocus();
		
		while (running)
		{
			currentTime = System.nanoTime();
			passedTime = currentTime - previousTime;
			previousTime = currentTime;
			unprocessedSeconds += passedTime / 1000000000.0;
			
			while (unprocessedSeconds > secondsPerTick)
			{
				tick();
				unprocessedSeconds -= secondsPerTick;
				ticked = true;
				tickCount++;
				
				if (tickCount % 60 == 0)
				{
					System.out.println(frames + "fps");
					passedTime += 1000;
					frames = 0;
				}
			}
			if (ticked)
			{
				render();
				frames++;
			}
			render();
			frames++;
		}
	}
	
	private void tick()
	{
		game.tick(input.keys);
	}
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {createBufferStrategy(3); return;}
		
		screen.render(game);
		for (int i = 0; i < WIDTH * HEIGHT; i++) { pixels[i] = screen.pixels[i];}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args)
	{
		Display g = new Display();
		JFrame f = new JFrame();
		f.add(g);
		f.setTitle(TITLE);
		f.setSize(new Dimension(WIDTH, HEIGHT));
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.pack();
		
		g.start();
	}
	
}