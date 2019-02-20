package com.Rydebrink.Window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.Rydebrink.Framework.KeyInput;
import com.Rydebrink.Framework.ObjectId;
import com.Rydebrink.Framework.Texture;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -1064817451986736521L;

	private boolean running = false;
	private Thread thread;

	public static int WIDTH, HEIGHT;

	@SuppressWarnings("unused")
	private BufferedImage clouds = null, level = null;

	// Object
	Handeler handeler;
	Camera cam;
	static Texture tex;

	Random rand = new Random();

	public static int LEVEL = 1;
	public static int score = 0;
	public static int currentKeys = 0;
	public static int maxKeys = 0;

	private void init() {
		WIDTH = getWidth();
		HEIGHT = getHeight();

		tex = new Texture();

		BufferedImageLoader loader = new BufferedImageLoader();

		clouds = loader.loadImage("/bkg_clouds.png"); // Loading backround
		level = loader.loadImage("/level.png"); // loading the level

		cam = new Camera(0, 0);
		handeler = new Handeler(cam);

		handeler.switchLevel();
		;
		// handeler.switchLevel();

		// handeler.addObject(new Player(100, 100, handeler, ObjectId.Player));
		// handeler.createLevel();

		this.addKeyListener(new KeyInput(handeler));

	}

	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();

	}

	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	private void tick() {
		handeler.tick();
		for (int i = 0; i < handeler.object.size(); i++) {
			if (handeler.object.get(i).getId() == ObjectId.Player) {
				cam.tick(handeler.object.get(i));
			}
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		Graphics2D gtd = (Graphics2D) g;

		// ///////////////

		// // Draw here
		g.setColor(new Color(101, 142, 193));
		g.fillRect(0, 0, getWidth(), getHeight());
		gtd.translate(cam.getX(), cam.getY()); // Begin of cam

		for (int xx = 0; xx < clouds.getWidth(); xx = +clouds.getWidth()) {
			g.drawImage(clouds, xx, 100, this);
		}
		handeler.render(g);

		gtd.translate(-cam.getX(), -cam.getY()); // End of cam

		// ///////////////
		g.dispose();
		bs.show();

	}

	public static Texture getInstance() {
		return tex;
	}

	public static void main(String[] args) {
		new Window(800, 600, "Rydebrink Game", new Game());
	}

}
