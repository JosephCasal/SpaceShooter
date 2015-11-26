package elements;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

import extras.AnimationImages;
import functions.Vector;
import states.Game;

public class Ship extends Element{
	
	public float angle;
	public float frontPointX;
	public float frontPointY;
	public float frontPointXfirst;
	public float frontPointYfirst;
	public float[] points;
	public float xx;
	public float yy;
	public float xxfirst;
	public float yyfirst;
	public float xxx;
	public float yyy;
	public float xxxfirst;
	public float yyyfirst;
	public int activebullet;
	public int available2count;

	public Ship(AnimationImages moving, AnimationImages notMoving, float x, float y, int health, float speed, Shape shape, float[] points) throws SlickException {
		super(moving, notMoving, x, y, health, speed, shape);
		this.setNotMoving();
		this.angle = 0;
		this.frontPointX = this.getX() + this.getWidth();
		this.frontPointY = this.getY() + (this.getHeight()/2);
		frontPointXfirst = this.frontPointX;
		frontPointYfirst = this.frontPointY;
		this.points = points;
//		this.xx = 390.4f + 30;
//		this.yy = 286.4f;
//		this.xxfirst = 390.4f + 30;
//		this.yyfirst = 286.4f;
//		this.xxx = 390.4f + 30;
//		this.yyy = 286.4f + 28;
//		this.xxxfirst = 390.4f + 30;
//		this.yyyfirst = 286.4f + 28;
		this.xx = this.x + 30;
		this.yy = this.y;
		this.xxfirst = this.x + 30;
		this.yyfirst = this.y;
		this.xxx = this.x + 30;
		this.yyy = this.y + 28;
		this.xxxfirst = this.x + 30;
		this.yyyfirst = this.y + 28;
		this.activebullet = 1;
		this.available2count = 4;
//		System.out.println(this.speed);
	}

	public Ship(AnimationImages moving, AnimationImages notMoving, float speed, Shape shape, float[] points) throws SlickException {
		super(moving, notMoving, speed, shape);
		this.setNotMoving();
		this.angle = 0;
		this.frontPointX = this.getX() + this.getWidth();
		this.frontPointY = this.getY() + (this.getHeight()/2);
		frontPointXfirst = this.frontPointX;
		frontPointYfirst = this.frontPointY;
		this.points = points;
	}
	
	public void setAngle(Image image, int mousex, int mousey, float sx, float sy){
		
		float x = mousex - (sx + image.getCenterOfRotationX());
		float y = mousey - (sy + image.getCenterOfRotationY());
	
		if(x == 0 ){
			this.angle = (float) (((y > 0)?Math.PI/2: (3*Math.PI/2))*180/Math.PI);
			rotateFrontPoint(image, sx, sy, angle);
			return;
		}
		if(y == 0 ){
			this.angle = (float)((x > 0)?0: (Math.PI)*180/Math.PI);
			rotateFrontPoint(image, sx, sy, angle);
			return;
		}
		
		float angle = (float) Math.atan(y/x);
		
		if((x < 0 && y > 0) || (x < 0 && y < 0))
			angle += Math.PI;
		if(x > 0 && y < 0 )
			angle += 2*Math.PI; 
		rotateFrontPoint(image, sx, sy, angle);
		
		this.angle = (float) (angle*180/Math.PI);
	}

	public void draw(int mouseX, int mouseY) throws SlickException{
		if(!this.paused){
			this.setAngle(this.getAnimation().getCurrentFrame(), mouseX, mouseY, this.getX(), this.getY());
			Image image = new Image(this.getAnimation().getCurrentFrame().getResourceReference());
			image.rotate(this.angle);
			image.draw(this.getX(), this.getY());
			this.getAnimation().draw(-200,-200);
		}else{
			Image image = new Image(this.getAnimation().getCurrentFrame().getResourceReference());
			image.rotate(this.angle);
			image.draw(this.getX(), this.getY());
		}
		float[] p = {xx, yy,
				this.frontPointX, this.frontPointY,
				xxx, yyy};
		this.collision = new Polygon(p);
	}
	
	public void rotateFrontPoint(Image image, float sx, float sy, float angle){
		if(!this.paused){
			
			float newx = (float) ((Math.cos(angle)) * (this.frontPointXfirst - (sx + image.getCenterOfRotationX())) -
		            (Math.sin(angle)) * (this.frontPointYfirst - (sy + image.getCenterOfRotationY())) + (sx + image.getCenterOfRotationX()));
			float newy = (float) ((Math.sin(angle)) * (this.frontPointXfirst - (sx + image.getCenterOfRotationX())) +
		            (Math.cos(angle)) * (this.frontPointYfirst - (sy + image.getCenterOfRotationY())) + (sy + image.getCenterOfRotationY()));
			if(angle == 270.0){
				newx = (sx + image.getCenterOfRotationX());
				newy = (sy + image.getCenterOfRotationY()) - (this.getWidth()/2);
			}else if(angle == 90){
				newx = (sx + image.getCenterOfRotationX());
				newy = (sy + image.getCenterOfRotationY()) + (this.getWidth()/2);
			}
			
			this.frontPointX = newx;
			this.frontPointY = newy;
			
			
			float newxx = (float) ((Math.cos(angle)) * (this.xxfirst - (sx + image.getCenterOfRotationX())) -
		            (Math.sin(angle)) * (this.yyfirst - (sy + image.getCenterOfRotationY())) + (sx + image.getCenterOfRotationX()));
			float newyy = (float) ((Math.sin(angle)) * (this.xxfirst - (sx + image.getCenterOfRotationX())) +
		            (Math.cos(angle)) * (this.yyfirst - (sy + image.getCenterOfRotationY())) + (sy + image.getCenterOfRotationY()));
			if(angle == 270.0){
				newxx = (sx + image.getCenterOfRotationX());
				newyy = (sy + image.getCenterOfRotationY()) - (this.getWidth()/2);
			}else if(angle == 90){
				newxx = (sx + image.getCenterOfRotationX());
				newyy = (sy + image.getCenterOfRotationY()) + (this.getWidth()/2);
			}
			
			this.xx = newxx;
			this.yy = newyy;
			
			
			float newxxx = (float) ((Math.cos(angle)) * (this.xxxfirst - (sx + image.getCenterOfRotationX())) -
		            (Math.sin(angle)) * (this.yyyfirst - (sy + image.getCenterOfRotationY())) + (sx + image.getCenterOfRotationX()));
			float newyyy = (float) ((Math.sin(angle)) * (this.xxxfirst - (sx + image.getCenterOfRotationX())) +
		            (Math.cos(angle)) * (this.yyyfirst - (sy + image.getCenterOfRotationY())) + (sy + image.getCenterOfRotationY()));
			if(angle == 270.0){
				newxxx = (sx + image.getCenterOfRotationX());
				newyyy = (sy + image.getCenterOfRotationY()) - (this.getWidth()/2);
			}else if(angle == 90){
				newxxx = (sx + image.getCenterOfRotationX());
				newyyy = (sy + image.getCenterOfRotationY()) + (this.getWidth()/2);
			}
			
			this.xxx = newxxx;
			this.yyy = newyyy;
			
		}
	}

//	@Override
	public void moveForward(int delta) {
		if(!this.paused){
			this.x += this.speed * delta;
			this.xx += this.speed * delta;
			this.xxx += this.speed * delta;
			if(this.x > Game.getScreenWidth() - this.width)
				this.x = Game.getScreenWidth() - this.width;
			if(this.xx > Game.getScreenWidth() - this.width)
				this.xx = Game.getScreenWidth() - this.width;
			if(this.xxx > Game.getScreenWidth() - this.width)
				this.xxx = Game.getScreenWidth() - this.width;
			if(!this.getAnimation().equals(this.moving))
				this.setMoving();
		}
		this.frontPointXfirst = this.getX() + this.getWidth();
		this.xxfirst = this.getX() + 30;
		this.xxxfirst = this.getX() + 30;
	}

//	@Override
	public void moveBack(int delta) {
		if(!this.paused){
			this.x -= this.speed * delta;
			this.xx -= this.speed * delta;
			this.xxx -= this.speed * delta;
			if(this.x < 0)
				this.x = 0;
			if(this.xx < 0)
				this.xx = 0;
			if(this.xxx < 0)
				this.xxx = 0;
			if(!this.getAnimation().equals(this.moving))
				this.setMoving();
		}
		this.frontPointXfirst = this.getX() + this.getWidth();
		this.xxfirst = this.getX() + 30;
		this.xxxfirst = this.getX() + 30;
	}

//	@Override
	public void moveUp(int delta) {
		if(!this.paused){
			this.y -= this.speed * delta;
			this.yy -= this.speed * delta;
			this.yyy -= this.speed * delta;
			if(this.y < 0)
				this.y = 0;
			if(this.yy < 0)
				this.yy = 0;
			if(this.yyy < 0)
				this.yyy = 0;
			if(!this.getAnimation().equals(this.moving))
				this.setMoving();
		}
		this.frontPointYfirst = this.getY() + (this.getHeight()/2);
		this.yyfirst = this.getY();
		this.yyyfirst = this.getY() + 28;
	}

//	@Override
	public void moveDown(int delta) {
		if(!this.paused){
			this.y += this.speed * delta;
			this.yy += this.speed * delta;
			this.yyy += this.speed * delta;
			if(this.y > Game.getScreenHeight() - this.height)
				this.y = Game.getScreenHeight() - this.height;
			if(this.yy > Game.getScreenHeight() - this.height)
				this.yy = Game.getScreenHeight() - this.height;
			if(this.yyy > Game.getScreenHeight() - this.height)
				this.yyy = Game.getScreenHeight() - this.height;
			if(!this.getAnimation().equals(this.moving))
				this.setMoving();
		}
		this.frontPointYfirst = this.getY() + (this.getHeight()/2);
		this.yyfirst = this.getY();
		this.yyyfirst = this.getY() + 28;
	}
	
	public void shoot() throws SlickException {
		if(!this.paused){
			Shape shape;
			if(activebullet == 1){
				Vector xv = new Vector(2.7f, this.frontPointX, (float) Math.cos(this.angle*Math.PI/180));
				Vector yv = new Vector(2.7f, this.frontPointY, (float) Math.sin(this.angle*Math.PI/180));
				float[] points = {this.frontPointX, this.frontPointY,
						this.frontPointX+6, this.frontPointY,
						this.frontPointX+6, this.frontPointY+3,
						this.frontPointX, this.frontPointY+3};
				shape = new Polygon(points);
				Bullet b = new Bullet(new Image("res/bullets/bullet" + activebullet + ".png"), xv, yv, 25, shape, activebullet);
				bullets.add(b);
			}else if(activebullet == 3){
				if(this.available2count > 0){
					Vector xv = new Vector(.3f, this.frontPointX, (float) Math.cos(this.angle*Math.PI/180));
					Vector yv = new Vector(.3f, this.frontPointY-10, (float) Math.sin(this.angle*Math.PI/180));
					float[] points = {this.frontPointX+20, this.frontPointY-10+4,
							this.frontPointX+55, this.frontPointY-10+4,
							this.frontPointX+55, this.frontPointY+38-10,
							this.frontPointX+20, this.frontPointY+38-10};
					shape = new Polygon(points);
					Bullet b = new Bullet(new Image("res/bullets/bullet" + activebullet + ".png"), xv, yv, 100, shape, activebullet);
					bullets.add(b);
					this.available2count--;
				}
			}
		}
	}
	
//	public void shoot2(int targetx, int targety, float sx, float sy) throws SlickException {
//		if(!this.paused){
//			float ang;
//			float x = targetx - sx;
//			float y = targety - sy;
//		
//			if(x == 0 ){
//				ang = (float) (((y > 0)?Math.PI/2: (3*Math.PI/2))*180/Math.PI);
//			}else if(y == 0 ){
//				ang = (float)((x > 0)?0: (Math.PI)*180/Math.PI);
//			}else{
//				float angle = (float) Math.atan(y/x);
//				
//				if((x < 0 && y > 0) || (x < 0 && y < 0))
//					angle += Math.PI;
//				if(x > 0 && y < 0 )
//					angle += 2*Math.PI; 
//				
//				ang = (float) (angle*180/Math.PI);
//			}
//			
//			Vector xv = new Vector(2, sx, (float) Math.cos(ang*Math.PI/180));
//			Vector yv = new Vector(2, sy, (float) Math.sin(ang*Math.PI/180));
//			Bullet b = new Bullet(new Image("res/bullets/bullet" + activebullet + ".png"), xv, yv, 100);
//			bullets.add(b);
//			
//		}
//	}
	
//	public int updateBullets(ArrayList<Element> enemies, AllExplosions explosions) throws SlickException{
//		boolean check = false;
//		int killed = 0;
//		if(!this.paused){
//			for (int i = 0; i < bullets.size(); i++) {
//				bullets.get(i).setXY();
//				for (int j = 0; j < enemies.size(); j++) {
//					if(bullets.get(i).checkCollision(enemies.get(j), bullets.get(i).damage)){
//						explosions.addBulletHitExplosion(bullets.get(i), enemies.get(j));
//						bullets.remove(i);
//						i--;
//						if(enemies.get(j).checkHealth()){
//							explosions.addExplosion(enemies.get(j).x, enemies.get(j).y);
//							enemies.remove(j);
//							killed++;
//							j--;
//						}
//						check = true;
//						break;
//					}
//				}
//				if(check){
//					continue;
//				}
//				if(bullets.get(i).getX() > Game.getScreenWidth() || bullets.get(i).getX() < 0 || 
//						bullets.get(i).getY() > Game.getScreenHeight() || bullets.get(i).getY() < 0){
//					bullets.remove(i);
//					i--;
//				}
//			}
//		}
//		return killed;
//	}
	
	public int updateBullets(ArrayList<Element> enemies, AllExplosions explosions, ArrayList<Bullet> extrabullets) throws SlickException{
		boolean check;
		int killed = 0;
		int n = 0;
		if(!this.paused){
			for (int i = 0; i < bullets.size(); i++) {
				check = false;
				n = bullets.get(i).updatebullet(enemies, explosions, extrabullets);
				
				if(bullets.get(i).type == 3){
					if(n > 0){
						killed += n;
						check = true;
					}
				}else{
					if(n == 1 || n == 2){
						bullets.remove(i);
						i--;
						if(n == 2){
							killed++;
						}
						check = true;
					}
				}
				if(check){
					continue;
				}
				if(bullets.get(i).getX() > Game.getScreenWidth() || bullets.get(i).getX() < -100 || 
						bullets.get(i).getY() > Game.getScreenHeight() || bullets.get(i).getY() < -100){
					bullets.remove(i);
					i--;
				}
			}
		}
		return killed;
	}
	
	public boolean checkCollision(Element e, int damage){
//		//called from every enemy with player ship passed as e
//		//e.x is top left corner but need to move 30 units right because of flames of the back of ship
//		if(((this.x >= (e.x+30) && this.x <= (e.x+e.width)) && (this.y >= e.y && this.y <= (e.y+e.height)))
//			|| (((this.x+this.width) >= (e.x+30) && (this.x+this.width) <= (e.x+e.width)) && (this.y >= e.y && this.y <= (e.y+e.height)))
//			|| ((this.x >= (e.x+30) && this.x <= (e.x+e.width)) && ((this.y+this.height) >= e.y && (this.y+this.height) <= (e.y+e.height)))
//			|| (((this.x+this.width) >= (e.x+30) && (this.x+this.width) <= (e.x+e.width)) && ((this.y+this.height) >= e.y && (this.y+this.height) <= (e.y+e.height)))){
//			e.health = e.health - damage;
//			this.health = this.health - damage;
//			return true;
//		}
//		return false;
		
		if(this.collision.intersects(e.collision)){
			e.health = e.health - damage;
			this.health = this.health - damage;
			return true;
		}
		return false;
		
	}
	
}







