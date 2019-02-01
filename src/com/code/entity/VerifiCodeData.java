package com.code.entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

public class VerifiCodeData {
	private String[] words;
    private List<Rectangle> rectangles;
    private BufferedImage image;
    
	public String[] getWords() {
		return words;
	}
	public void setWords(String[] words) {
		this.words = words;
	}
	public List<Rectangle> getRectangles() {
		return rectangles;
	}
	public void setRectangles(List<Rectangle> rectangles) {
		this.rectangles = rectangles;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
