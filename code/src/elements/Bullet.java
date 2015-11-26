package elements;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

import states.Game;
import functions.Vector;

public class Bullet extends AbstractElement{
	
	protected Image image;
	protected long startTime;
	protected Vector xv;
	protected Vector yv;
	protected int damage;
	protected float lastX;
	protected float lastY;
	protected int type;
	
	public Bullet(Image image, Vector xv, Vector yv, int damage, Shape shape, int type){
		this.image = image;
		this.xv = xv;
		this.yv = yv;
		this.damage = damage;
		this.lastX = this.x;
		this.lastY = this.y;
		this.collision = shape;
		this.type = type;
	}
	
	public void setXY(){
		this.x = this.xv.get();
		this.y = this.yv.get();
	}
	
	public void draw(){
		this.lastX = this.x;
		this.lastY = this.y;
		image.draw(this.x, this.y);
		if(this.type == 1){
			float[] points = {this.x, this.y, this.x+6, this.y, this.x+6, this.y+3, this.x, this.y+3};
			this.collision = new Polygon(points);
		}else if(this.type == 3){
			float[] points = {this.x+20, this.y+4, this.x+55, this.y+4, this.x+55, this.y+38, this.x+20, this.y+38};
			this.collision = new Polygon(points);
		}else{
			float[] points = {this.x, this.y, this.x+8, this.y, this.x+8, this.y+8, this.x, this.y+8};
			this.collision = new Polygon(points);
		}
	}
	
	public boolean checkCollision(Element e, int damage){
//		if(((this.x >= e.x && this.x <= e.x + e.width) && (this.y >= e.y && y <= e.y + e.height))){
//			e.health = e.health - damage;
//			return true;
//		}
//		return false;
		if(this.collision.intersects(e.collision)){
			e.health = e.health - damage;
			return true;
		}
		return false;
	}
	
	
	public int updatebullet(ArrayList<Element> enemies, AllExplosions explosions, ArrayList<Bullet> extrabullets) throws SlickException{
		//return 0 if no hit and still in screen
		//return 1 if hit
		//return 2 if hit and kill
		//return 3 if out of screen
		//if type = 3 then return killcount or -1 if out of screen
		int killcount = 0;
		this.setXY();
		for (int j = 0; j < enemies.size(); j++) {
			if(this.checkCollision(enemies.get(j), this.damage)){
				explosions.addBulletHitExplosion(this, enemies.get(j));
				if(enemies.get(j).checkHealth()){
					explosions.addExplosion(enemies.get(j).x, enemies.get(j).y);
					if(enemies.get(j) instanceof SimpleEnemy && ((SimpleEnemy)enemies.get(j)).type == 1){
						for (int i = 0; i < enemies.get(j).bullets.size(); i++) {
							extrabullets.add(enemies.get(j).bullets.get(i));
						}
					}
					enemies.remove(j);
					j--;
					killcount++;
					if(this.type != 3)
						return 2;
				}
				if(this.type != 3)
					return 1;
			}
		}
		if(this.getX() > Game.getScreenWidth() || this.getX() < -100 || 
				this.getY() > Game.getScreenHeight() || this.getY() < -100){
			if(this.type == 3){
				return -1;
			}
			return 3;
		}
		if(this.type == 3){
			return killcount;
		}
		return 0;
	}
	
}
