package elements;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

import extras.AnimationImages;
import functions.LinearPath;
import functions.Path;
import functions.Vector;

public class SimpleEnemy extends Ship{
	
	protected ArrayList<Path> paths;
	protected boolean active;
	public int pathscount;
	public int type;					//0 = 2 random spots then leave screen
										//1 = shooter (bullet2) 1 random spot, stays there
	
	public long starttime;
	public long lasttimeshot;
	public long pausetime;
	
	public SimpleEnemy(AnimationImages moving, AnimationImages notMoving, float x, float y, int health, float speed, ArrayList<Path> p, Shape shape, float[] points, int type) throws SlickException{
		super( moving,  notMoving,  x,  y,  health,  speed, shape, points);
		this.setMoving();
		this.paths = p;
		this.active = true;
		this.pathscount = 1;
		this.type = type;
		if(this.type == 1){
			this.activebullet = 2;
		}
		this.lasttimeshot = 0;
		this.starttime = System.nanoTime();
		this.pausetime = 0;
	}
	
	public void move(int delta){
		if(!this.paused){
			if(paths.size() != 0){
						if(paths.get(0).setXY()){
							this.x = paths.get(0).x;
							this.y = paths.get(0).y;
							float[] p = {this.x+10,this.y, this.x+29,this.y, this.x+29,this.y+11, this.x+39,this.y+11,
									this.x+39,this.y+24, this.x,this.y+24, this.x,this.y+11, this.x+10,this.y+11};
//							float[] p = {this.x, this.y, this.x+39, this.y, this.x+39, this.y+29, this.x, this.y+29};
							this.collision = new Polygon(p);
							return;
						}
						this.x = paths.get(0).x;
						this.y = paths.get(0).y;
						this.paths.remove(0);
						float[] p = {this.x+10,this.y, this.x+29,this.y, this.x+29,this.y+11, this.x+39,this.y+11,
								this.x+39,this.y+24, this.x,this.y+24, this.x,this.y+11, this.x+10,this.y+11};
//						float[] p = {this.x, this.y, this.x+39, this.y, this.x+39, this.y+29, this.x, this.y+29};
						this.collision = new Polygon(p);
						return;
			}else{
				this.active = false;
			}
			float[] p = {this.x+10,this.y, this.x+29,this.y, this.x+29,this.y+11, this.x+39,this.y+11,
					this.x+39,this.y+24, this.x,this.y+24, this.x,this.y+11, this.x+10,this.y+11};
//			float[] p = {this.x, this.y, this.x+39, this.y, this.x+39, this.y+29, this.x, this.y+29};
			this.collision = new Polygon(p);
		}
	}

	@Override
	public void pause() throws SlickException {
		super.pause();
		if(this.active){
			if(paths.get(0) instanceof LinearPath){
				((LinearPath) paths.get(0)).xv.position = this.x;
				((LinearPath) paths.get(0)).yv.position = this.y;
			}
		}
	}

	@Override
	public void unPause() throws SlickException {
		super.unPause();
		long z = System.nanoTime();
		if(this.active){
			if(paths.get(0) instanceof LinearPath){
				((LinearPath) paths.get(0)).xv.startTime = z;
				((LinearPath) paths.get(0)).yv.startTime = z;
			}
		}
	}
	
	public boolean isActive(){
		return this.active;
	}
	
	public void setactive(boolean t){
		this.active = t;
	}
	
	public void addPath(Path p){
		this.paths.add(p);
	}

	public void shoot(float targetx, float targety, float sx, float sy) throws SlickException {
		if(!this.paused){
			sx = sx + 19;
			sy = sy + 20;
			if((System.nanoTime()/10000000) - (starttime/10000000) + pausetime - lasttimeshot == 200){
//			if((System.nanoTime()/10000000) - lasttimeshot == 200){
//			System.out.println((System.nanoTime()/10000000) + " " + (starttime/10000000) + " " + pausetime
//					+ " " + temp + " " + ((System.nanoTime()/10000000) - (starttime/10000000) + pausetime - temp));
				float ang;
				float x = targetx - sx;
				float y = targety - sy;
			
				if(x == 0 ){
					ang = (float) (((y > 0)?Math.PI/2: (3*Math.PI/2))*180/Math.PI);
				}else if(y == 0 ){
					ang = (float)((x > 0)?0: (Math.PI)*180/Math.PI);
				}else{
					float angle = (float) Math.atan(y/x);
					
					if((x < 0 && y > 0) || (x < 0 && y < 0))
						angle += Math.PI;
					if(x > 0 && y < 0 )
						angle += 2*Math.PI; 
					
					ang = (float) (angle*180/Math.PI);
				}
				
				Vector xv = new Vector(.3f, sx, (float) Math.cos(ang*Math.PI/180));
				Vector yv = new Vector(.3f, sy, (float) Math.sin(ang*Math.PI/180));
				Shape shape;
//				if(activebullet == 2){
					float[] points = {this.x, this.y,
							this.x+8, this.y,
							this.x+8, this.y+8,
							this.x, this.y};
					shape = new Polygon(points);
//				}
				Bullet b = new Bullet(new Image("res/bullets/bullet" + activebullet + ".png"), xv, yv, 100, shape, activebullet);
				bullets.add(b);
//				lasttimeshot = System.nanoTime()/10000000;
				lasttimeshot = (System.nanoTime()/10000000) - (starttime/10000000) + pausetime;
			}
		}
	}
	
	public void pausecall(){
		pausetime = (System.nanoTime()/10000000) - (starttime/10000000) + pausetime;
//		System.out.println(pausetime);
	}
	
	public void unpausecall(){
		starttime = System.nanoTime();
//		System.out.println((System.nanoTime()/10000000) - (starttime/10000000) + pausetime);
	}
	
}
