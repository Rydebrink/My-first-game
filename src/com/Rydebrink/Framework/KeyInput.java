package com.Rydebrink.Framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.Rydebrink.Objects.Bullet;
import com.Rydebrink.Objects.Player;
import com.Rydebrink.Objects.Shell;
import com.Rydebrink.Window.Handeler;

public class KeyInput extends KeyAdapter {

	Handeler handeler;

	public KeyInput(Handeler handeler) {
		this.handeler = handeler;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handeler.object.size(); i++) {
			GameObject tempObject = handeler.object.get(i);

			if (tempObject.getId() == ObjectId.Player) {
				if (key == KeyEvent.VK_D) {
					tempObject.setVelX(5);
				}
				if (key == KeyEvent.VK_A) {
					tempObject.setVelX(-5);
				}
				if (key == KeyEvent.VK_W && !tempObject.jumping) {
					tempObject.setJumping(true);
					tempObject.setVelY(-10);
				}
				if (key == KeyEvent.VK_SPACE) {
					handeler.addObject(new Bullet(tempObject.getX(), tempObject
							.getY(), ObjectId.Bullet, handeler, tempObject
							.getFacing() * 8));
				}
				if (key == KeyEvent.VK_ENTER && Player.shells!=0) {
					handeler.addObject(new Shell(tempObject.getX(), tempObject
							.getY(), ObjectId.Shell, handeler, tempObject
							.getFacing() * 8));
					Player.shells--;
				}
			}
		}

		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handeler.object.size(); i++) {
			GameObject tempObject = handeler.object.get(i);

			if (tempObject.getId() == ObjectId.Player) {
				if (key == KeyEvent.VK_D) {
					tempObject.setVelX(0);
				}
				if (key == KeyEvent.VK_A) {
					tempObject.setVelX(0);
				}
			}
		}

	}

}
