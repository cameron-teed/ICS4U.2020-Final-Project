package com.helloworld.main;

import java.awt.image.BufferedImage;

/*
* This class renders the egg.
*
* @author  Cameron Teed
* @version 1.0
* @since   2021-05-26
*/

public class Egg {
	
	private BufferedImage sprite;
	
	public Egg(BufferedImage ss) {
		this.sprite = ss;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height) {
		BufferedImage img = sprite.getSubimage((row * 50), (col * 50), width, height);
		
		return img;
	}
	
	public BufferedImage grabImage2(int col, int row, int width, int height) {
		BufferedImage img2 = sprite.getSubimage(0, 0, width, height);
		
		return img2;
	}
}
