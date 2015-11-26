package extras;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

import elements.Ship;

public class Intro {
	
	protected Ship s;
	protected Ship playerShip;
	protected boolean playing;
	protected int mousex;
	protected int mousey;
	protected long startTime;
	protected boolean check;
	protected boolean check2;
	protected float x;
	protected int y;
	protected boolean increasing;
	protected long secondStart;
	protected boolean checkSecondStart;
	
	public Intro(Ship playerShip, GameContainer gc) throws SlickException{
		float[] points = {(gc.getWidth()/4) + 30, (gc.getHeight()/4) + (gc.getHeight()/2),
				(gc.getWidth()/4) + 30 + 34, (gc.getHeight()/4) + (gc.getHeight()/2) + 14,
				(gc.getWidth()/4) + 30, (gc.getHeight()/4) + (gc.getHeight()/2) + 28};
		Shape shape = new Polygon(points);
		s = new Ship(new AnimationImages(new String[]{"res/playerShips/type1/red/f1.png",
				"res/playerShips/type1/red/f2.png","res/playerShips/type1/red/f3.png",
				"res/playerShips/type1/red/f4.png"}, 75, true), new AnimationImages(new String[]{
						"res/playerShips/type1/red/shipMovingBack.png"}, 75, true),
				gc.getWidth()/4, (gc.getHeight()/4) + (gc.getHeight()/2), 100, 0.2f, shape, points);
		this.playerShip = playerShip;
		this.s.x = -200;
		this.s.y = (gc.getHeight()/4) + (gc.getHeight()/2);
//		s.speed = 0.2f;
		this.s.setMoving();
		this.playing = true;
		this.mousex = (int) (1280);
		this.mousey = (int) (s.y);
		this.startTime = System.currentTimeMillis()/1000;
		this.check = true;
		this.check2 = false;
		this.x = 500;
		this.y = 100;
		this.increasing = true;
		this.secondStart = 0;
		this.checkSecondStart = false;
	}
	
	public boolean render(Graphics g) throws SlickException{
		if(this.isPlaying()){
			s.draw(mousex, mousey);
			if(x <= 300){
				this.x = (float) (x + 0.1);
				this.increasing = true;
			}else if(this.x >= 700){
				this.x = (float) (x - 0.1);
				this.increasing = false;
			}
			if(increasing){
				g.drawString("Welcome to Space Shooter!", x, y);
				this.x = (float) (x + 0.1);
			}else{
				g.drawString("Welcome to Space Shooter!", x, y);
				this.x = (float) (x - 0.1);
			}
//			g.drawString("Welcome to Space Shooter!", 500, 100);
			if(check2){
				g.drawString("GET READY...", 500, 400);
			}
			return true;
		}
		return false;
	}
	
	public boolean update(int delta, Input in){
		if(this.isPlaying()){
			if(in.isMousePressed(0) || in.isKeyPressed(Input.KEY_SPACE)){
				this.playing = false;
				this.s.x = 390;
				this.s.y = 286;
				this.s.moveForward(delta);
				this.s.moveDown(delta);
				return false;
			}
			if(check2){
				if((System.currentTimeMillis()/1000) - this.startTime <= 1.5){
					this.mousex = 1280;
					this.mousey = (int) s.y;
					return true;
				}
				this.playing = false;
				return true;
			}
			if((System.currentTimeMillis()/1000) - startTime <= 2){
				s.moveForward(delta);
				return true;
			}else{
//				System.out.println(s.angle);
				if(s.angle >= 270 || s.angle < 1){
					if(check){
						s.speed = 0.1f;
						this.mousey--;
						this.mousex--;
						check = false;
					}else{
						check = true;
					}
					s.moveForward(delta);
					s.moveUp(delta);
					return true;
				}
				else if(s.angle >= 180){
					if(check){
						this.mousey++;
						this.mousex--;
						check = false;
					}else{
						check = true;
					}
					s.moveBack(delta);
					s.moveUp(delta);
					return true;
				}
				else if(s.angle >= 90){
					if(check){
						this.mousey++;
						this.mousex++;
						check = false;
					}else{
						check = true;
					}
					s.moveBack(delta);
					s.moveDown(delta);
					return true;
				}
				else if(s.angle >= 3){
					if(check){
						this.mousey--;
						this.mousex++;
						check = false;
					}else{
						check = true;
					}
					s.moveForward(delta);
					s.moveDown(delta);
					return true;
				}
				else{
					this.mousex = 1280;
					this.mousey = (int) s.y;
					s.speed = 0.35f;
					this.check2 = true;
					this.startTime = System.currentTimeMillis()/1000;
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isPlaying() {
		return playing;
	}

	public boolean rendering(Graphics g, String string, float x, float y) throws SlickException{
		if(this.render(g)){
			this.secondStart = System.currentTimeMillis()/1000;
			return true;
		}else{
			if(!checkSecondStart){
				playerShip.x = s.x;
				playerShip.y = s.y;
				checkSecondStart = true;
			}
			if((System.currentTimeMillis()/1000) - this.secondStart <= 1.5)
				g.drawString(string, x, y);
			return false;
		}
	}
	
}
