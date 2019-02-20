package com.Rydebrink.EnemyObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.Timer;

import com.Rydebrink.Framework.GameObject;
import com.Rydebrink.Framework.ObjectId;
import com.Rydebrink.Framework.Texture;
import com.Rydebrink.Objects.EnemyZoneBlock;
import com.Rydebrink.Objects.Player;
import com.Rydebrink.Window.Animation;
import com.Rydebrink.Window.Game;
import com.Rydebrink.Window.Handeler;

public class Snail extends GameObject {
	Texture tex = Game.getInstance();

	private Animation snailWalkRight, snailWalkLeft;

	public static int damage;
	private int flinchTime;
	private static Timer timer;
	private int count;
	private int health;
	private Handeler handeler;
	private boolean edgeHit;

	public Snail(float x, float y, Handeler handeler, ObjectId id) {
		super(x, y, id);
		this.handeler = handeler;
		damage = 10;
		flinchTime = 1;
		timer = new Timer(1000, new FlinchTimer());
		count = 0;
		health = 15;
		dead = false;
		edgeHit=false;
		velX = 1;
		snailWalkRight = new Animation(8, tex.snail[0], tex.snail[1],
				tex.snail[2], tex.snail[3], tex.snail[4], tex.snail[5],
				tex.snail[6], tex.snail[7], tex.snail[8]);

		snailWalkLeft = new Animation(8, tex.snail[9], tex.snail[10],
				tex.snail[11], tex.snail[12], tex.snail[13], tex.snail[14],
				tex.snail[15], tex.snail[16], tex.snail[17]);

	}

	public void tick(LinkedList<GameObject> object) {
		x += velX;
		if (velX < 0)
			facing = -1;
		else if (velX > 0)
			facing = 1;
		if (!dead) {
			move(object);
			Collision(object);
		}
		snailWalkRight.runAnimation();
		snailWalkLeft.runAnimation();
	}

	public void render(Graphics g) {
		if (!dead) {
			if (facing > 0) {
				snailWalkRight.drawAnimation(g, (int)x, (int)y);
			}
			if (facing < 0) {
				snailWalkLeft.drawAnimation(g, (int)x, (int)y);
			}
		} else {
			g.drawImage(tex.snail[18], (int) x, (int) y, null);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public static void hit(int damage) {
		if (!Player.flinching) {
			Player.flinching = true;
			Player.health = Player.health - damage;
			if (Player.health < 0)
				Player.health = 0;
		}
		timer.start();
	}

	public void hurt(int pain) {
		health = health - pain;
		if (health <= 0) {
			dead = true;
			setVelX(0);
		}
	}

	private void move(LinkedList<GameObject> object) {
		for (int i = 0; i < handeler.object.size(); i++) {
			GameObject tempObject = handeler.object.get(i);

			if (tempObject.getId() == ObjectId.Player) {
				if (tempObject.getY() == getY()) {
					if (tempObject.getX() < getX()) {
						setVelX(-1);
					} else if (tempObject.getX() > getX()) {
						setVelX(1);
					}
				}
			}
		}
	}
	
	private void Collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handeler.object.size(); i++) {
			GameObject tempObject = handeler.object.get(i);

			if (tempObject.getId() == ObjectId.EnemyZoneBlock) {
				if (getBounds().intersects(tempObject.getBounds())) {
					setVelX(getVelX() * -1);
					edgeHit = true;
				}
			}
		}
	}

	private class FlinchTimer implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			count++;
			if (count >= flinchTime) {
				Player.flinching = false;
				count = 0;
				timer.stop();
			}
		}
	}
}
