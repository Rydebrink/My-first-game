package com.Rydebrink.Framework;

import java.awt.image.BufferedImage;

import com.Rydebrink.Window.BufferedImageLoader;

public class Texture {

	SpriteSheet bs, ps, pfs, ss, cs, bullet, key, normNumbers, invNumbers;
	private BufferedImage block_sheet = null, player_sheet = null,
			snail_sheet = null, coinSheet = null, bullet_sheet = null,
			key_sheet = null, normalNumbers_sheet = null,
			invertedNumbers_sheet = null, playerFlinching_sheet = null;

	public BufferedImage[] block = new BufferedImage[2];
	public BufferedImage[] player = new BufferedImage[10];
	public BufferedImage[] playerJump = new BufferedImage[2];
	public BufferedImage[] snail = new BufferedImage[21];
	public BufferedImage[] playerFlinching = new BufferedImage[12];
	public BufferedImage[] textures = new BufferedImage[2];
	public BufferedImage[] coin = new BufferedImage[1];
	public BufferedImage[] bullets = new BufferedImage[1];
	public BufferedImage[] bossKey = new BufferedImage[1];
	public BufferedImage[] normalNumbers = new BufferedImage[10];
	public BufferedImage[] invertedNumbers = new BufferedImage[10];

	public Texture() {

		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			block_sheet = loader.loadImage("/block_sheet.png");
			player_sheet = loader.loadImage("/player_sheet.png");
			playerFlinching_sheet = loader.loadImage("/playerFlinching_sheet.png");
			snail_sheet = loader.loadImage("/snail.png");
			coinSheet = loader.loadImage("/coin.png");
			bullet_sheet = loader.loadImage("/bullet_sheet.png");
			key_sheet = loader.loadImage("/bosskey.png");
			normalNumbers_sheet = loader.loadImage("/numbers.png");
			invertedNumbers_sheet = loader.loadImage("/numbers_pressed.png");
		} catch (Exception e) {
			e.printStackTrace();
		}

		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		pfs= new SpriteSheet(playerFlinching_sheet);
		ss = new SpriteSheet(snail_sheet);
		cs = new SpriteSheet(coinSheet);
		bullet = new SpriteSheet(bullet_sheet);
		key = new SpriteSheet(key_sheet);
		normNumbers = new SpriteSheet(normalNumbers_sheet);
		invNumbers = new SpriteSheet(invertedNumbers_sheet);
		getTextures();

	}

	private void getTextures() {
		block[0] = bs.grabImage(4, 1, 32, 32); // Grass
		block[1] = bs.grabImage(3, 1, 32, 32); // Dirt

		// Looking right
		player[0] = ps.grabImage(1, 6, 32, 32); // Idle player frame
		player[1] = ps.grabImage(2, 6, 32, 32); // Player walking
		player[2] = ps.grabImage(3, 6, 32, 32); // Player walking
		player[3] = ps.grabImage(4, 6, 32, 32); // Player walking
		player[4] = ps.grabImage(5, 6, 32, 32); // Player walking

		// Looking left
		player[5] = ps.grabImage(1, 4, 32, 32); // Idle player frame
		player[6] = ps.grabImage(2, 4, 32, 32); // Player walking
		player[7] = ps.grabImage(3, 4, 32, 32); // Player walking
		player[8] = ps.grabImage(4, 4, 32, 32); // Player walking
		player[9] = ps.grabImage(5, 4, 32, 32); // Player walking

//		 Flinching looking right
		 playerFlinching[0] = pfs.grabImage(1, 6, 32, 32);	//Idle player frame
		 playerFlinching[1] = pfs.grabImage(2, 6, 32, 32);	//Player walking /Jumping
		 playerFlinching[2] = pfs.grabImage(3, 6, 32, 32);	//Player walking
		 playerFlinching[3] = pfs.grabImage(4, 6, 32, 32);	//Player walking
		 playerFlinching[4] = pfs.grabImage(5, 6, 32, 32);	//Player walking
		 
//		 Flinching looking left
		 playerFlinching[5] = pfs.grabImage(1, 4, 32, 32);	//Idle player frame
		 playerFlinching[6] = pfs.grabImage(2, 4, 32, 32);	//Player walking /Jumping
		 playerFlinching[7] = pfs.grabImage(3, 4, 32, 32);	//Player walking
		 playerFlinching[8] = pfs.grabImage(4, 4, 32, 32);	//Player walking
		 playerFlinching[9] = pfs.grabImage(5, 4, 32, 32);	//Player walking

		// Snail
		snail[0] = ss.grabImage(1, 2, 32, 32); // Looking right
		snail[1] = ss.grabImage(2, 2, 32, 32);
		snail[2] = ss.grabImage(3, 2, 32, 32);
		snail[3] = ss.grabImage(4, 2, 32, 32);
		snail[4] = ss.grabImage(5, 2, 32, 32);
		snail[5] = ss.grabImage(6, 2, 32, 32);
		snail[6] = ss.grabImage(7, 2, 32, 32);
		snail[7] = ss.grabImage(8, 2, 32, 32);
		snail[8] = ss.grabImage(9, 2, 32, 32);

		snail[9] = ss.grabImage(1, 4, 32, 32); // Looking left
		snail[10] = ss.grabImage(2, 4, 32, 32);
		snail[11] = ss.grabImage(3, 4, 32, 32);
		snail[12] = ss.grabImage(4, 4, 32, 32);
		snail[13] = ss.grabImage(5, 4, 32, 32);
		snail[14] = ss.grabImage(6, 4, 32, 32);
		snail[15] = ss.grabImage(7, 4, 32, 32);
		snail[16] = ss.grabImage(8, 4, 32, 32);
		snail[17] = ss.grabImage(9, 4, 32, 32);

		snail[18] = ss.grabImage(10, 2, 32, 32); // Dead

		// Jumping
		playerJump[0] = ps.grabImage(2, 6, 32, 32); // Player jumping right
		playerJump[1] = ps.grabImage(2, 4, 32, 32); // Player jumping left

		// Textures
		textures[0] = bs.grabImage(2, 6, 32, 32); // Door for finish line.
													// (TopHalf)
		textures[1] = bs.grabImage(2, 7, 32, 32); // Door for finish line.
													// (Bottom half)
		coin[0] = cs.grabImage(1, 1, 32, 32); // Coin
		bullets[0] = bullet.grabImage(10, 19, 16, 16); // Bullet
		bossKey[0] = key.grabImage(1, 1, 32, 32); // Key

		normalNumbers[0] = normNumbers.grabImage(1, 1, 32, 32); // Number 0
		normalNumbers[1] = normNumbers.grabImage(2, 1, 32, 32); // Number 1
		normalNumbers[2] = normNumbers.grabImage(3, 1, 32, 32); // Number 2
		normalNumbers[3] = normNumbers.grabImage(4, 1, 32, 32); // Number 3
		normalNumbers[4] = normNumbers.grabImage(5, 1, 32, 32); // Number 4
		normalNumbers[5] = normNumbers.grabImage(6, 1, 32, 32); // Number 5
		normalNumbers[6] = normNumbers.grabImage(7, 1, 32, 32); // Number 6
		normalNumbers[7] = normNumbers.grabImage(8, 1, 32, 32); // Number 7
		normalNumbers[8] = normNumbers.grabImage(9, 1, 32, 32); // Number 8
		normalNumbers[9] = normNumbers.grabImage(10, 1, 32, 32); // Number 9

		invertedNumbers[0] = invNumbers.grabImage(1, 1, 32, 32); // Number 0
		invertedNumbers[1] = invNumbers.grabImage(2, 1, 32, 32); // Number 1
		invertedNumbers[2] = invNumbers.grabImage(3, 1, 32, 32); // Number 2
		invertedNumbers[3] = invNumbers.grabImage(4, 1, 32, 32); // Number 3
		invertedNumbers[4] = invNumbers.grabImage(5, 1, 32, 32); // Number 4
		invertedNumbers[5] = invNumbers.grabImage(6, 1, 32, 32); // Number 5
		invertedNumbers[6] = invNumbers.grabImage(7, 1, 32, 32); // Number 6
		invertedNumbers[7] = invNumbers.grabImage(8, 1, 32, 32); // Number 7
		invertedNumbers[8] = invNumbers.grabImage(9, 1, 32, 32); // Number 8
		invertedNumbers[9] = invNumbers.grabImage(10, 1, 32, 32); // Number 9
	}

}
