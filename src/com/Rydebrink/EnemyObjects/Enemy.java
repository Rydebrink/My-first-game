package com.Rydebrink.EnemyObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import com.Rydebrink.Framework.GameObject;
import com.Rydebrink.Framework.ObjectId;

public abstract class Enemy extends GameObject {

	protected int health;
	protected int maxHealth;
	protected boolean dead;
	protected int damage;
	protected boolean flinching;
	protected int flinchTime;
	protected int speed;

	public Enemy(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	public abstract void tick(LinkedList<GameObject> object);
		
	public abstract void render(Graphics g);

	public abstract Rectangle getBounds();

	public abstract void hit(int damage);

	public boolean isDead() {
		return dead;
	}

	public int getDamage() {
		return damage;
	}

}
