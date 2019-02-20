package com.Rydebrink.Objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.Timer;

import com.Rydebrink.Framework.GameObject;
import com.Rydebrink.Framework.ObjectId;
import com.Rydebrink.Framework.Texture;
import com.Rydebrink.Window.Game;
import com.Rydebrink.Window.TimeListener;

public class TimeBlock extends GameObject {
	Texture tex = Game.getInstance();
	public static boolean pressed;
	public static int blockTimer;
	private static Timer timer;
	public static int time;

	public TimeBlock(float x, float y, int time,  ObjectId id) {
		super(x, y, id);
		pressed=false;
		blockTimer = 0;
		TimeBlock.time=time;
		timer = new Timer(1000, new TimeListener());
	}

	public void tick(LinkedList<GameObject> object) {

	}

	public void render(Graphics g) {
		if (!isPressed()) {
			g.drawImage(tex.normalNumbers[time], (int)x, (int)y, null);
		} else if (isPressed()) {
			g.drawImage(tex.invertedNumbers[time-blockTimer], (int)x, (int)y, null);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public static boolean isPressed() {
		return pressed;
	}

	public static void setPressed() {
		pressed=true;
	}

	public static void startTimer() {
		timer.start();

	}

	public static int getTime() {
		return blockTimer;
	}

	public static void resetBlock() {
		timer.stop();
		blockTimer = 0;
		pressed = false;

	}

}
