package com.Rydebrink.Objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.Rydebrink.Framework.GameObject;
import com.Rydebrink.Framework.ObjectId;
import com.Rydebrink.Framework.Texture;
import com.Rydebrink.Window.Game;
import com.Rydebrink.Window.Handeler;

public class Shell extends GameObject {
	private Handeler handeler;
	Texture tex = Game.getInstance();

	public Shell(float x, float y, ObjectId id, Handeler handeler, int velX) {

		super(x, y, id);
		this.handeler = handeler;
		this.velX = velX;
	}

	public void tick(LinkedList<GameObject> object) {
		x += velX;
		Collision(object);
	}

	public void render(Graphics g) {
		g.drawImage(tex.snail[18], (int)x, (int)y, null);

		// g.drawImage(tex.bullets[0], (int) x, (int) y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 16, 16);
	}

	private void Collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handeler.object.size(); i++) {

			GameObject tempObject = handeler.object.get(i);
			if (tempObject.getId() == ObjectId.Block) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handeler.removeObject(this);
				}
			}
			if (tempObject.getId() == ObjectId.Snail) {
				if (getBounds().intersects(tempObject.getBounds())
						&& !tempObject.isDead()) {
					tempObject.hurt(15);
					handeler.removeObject(this);
				}
			}
		}
	}
}
