package com.Rydebrink.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.Timer;

import com.Rydebrink.Framework.GameObject;
import com.Rydebrink.Framework.ObjectId;
import com.Rydebrink.Framework.Texture;
import com.Rydebrink.Window.Game;

public class InvisibleBlock extends GameObject {

	Texture tex = Game.getInstance();
	public Timer timer;
	public int count;
	public boolean contact;

	public InvisibleBlock(float x, float y, ObjectId id) {
		super(x, y, id);
		timer = new Timer(1000, new BlockTimer());
		count = 0;
		contact = false;
	}

	public void tick(LinkedList<GameObject> object) {

	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		if (contact) {
			g.drawRect((int) x, (int) y, 32, 32);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public Timer getTimer() {
		return timer;
	}

	public boolean isContact() {
		return contact;
	}

	public void setContact() {
		contact=true;
		timer.start();
	}
	
	public void resetCounter(){
		count =0;
	}

	public  int getTimeCounter() {
		return count;
	}

	public void resetBlock() {
		timer.stop();
		count = 0;
		contact = false;
	}

	private class BlockTimer implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			count++;
			if(count>5){
				resetBlock();
			}
		}
	}
}
