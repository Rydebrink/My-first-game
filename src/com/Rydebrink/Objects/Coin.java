package com.Rydebrink.Objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.Rydebrink.Framework.GameObject;
import com.Rydebrink.Framework.ObjectId;
import com.Rydebrink.Framework.Texture;
import com.Rydebrink.Window.Game;

public class Coin extends GameObject {
	Texture tex = Game.getInstance();

	public Coin(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	public void tick(LinkedList<GameObject> object) {

	}

	public void render(Graphics g) {
		g.drawImage(tex.coin[0], (int) x, (int) y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}
