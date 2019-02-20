package com.Rydebrink.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.Timer;
import com.Rydebrink.EnemyObjects.Snail;
import com.Rydebrink.Framework.GameObject;
import com.Rydebrink.Framework.ObjectId;
import com.Rydebrink.Framework.Texture;
import com.Rydebrink.Window.Animation;
import com.Rydebrink.Window.Camera;
import com.Rydebrink.Window.Game;
import com.Rydebrink.Window.Handeler;

public class Player extends GameObject {
	private float width = 32, height = 32;
	private float gravity = 0.5f;
	private final float MAX_SPEED = 10;
	private Handeler handeler;
	@SuppressWarnings("unused")
	private Camera cam;
	public static int health;
	public static int shells;
	public static boolean flinching;
	private Timer timer;
	private int collissionTimer;
	private boolean colRight = false, colLeft = false, colBot = false,
			colTop = false;

	Texture tex = Game.getInstance();

	private Animation playerWalk, playerWalkLeft, playerFlinchRight,
			playerFlinchLeft;

	public Player(float x, float y, Handeler handeler, Camera cam, ObjectId id) {
		super(x, y, id);
		this.handeler = handeler;
		this.cam = cam;
		health = 100;
		flinching = false;
		timer = new Timer(2, new PlayerTimer());

		playerWalk = new Animation(5, tex.player[1], tex.player[2],
				tex.player[3], tex.player[4]);
		playerWalkLeft = new Animation(5, tex.player[6], tex.player[7],
				tex.player[8], tex.player[9]);

		playerFlinchRight = new Animation(5, tex.playerFlinching[1],
				tex.playerFlinching[2], tex.playerFlinching[3],
				tex.playerFlinching[4]);
		playerFlinchLeft = new Animation(5, tex.playerFlinching[6],
				tex.playerFlinching[7], tex.playerFlinching[8],
				tex.playerFlinching[9]);
	}

	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;

		if (velX < 0)
			facing = -1;
		else if (velX > 0)
			facing = 1;
		if (falling || jumping) {
			velY += gravity;
			if (velY > MAX_SPEED) {
				velY = MAX_SPEED;
			}
		}
		Collision(object);
		playerWalk.runAnimation();
		playerWalkLeft.runAnimation();
		playerFlinchLeft.runAnimation();
		playerFlinchRight.runAnimation();

	}

	private void Collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handeler.object.size(); i++) {

			GameObject tempObject = handeler.object.get(i);

			if (tempObject.getId() == ObjectId.Snail && tempObject.isDead()) {
				if (getBoundsRight().intersects(tempObject.getBounds())) {
					handeler.removeObject(tempObject);
					shells++;
				}
			}

			if (tempObject.getId() == ObjectId.Snail && !tempObject.isDead()) {
				if (getBoundsRight().intersects(tempObject.getBounds())
						&& !flinching) {
					// Move the player away from the enemy
					Snail.hit(Snail.damage);
					velX = 0;
					colRight = true;
					timer.start();
				}
				if (getBoundsLeft().intersects(tempObject.getBounds())
						&& !flinching) {
					// Move the player away from the enemy
					Snail.hit(Snail.damage);
					velX = 0;
					colLeft = true;
					timer.start();
				}
				if (getBounds().intersects(tempObject.getBounds())
						&& !flinching) {
					tempObject.hurt(15);
					colBot = true;
					timer.start();
				}
				if (getBoundsTop().intersects(tempObject.getBounds())
						&& !flinching) {
					Snail.hit(Snail.damage);
					colTop = true;
					timer.start();
				}
			}
			if (tempObject.getId() == ObjectId.InvisibleBlock) {
				if (getBounds().intersects(tempObject.getBounds())
						|| getBoundsTop().intersects(tempObject.getBounds())
						|| getBoundsRight().intersects(tempObject.getBounds())
						|| getBoundsLeft().intersects(tempObject.getBounds())) {
					tempObject.setContact();
				}
			}

			if (tempObject.getId() == ObjectId.Block
					|| tempObject.getId() == ObjectId.InvisibleBlock
					|| (tempObject.getId() == ObjectId.MagicBlock && TimeBlock
							.isPressed())) {
				if (getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + 32;
					velY = 0;

				}
				if (getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;

				} else {
					falling = true;

				}
				// Right
				if (getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - width;

				}
				// Left
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 33;

				}
			} else if (tempObject.getId() == ObjectId.Coin) {

				if (getBounds().intersects(tempObject.getBounds())) {
					handeler.removeObject(tempObject);
					Game.score++;
				}
			} else if (tempObject.getId() == ObjectId.Key) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handeler.removeObject(tempObject);
					Game.currentKeys++;
				}
			}

			else if (tempObject.getId() == ObjectId.Flag) {
				// Switch level
				if (getBounds().intersects(tempObject.getBounds())
						&& Game.currentKeys == Game.maxKeys) {
					handeler.switchLevel();
				}

			} else if (tempObject.getId() == ObjectId.TimeBlock) {
				// Make blocks appear
				if (getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + 32;
					velY = 0;
					TimeBlock.setPressed();
					TimeBlock.startTimer();
				}
				if (getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				} else {
					falling = true;
				}
				// Right
				if (getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - width;
				}
				// Left
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 33;
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("Shells: " + shells, (int) getX(), (int) getY() - 50);
		g.drawString("Health: " + health, (int) getX(), (int) getY() - 35);
		g.drawString("Score: " + Game.score, (int) getX(), (int) getY() - 5);
		g.drawString("Keys: " + Game.currentKeys + "/" + Game.maxKeys,
				(int) getX(), (int) getY() - 20);
		if (jumping && !flinching) {
			if (facing == 1) {
				g.drawImage(tex.playerJump[0], (int) x, (int) y, null);
			} else if (facing == -1) {
				g.drawImage(tex.playerJump[1], (int) x, (int) y, null);
			}
		} else if (!flinching) {
			if (velX != 0) {
				if (facing == 1) {
					playerWalk.drawAnimation(g, (int) x, (int) y);
				} else {
					playerWalkLeft.drawAnimation(g, (int) x, (int) y);
				}
			} else {
				if (facing == 1) {
					g.drawImage(tex.player[0], (int) x, (int) y, null);
				} else if (facing == -1) {
					g.drawImage(tex.player[5], (int) x, (int) y, null);
				}
			}
		}
		if (jumping && flinching) {
			if (facing == 1) {
				g.drawImage(tex.playerFlinching[1], (int) x, (int) y, null);
			} else if (facing == -1) {
				g.drawImage(tex.playerFlinching[6], (int) x, (int) y, null);
			}
		} else if (flinching) {
			if (velX != 0) {
				if (facing == 1) {
					playerFlinchRight.drawAnimation(g, (int) x, (int) y);
				} else {
					playerFlinchLeft.drawAnimation(g, (int) x, (int) y);
				}
			} else {
				if (facing == 1) {
					g.drawImage(tex.playerFlinching[0], (int) x, (int) y, null);
				} else if (facing == -1) {
					g.drawImage(tex.playerFlinching[5], (int) x, (int) y, null);
				}
			}
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) ((int) x + (width / 2) - (width / 2 / 2)),
				(int) ((int) y + (height / 2)), (int) width / 2,
				(int) height / 2);
	}

	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int) x + (width / 2) - (width / 2 / 2)),
				(int) y, (int) width / 2, (int) height / 2);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int) x + width - 5), (int) y + 5, (int) 5,
				(int) height - 10);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) y + 5, (int) 5, (int) height - 10);
	}

	private class PlayerTimer implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			collissionTimer++;
			if (colRight) {
				x = x - 1;
			} else if (colLeft) {
				x = x + 1;
			} else if (colBot) {
				velY = -8;
				jumping = false;
			} else if (colTop) {
				velY = 0;
			}
			if (collissionTimer >= 64) {
				collissionTimer = 0;
				colRight = false;
				colTop = false;
				colLeft = false;
				colBot = false;
				timer.stop();
			}
		}
	}
}
