package com.Rydebrink.Framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {

	protected float x, y;
	protected float velX = 0, velY = 0;
	protected ObjectId id;
	protected boolean falling = true, jumping = false;
	protected int facing = 1;
	protected boolean dead;

	public GameObject(float x, float y, ObjectId id) {
		this.x = x;
		this.y = y;
		this.id = id;

	}

	public abstract void tick(LinkedList<GameObject> object);

	public abstract void render(Graphics g);

	public abstract Rectangle getBounds();
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float setX(float x) {
		return this.x = x;
	}

	public float setY(float y) {
		return this.y = y;
	}

	public float getVelX() {
		return velX;
	}

	public float getVelY() {
		return velY;
	}

	public float setVelX(float velX) {
		return this.velX = velX;
	}

	public float setVelY(float velY) {
		return this.velY = velY;
	}

	public ObjectId getId() {
		return id;
	}

	public boolean isFalling() {
		return falling;
	}
	
	public boolean isDead(){
		return dead;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public int getFacing() {
		return facing;
	}

	public void setContact() {

	}
	
	public void hurt(int pain){
		
	}

}
