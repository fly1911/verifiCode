package com.code.entity;

public class ClickPoint {
    private int x;
    private int y;
    
    public ClickPoint() {
	}

	public ClickPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}