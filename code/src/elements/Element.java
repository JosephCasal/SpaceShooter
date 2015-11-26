package elements;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import extras.AnimationImages;

public abstract class Element extends AbstractElement{
	
	protected int health;
	protected Animation moving;
	protected Animation notMoving;
	protected Animation currentAnimation;
	public ArrayList<Bullet> bullets;
	
	public Element(){
		
	}
	
	public Element(AnimationImages moving, AnimationImages notMoving, float speed, Shape shape) throws SlickException {
		this.setX(0);
		this.setY(0);
		this.health = 100;
		this.setMovingAnimation(moving.getAnimation());
		this.setNotMovingAnimation(notMoving.getAnimation());
		this.setWidth(moving.getImages()[0].getWidth());
		this.setHeight(moving.getImages()[0].getHeight());
		this.speed = speed;
		this.bullets = new ArrayList<Bullet>();
		this.setCollision(shape);
	}
	
	public Element(AnimationImages moving, AnimationImages notMoving, float x, float y, int health, float speed, Shape shape) throws SlickException {
		this.setX(x);
		this.setY(y);
		this.health = health;
		this.setMovingAnimation(moving.getAnimation());
		this.setNotMovingAnimation(notMoving.getAnimation());
		this.setWidth(moving.getImages()[0].getWidth());
		this.setHeight(moving.getImages()[0].getHeight());
		this.speed = speed;
		this.bullets = new ArrayList<Bullet>();
		this.setCollision(shape);
	}
	
	public void pause() throws SlickException{
		this.paused = true;
		this.currentAnimation = notMoving;
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).xv.position = bullets.get(i).x;
			bullets.get(i).yv.position = bullets.get(i).y;
		}
	}
	
	public void unPause() throws SlickException{
		this.paused = false;
		this.setMoving();
		for (int i = 0; i < bullets.size(); i++) {
			long z = System.nanoTime();
			bullets.get(i).xv.startTime = z;
			bullets.get(i).yv.startTime = z;
		}
	}
	
	public void setMoving(){
		this.currentAnimation = moving;
	}

	public Animation getAnimation() {

		return currentAnimation;
	}
	
	public void setMovingAnimation(Animation a) throws SlickException {
		this.moving = a;
	}
	
	public void setNotMovingAnimation(Animation a) throws SlickException {
		this.notMoving = a;
	}

	public void setNotMoving() {
		this.currentAnimation = notMoving;
	}

	public boolean checkHealth(){
		if(this.health <= 0){
			return true;
		}
		return false;
	}
	
	public void drawBullets(Graphics g){
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw();
//			g.draw(bullets.get(i).getCollision());
		}
	}
	
}
