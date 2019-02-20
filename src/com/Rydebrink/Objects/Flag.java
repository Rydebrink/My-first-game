package com.Rydebrink.Objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.Rydebrink.Framework.GameObject;
import com.Rydebrink.Framework.ObjectId;
import com.Rydebrink.Framework.Texture;
import com.Rydebrink.Window.Game;

public class Flag extends GameObject {
	Texture tex = Game.getInstance();
	private int half;

	public Flag(float x, float y, int half, ObjectId id) {
		super(x, y, id);
		this.half=half;
	}

	public void tick(LinkedList<GameObject> object) {

	}

	public void render(Graphics g) {
//		g.setColor(Color.yellow);
		if (half == 0) { // Top half
			g.drawImage(tex.textures[0], (int) x, (int) y, null);
		}
		if (half == 1) { // Bottom half
			g.drawImage(tex.textures[1], (int) x, (int) y, null);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}
