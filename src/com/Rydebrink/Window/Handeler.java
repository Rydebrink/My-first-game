package com.Rydebrink.Window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import com.Rydebrink.EnemyObjects.Snail;
import com.Rydebrink.Framework.GameObject;
import com.Rydebrink.Framework.ObjectId;
import com.Rydebrink.Objects.Block;
import com.Rydebrink.Objects.Coin;
import com.Rydebrink.Objects.EnemyZoneBlock;
import com.Rydebrink.Objects.Flag;
import com.Rydebrink.Objects.InvisibleBlock;
import com.Rydebrink.Objects.Key;
import com.Rydebrink.Objects.MagicBlock;
import com.Rydebrink.Objects.Player;
import com.Rydebrink.Objects.TimeBlock;
import com.Rydebrink.Objects.TransparentBlock;

public class Handeler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();

	private GameObject tempObject;

	private Camera cam;
	private BufferedImage level2 = null, level = null, level3 = null;

	public Handeler(Camera cam) {
		this.cam = cam;

		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level.png"); // loading the level
		level2 = loader.loadImage("/level2.png"); // loading the level
		level3 = loader.loadImage("/level3.png"); // loading the level
	}

	public void tick() {

		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.tick(object);

		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);

			tempObject.render(g);
		}
	}


	private void loadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		for (int xx = 0; xx < h; xx++) {
			for (int yy = 0; yy < w; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 255 && green == 255 && blue == 255)
					addObject(new Block(xx * 32, yy * 32, 0, ObjectId.Block)); // Grass
																				// block

				if (red == 109 && green == 109 && blue == 109)
					addObject(new Block(xx * 32, yy * 32, 1, ObjectId.Block)); // Dirt
																				// block
				if (red == 188 && green == 188 && blue == 188)
					addObject(new TransparentBlock(xx * 32, yy * 32, 0,
							ObjectId.TransparentBlock)); // Grass block

				if (red == 132 && green == 132 && blue == 132)
					addObject(new TransparentBlock(xx * 32, yy * 32, 1,
							ObjectId.TransparentBlock)); // Dirt block

				if (red == 160 && green == 255 && blue == 160)
					addObject(new InvisibleBlock(xx * 32, yy * 32,
							ObjectId.InvisibleBlock)); // Dirt block

				if (red == 0 && green == 255 && blue == 255
						&& !TimeBlock.pressed)
					addObject(new MagicBlock(xx * 32, yy * 32, 0,
							ObjectId.MagicBlock)); // Grass block

				if (red == 153 && green == 102 && blue == 51
						&& !TimeBlock.pressed)
					addObject(new MagicBlock(xx * 32, yy * 32, 1,
							ObjectId.MagicBlock)); // Dirt block

				if (red == 255 && green == 0 && blue == 0)
					addObject(new TimeBlock(xx * 32, yy * 32, 6,
							ObjectId.TimeBlock));

				 if (red == 128 && green == 0 && blue == 128)
				 addObject(new TimeBlock(xx * 32, yy * 32, 4,
				 ObjectId.TimeBlock));

				if (red == 0 && green == 0 && blue == 255)
					addObject(new Player(xx * 32, yy * 32, this, cam,
							ObjectId.Player));

				if (red == 255 && green == 225 && blue == 0)
					addObject(new Flag(xx * 32, yy * 32, 0, ObjectId.Flag));

				if (red == 255 && green == 129 && blue == 62)
					addObject(new Flag(xx * 32, yy * 32, 1, ObjectId.Flag));

				if (red == 115 && green == 255 && blue == 50)
					addObject(new Coin(xx * 32, yy * 32, ObjectId.Coin));

				if (red == 255 && green == 0 && blue == 255) {
					addObject(new Key(xx * 32, yy * 32, ObjectId.Key));
					Game.maxKeys++;
				}
				if(red == 81 && green == 70 && blue == 255)
					addObject(new Snail(xx*32, yy*32, this, ObjectId.Snail));
				
				if (red == 255 && green == 255 && blue == 188)
					addObject(new EnemyZoneBlock(xx * 32, yy * 32, ObjectId.EnemyZoneBlock)); // Grass
																						// block
			}
		}
	}

	public void addObject(GameObject object) {
		this.object.add(object);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	

	public void switchLevel() {
		clearLevel();
		cam.setX(0);
		Game.currentKeys = 0;
		Game.maxKeys = 0;
		Player.health=100;
		switch (Game.LEVEL) {
		case 1:
			loadImageLevel(level);
			break;
		case 2:
			loadImageLevel(level2);
			break;
		case 3:
			loadImageLevel(level3);
			break;
		}
		Game.LEVEL++;
	}

	public void clearLevel() {
		object.clear();
	}
}
