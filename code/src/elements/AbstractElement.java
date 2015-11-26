package elements;

import org.newdawn.slick.geom.Shape;

public abstract class AbstractElement {
	
	public float x;
	public float y;
	public float speed;
	protected boolean paused = false;
	public int height;
	public int width;
	
	protected Shape collision;
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public void setCollision(Shape shape){
		collision = shape;
	}
	
	public Shape getCollision(){
		return collision;
	}
	
}
