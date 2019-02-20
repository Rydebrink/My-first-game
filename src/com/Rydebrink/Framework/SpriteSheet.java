package com.Rydebrink.Framework;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage image;

	public SpriteSheet(BufferedImage image) {
		this.image = image;
	}

	public BufferedImage grabImage(int col, int row, int width, int hight) {
		BufferedImage img = image.getSubimage(col * width - width, row * hight
				- hight, width, hight);
		return img;
	}
}
