package com.Rydebrink.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.Rydebrink.Framework.GameObject;
import com.Rydebrink.Framework.ObjectId;
import com.Rydebrink.Framework.Texture;
import com.Rydebrink.Window.Game;

public class TransparentBlock extends GameObject {

	Texture tex = Game.getInstance();
	private int type;

	public TransparentBlock(float x, float y, int type, ObjectId id) {
		super(x, y, id);
		this.type = type;
	}

	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub

	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		if (type == 0) { // Grass
		 g.drawImage(tex.block[0], (int) x, (int) y, null);
		}
		if (type == 1) { // Dirt
		 g.drawImage(tex.block[1], (int) x, (int) y, null);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}
