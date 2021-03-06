package net.geforcemods.rologia.os.misc;

public class Position {
	
	private int xPos;
	private int yPos;
	
	public Position() {
		xPos = 0;
		yPos = 0;
	}
	
	public Position(int x, int y) {
		xPos = x;
		yPos = y;
	}
	
	public int getX() {
		return xPos;
	}
	
	public int getY() {
		return yPos;
	}
	
	public void setX(int x) {
		xPos = x;
	}
	
	public void setY(int y) {
		yPos = y;
	}
	
	public Position shiftX(int x) {
		return new Position(xPos + x, yPos);
	}
	
	public Position shiftY(int y) {
		return new Position(xPos, yPos + y);
	}
	
	public Position scalePos(float scale) {
		xPos = (int) (xPos / scale);
		yPos = (int) (yPos / scale);
		return this;
	}

	public Position transpose() {
		int x = xPos;
		xPos = yPos;
		yPos = x;
		return this;
	}

	public boolean matches(Position pos) {
		return pos.getX() == xPos && pos.getY() == yPos;
	}

	public boolean matches(int x, int y) {
		return x == xPos && y == yPos;
	}

}
