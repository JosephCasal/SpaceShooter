package elements;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import extras.AnimationImages;

public class AllExplosions {
	
	protected ArrayList<Explosion> a;
	protected boolean paused = false;
	
	public AllExplosions(){
		a = new ArrayList<Explosion>();
	}
	
	public void draw(){
		if(!this.paused){
			if(a.size() != 0){
				for (int i = 0; i < a.size(); i++) {
					if(a.get(i).draw())
						a.remove(i);
				}
			}
		}else{
			if(a.size() != 0){
				for (int i = 0; i < a.size(); i++) 
					a.get(i).a.getImage(a.get(i).index).draw(a.get(i).x, a.get(i).y);
			}
		}
	}
	
	public void addExplosion(float x, float y) throws SlickException{
		this.a.add(new Explosion(new AnimationImages(new String[]{"res/explosions/destroyed/explosion0.png",
				"res/explosions/destroyed/explosion1.png","res/explosions/destroyed/explosion2.png",
				"res/explosions/destroyed/explosion3.png", "res/explosions/destroyed/explosion4.png",
				"res/explosions/destroyed/explosion5.png","res/explosions/destroyed/explosion6.png",
				"res/explosions/destroyed/explosion7.png","res/explosions/destroyed/explosion8.png",
				"res/explosions/destroyed/explosion9.png","res/explosions/destroyed/explosion10.png",
				"res/explosions/destroyed/explosion11.png","res/explosions/destroyed/explosion12.png",
				"res/explosions/destroyed/explosion13.png","res/explosions/destroyed/explosion14.png",
				"res/explosions/destroyed/explosion15.png"}, new int[]{200, 150, 75, 75, 75, 75, 75, 75, 75,
				75, 75, 75, 75, 75, 75, 75}, true), x, y));
	}
	
	public void addBulletHitExplosion(Bullet b, Element e) throws SlickException{
		if(b.x >= e.x + e.width/2){
			b.x -= e.width/4;
		}else{
			b.x += e.width/14;
		}
		if(b.y >= e.y + e.height/2){
			b.y -= e.height/4;
		}else{
			b.y += e.height/4;
		}
		this.a.add(new Explosion(new AnimationImages(new String[]{"res/explosions/bulletHit/bulletHitExplosion1.png", 
				"res/explosions/bulletHit/bulletHitExplosion1.png"}, 200, true), b.x, b.y));
	}
	
	public void pause(){
		this.paused = true;
		for (int i = 0; i < a.size(); i++) {
			a.get(i).index = a.get(i).a.getFrame();
			a.get(i).a.stop();
		}
	}
	
	public void unpause(){
		this.paused = false;
		for (int i = 0; i < a.size(); i++) {
			a.get(i).a.restart();
			a.get(i).a.setCurrentFrame(a.get(i).index);
		}
	}

}
