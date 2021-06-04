package com.helloworld.main;
/*
* This class outputs hello world.
*
* @author  Cameron Teed
* @version 1.0
* @since   2021-05-26
*/
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


public class Game extends Canvas implements Runnable{
	
	/**
	 * SerialVersion UID (Will need for later).
	 */
	private static final long serialVersionUID = 5102725995743094780L;
	/**
	 * The size of the GUI.
	 */
	public static final int WIDTH = 740, HEIGHT = WIDTH / 12 * 9;
	/**
	 * Initializes the threads.
	 */
	private Thread thread;
	/**
	 * Tells the programs its not yet running.
	 */
	private boolean running = false;
	
	public static BufferedImage sprite_sheet;
	
	
	
	/**
	 * Constructor.
	 */
	public Game() {
		BufferedImageLoader loader = new BufferedImageLoader();
		sprite_sheet = loader.loadImage("/egg.png");
		new Window(WIDTH, HEIGHT, "Hello, World!", this);
	}
	
	/**
	 * Starts the GUI.
	 */
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	/**
	 * Stops the GUI.
	 */
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
		e.printStackTrace();
		}
	}
	
	/**
	 * This method creates a loop that can run the graphics.
	 */
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			render();
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
			}
		}
		stop();
	}
	
	/**
	 * This method will set the tick speed.
	 */
	public void tick() {
		
	}
	
	/**
	 * This method renders the graphics.
	 */
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0,  0,  WIDTH, HEIGHT);
		
		g.setColor(Color.white);
	
		g.drawString("Hello, World! ", 10, 20);
		
		Egg ss = new Egg(Game.sprite_sheet);
		
		BufferedImage eggSprite0 = ss.grabImage(1, 1, 50, 50);
		BufferedImage eggSprite = ss.grabImage(1, 2, 50, 50);
		BufferedImage eggSprite2 = ss.grabImage(2, 1, 50, 50);
		BufferedImage eggSprite3 = ss.grabImage(2, 2, 50, 50);
		
		BufferedImage eggSprite4 = ss.grabImage2(0, 0, 200, 200);
		
		
		g.drawImage(eggSprite0, 30, 30, null);
		g.drawImage(eggSprite, 80, 30, null);
		g.drawImage(eggSprite2, 30, 80, null);
		g.drawImage(eggSprite3, 80, 80, null);
		g.drawImage(eggSprite4, 150, 150, null);
		g.dispose();
		bs.show();
	}
		
	/**
	 * Main function.
	 *
	 * @param args
	 */
	public static void main(String args[]) {
		new Game();
	}
}
