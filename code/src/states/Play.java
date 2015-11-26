package states;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import elements.AllExplosions;
import elements.Button;
import elements.Element;
import elements.Ship;
import elements.SimpleEnemy;
import elements.Bullet;
import extras.AnimationImages;
import extras.Background;
import extras.Intro;
import functions.LinearPath;
import functions.Path;

public class Play extends BasicGameState{
	
	private Ship s;
	private AllExplosions explosions;
	private ArrayList<Element> enemies;
	private Background bg, stars;
	private Button exit, resume, menu;
	private int mouseX;
	private int mouseY;
//	private Intro intro;
	Random rand = new Random();
//	private long spawnDelay;
//	private long count = 0;
//	private long count2 = 0;
	private boolean gameOver = false;
	private long starttime = System.nanoTime();
	private long currenttime = starttime;
	private long lasttime = 0;
	private int killed = 0;
	private int score = 0;
	private ArrayList<Bullet> extrabullets;		//needed for bullets of dead enemies
	private int specialbulletspawn = 30;
	private long lasttimeshot = starttime;
	private long lastspawn1 = 0;
	private long lastspawn2 = 0;
	private long lastspawn3 = 0;
//	private long pausetime = System.nanoTime();
	int temp = 0;
	
	public Play(int state){
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
		starttime = System.nanoTime();
		currenttime = starttime;
		lasttimeshot = starttime/100000000;
		lastspawn1 = 0;
		lastspawn2 = 0;
		lastspawn3 = 0;
//		pausetime = 0;
		lasttime = 0;
		killed = 0;
		score = 0;
		specialbulletspawn = 30;
		temp = 0;
		
		float[] points = {gc.getWidth()/4 + 30, (gc.getHeight()/4) + (gc.getHeight()/2),
				gc.getWidth()/4 + 30 + 34, (gc.getHeight()/4) + (gc.getHeight()/2) + 14,
				gc.getWidth()/4 + 30, (gc.getHeight()/4) + (gc.getHeight()/2) + 28};
		Shape shape = new Polygon(points);
		s = new Ship(new AnimationImages(new String[]{"res/playerShips/type1/red/f1.png",
				"res/playerShips/type1/red/f2.png","res/playerShips/type1/red/f3.png",
				"res/playerShips/type1/red/f4.png"}, 75, true), new AnimationImages(new String[]{
						"res/playerShips/type1/red/shipMovingBack.png"}, 75, true),
				gc.getWidth()/4, (gc.getHeight()/4) + (gc.getHeight()/2), 100, 0.3f, shape, points);
		bg = new Background(0.03f, new Image("res/backgrounds/farback.png"));
		stars = new Background(0.1f, new Image("res/backgrounds/Starfield.png"));
		
		Image exitImage = new Image("res/buttons/exitGame.png");
		exit = new Button((gc.getWidth()/2) - (exitImage.getWidth()/2),
						(gc.getHeight()/2) - (exitImage.getHeight()/2) - 60, new Image("res/buttons/exitGame.png"));
		resume = new Button((gc.getWidth()/2) - (exitImage.getWidth()/2),
						(gc.getHeight()/2) - (exitImage.getHeight()/2), new Image("res/buttons/Resume.png"));
		menu = new Button((gc.getWidth()/2) - (exitImage.getWidth()/2),
				(gc.getHeight()/2) - (exitImage.getHeight()/2) + 60, new Image("res/buttons/MainMenu.png"));
		explosions = new AllExplosions();
		
		enemies = new ArrayList<Element>();
//		enemies.add(new SimpleEnemy(new AnimationImages(new String[]{"res/e_f1.png","res/e_f2.png","res/e_f3.png","res/e_f4.png", "res/e_f5.png", "res/e_f6.png"}, 75, true),
//					new AnimationImages(new String[]{"res/e_f1.png"}, 75, true), 1200, 500, 100, 0.2f, 
//					new ArrayList<Path>(Arrays.asList(new LinearPath(1200, 500, 0.2f, 500, -100)))));
//		for (int i = 0; i < 10; i++) {
//			int temp = rand.nextInt(gc.getHeight());
//			float temp2 = ((float)(rand.nextInt(11) + 15))/100;
//			enemies.add(new SimpleEnemy(new AnimationImages(new String[]{"res/e_f1.png","res/e_f2.png","res/e_f3.png","res/e_f4.png", "res/e_f5.png", "res/e_f6.png"}, 75, true),
//					new AnimationImages(new String[]{"res/e_f1.png"}, 75, true), 1400, temp, 100, temp2));
//		}
//		intro = new Intro(s, gc);
//		startTime = System.currentTimeMillis();
//		spawnDelay = 1000;
		extrabullets = new ArrayList<Bullet>();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		bg.play();
		stars.play();
//		if(intro.rendering(g, "GO!!!", 500, 360))
//			return;
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).getAnimation().draw(enemies.get(i).x,enemies.get(i).y);
			enemies.get(i).drawBullets(g);
//			g.draw(enemies.get(i).getCollision());
		}
		for (int i = 0; i < extrabullets.size(); i++) {
			extrabullets.get(i).draw();
//			g.draw(extrabullets.get(i).getCollision());
		}
		s.draw(mouseX, mouseY);
		s.drawBullets(g);
		explosions.draw();
//		g.drawString(spawnDelay+"  "+enemies.size() + " " + s.x, 200, 200);
//		g.drawString(gc.getWidth() + " " + gc.getHeight(), 200, 200);
//		g.drawString(s.x + " " + s.y + " " + mouseX + " " + mouseY + "\n" + s.frontPointX + " " + 
//					s.frontPointY + " \n" + s.frontPointXfirst + " " + s.frontPointYfirst, 200, 200);
//		g.draw(s.getCollision());
		if(killed == specialbulletspawn){
			s.available2count += 4;
			specialbulletspawn += 30;
		}
		if(!gc.isPaused() && !gameOver)
			currenttime = (System.nanoTime()/10000000)-(starttime/10000000) + lasttime;
//		System.out.println((System.nanoTime()/10000000) + " " + starttime/10000000 + " " + lasttime + " " + (currenttime));
		score = (int) ((currenttime/100) + (killed*5));
		g.drawString("Time: " + currenttime/100 + "\n"
				+ "Killed: " + killed + "\n"
				+ "Score: " + score, 10, 30);
		g.drawString("special: " + s.available2count + "\n"
				+ "enemies: " + enemies.size(), 10, 150);
		if(!gameOver){
			if(!gc.isPaused()){
//				if(count % spawnDelay == 0){
//				if((System.nanoTime()/10000000) - lastspawn1 ==200){
				if((System.nanoTime()/10000000) - (starttime/10000000) + lasttime - lastspawn1 == 70){
					int sx, sy, tx, ty;
					float speed;
					sx = rand.nextInt(100) + gc.getWidth()+30;
					sy = rand.nextInt(gc.getHeight() + 200) - 100;
					speed = ((float)(rand.nextInt(11) + 15))/100;
					tx = rand.nextInt(gc.getWidth());
					ty = rand.nextInt(gc.getHeight());
					float[] points = {sx+10,sy, sx+29,sy, sx+29,sy+11, sx+39,sy+11,
							sx+39,sy+24, sx,sy+24, sx,sy+11, sx+10,sy+11};
//					float[] points = {sx, sy, sx + 39, sy, sx + 39, sy + 29, sx, sy + 29};
					Shape shape = new Polygon(points);
					enemies.add(new SimpleEnemy(new AnimationImages(new String[]{"res/enemies/type1/yellow/e_f1.png",
							"res/enemies/type1/yellow/e_f2.png","res/enemies/type1/yellow/e_f3.png",
							"res/enemies/type1/yellow/e_f4.png", "res/enemies/type1/yellow/e_f5.png", 
							"res/enemies/type1/yellow/e_f6.png"}, 75, true),new AnimationImages(new String[]{
									"res/enemies/type1/yellow/e_f1.png"}, 75, true), sx, sy, 100, speed, 
						new ArrayList<Path>(Arrays.asList(new LinearPath(sx, sy, speed, tx, ty))), shape, points, 0));
//					count = 0;
//					if(spawnDelay != 300)
//						spawnDelay = spawnDelay - 25;
					lastspawn1 = (System.nanoTime()/10000000) - (starttime/10000000) + lasttime;
					temp++;
				}
				if((System.nanoTime()/10000000) - (starttime/10000000) + lasttime - lastspawn3 == 40){
					int sx, sy, tx, ty;
					float speed;
					sx = rand.nextInt(100) + gc.getWidth()+30;
					sy = rand.nextInt(gc.getHeight() + 200) - 100;
					speed = ((float)(rand.nextInt(11) + 40))/100;
					tx = -400;
					ty = rand.nextInt(gc.getHeight());
					float[] points = {sx+10,sy, sx+29,sy, sx+29,sy+11, sx+39,sy+11,
							sx+39,sy+24, sx,sy+24, sx,sy+11, sx+10,sy+11};
//					float[] points = {sx, sy, sx + 39, sy, sx + 39, sy + 29, sx, sy + 29};
					Shape shape = new Polygon(points);
					enemies.add(new SimpleEnemy(new AnimationImages(new String[]{"res/enemies/type1/purple/e_f1.png",
							"res/enemies/type1/purple/e_f2.png","res/enemies/type1/purple/e_f3.png",
							"res/enemies/type1/purple/e_f4.png", "res/enemies/type1/purple/e_f5.png", 
							"res/enemies/type1/purple/e_f6.png"}, 75, true),new AnimationImages(new String[]{
									"res/enemies/type1/purple/e_f1.png"}, 75, true), sx, sy, 50, speed, 
						new ArrayList<Path>(Arrays.asList(new LinearPath(sx, sy, speed, tx, ty))), shape, points, 2));
//					count = 0;
//					if(spawnDelay != 300)
//						spawnDelay = spawnDelay - 25;
					lastspawn3 = (System.nanoTime()/10000000) - (starttime/10000000) + lasttime;
					temp++;
				}
//				if(count2%1000 == 0){
				if((System.nanoTime()/10000000) - (starttime/10000000) + lasttime - lastspawn2 == 300){
					int sx, sy, tx, ty;
					float speed;
					sx = rand.nextInt(100) + gc.getWidth()+30;
					sy = rand.nextInt(gc.getHeight() + 200) - 100;
					speed = ((float)(rand.nextInt(11) + 15))/100;
					tx = rand.nextInt(gc.getWidth() - 40);
					ty = rand.nextInt(gc.getHeight() - 30);
					float[] points = {sx+10,sy, sx+29,sy, sx+29,sy+11, sx+39,sy+11,
									sx+39,sy+24, sx,sy+24, sx,sy+11, sx+10,sy+11};
//					float[] points = {sx, sy, sx + 39, sy, sx + 39, sy + 29, sx, sy + 29};
					Shape shape = new Polygon(points);
					enemies.add(new SimpleEnemy(new AnimationImages(new String[]{"res/enemies/type1/red/e_f1.png",
							"res/enemies/type1/red/e_f2.png","res/enemies/type1/red/e_f3.png",
							"res/enemies/type1/red/e_f4.png", "res/enemies/type1/red/e_f5.png", 
							"res/enemies/type1/red/e_f6.png"}, 75, true),new AnimationImages(new String[]{
									"res/enemies/type1/red/e_f1.png"}, 75, true), sx, sy, 100, speed, 
						new ArrayList<Path>(Arrays.asList(new LinearPath(sx, sy, speed, tx, ty))), shape, points, 1));
//					count2 = 0;
					lastspawn2 = (System.nanoTime()/10000000) - (starttime/10000000) + lasttime;
//					System.out.println(lastspawn2);
					temp++;
				}
//				count++;
//				count2++;
			}else{
				exit.draw();
				resume.draw();
				menu.draw();
				s.pause();
				for (int i = 0; i < enemies.size(); i++) {
					enemies.get(i).pause();
				}
				explosions.pause();
			}
		}else{
			exit.draw();
			menu.draw();
			s.pause();
			for (int i = 0; i < enemies.size(); i++) {
				enemies.get(i).pause();
			}
		}
		
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if(!gameOver){
			bg.update(delta);
			stars.update(delta);
		}
		Input in = gc.getInput();
//		if(intro.update(delta, in))
//			return;
		checkInput(in, gc, delta, sbg);
		if(!gc.isPaused() && !gameOver){
			mouseX = in.getMouseX();
			mouseY = in.getMouseY();
			killed += s.updateBullets(enemies, explosions, extrabullets);
			ArrayList<Element> e = new ArrayList<Element>();
			e.add(s);
			for (int i = 0; i < extrabullets.size(); i++) {
				int n = extrabullets.get(i).updatebullet(e, explosions, extrabullets);
				if(n == 2){
					gameOver = true;
				}
				if(n == 3){
					extrabullets.remove(i);
					i--;
				}
			}
			
			for (int i = 0; i < enemies.size(); i++) {
				((SimpleEnemy) enemies.get(i)).move(delta);
				
				if( ((SimpleEnemy) enemies.get(i)).type == 0  || ((SimpleEnemy) enemies.get(i)).type == 2){
					if(!((SimpleEnemy) enemies.get(i)).isActive()){
						int sx, sy, tx, ty;
						float speed;
						sx = (int) enemies.get(i).x;
						sy = (int) enemies.get(i).y;
						if(((SimpleEnemy) enemies.get(i)).type == 2){
							speed = ((float)(rand.nextInt(11) + 40))/100;
						}else{
							speed = ((float)(rand.nextInt(11) + 15))/100;
						}
						if( ((SimpleEnemy) enemies.get(i)).pathscount == 1 ){
							tx = -300;
						}else{
							tx = rand.nextInt(gc.getWidth());
						}
						ty = rand.nextInt(gc.getHeight());
						((SimpleEnemy) enemies.get(i)).addPath(new LinearPath(sx, sy, speed, tx, ty));
						((SimpleEnemy) enemies.get(i)).setactive(true);
						((SimpleEnemy) enemies.get(i)).pathscount++;
					}
				}if( ((SimpleEnemy) enemies.get(i)).type == 1 ){
					((SimpleEnemy) enemies.get(i)).shoot(s.frontPointX-20, s.frontPointY,
							((SimpleEnemy) enemies.get(i)).x, ((SimpleEnemy) enemies.get(i)).y);
					if(((SimpleEnemy) enemies.get(i)).updateBullets(e, explosions, extrabullets) > 0){
						gameOver = true;
					}
				}
				if(((SimpleEnemy) enemies.get(i)).checkCollision(s, 100)){
//					if(!gameOver){
						explosions.addExplosion(enemies.get(i).x, enemies.get(i).y);
						explosions.addExplosion(s.x+30, s.y);
						gameOver = true;
//					}
				}
				
				//remove if out of screen
				if(enemies.get(i).x < (-200) + enemies.get(i).width || enemies.get(i).y > Game.getScreenHeight()+200 || 
						enemies.get(i).y < (-200) - enemies.get(i).height){
					enemies.remove(i);
					i--;
				}
				
			}
		}
	}

	@Override
	public int getID() {
		return 1;
	}

	public void checkInput(Input in, GameContainer gc, int delta, StateBasedGame sbg) throws SlickException{
		if(in.isKeyPressed(Input.KEY_ESCAPE)){
			gc.pause();
			lasttime = currenttime;
//			System.out.println(lasttime);
			for (int i = 0; i < enemies.size(); i++) {
				((SimpleEnemy)enemies.get(i)).pausecall();
			}
//			System.out.println(lasttime + " " + ((System.nanoTime()/10000000)-(starttime/10000000)+lasttime));
//			pausetime = (System.nanoTime()/10000000) - (starttime/10000000);
			return;
		}
		if(gc.isPaused()){
			if(exit.clicked(in)){
				System.exit(0);
				return;
			}
			if(resume.clicked(in)){
				s.unPause();
				starttime = System.nanoTime();
				explosions.unpause();
				for (int i = 0; i < enemies.size(); i++) {
					enemies.get(i).unPause();
					((SimpleEnemy)enemies.get(i)).unpausecall();
				}
				gc.resume();
				return;
			}
			if(menu.clicked(in)){
				sbg.enterState(0);
				gameOver = false;
				return;
			}
		}
		
		if(in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
			if((System.nanoTime()/10000000) - lasttimeshot >= 15){
				s.shoot();
				lasttimeshot = System.nanoTime()/10000000;
			}
		}
		if(in.isKeyDown(Input.KEY_1)){
			s.activebullet = 1;
		}
		if(in.isKeyPressed(Input.KEY_E)){
			s.activebullet = 3;
			s.shoot();
			s.activebullet = 1;
		}
		if(in.isKeyDown(Input.KEY_W)){
			s.moveUp(delta);
			if(in.isKeyDown(Input.KEY_D)){
				s.moveForward(delta);
				return;
			}
			else if(in.isKeyDown(Input.KEY_A)){
				s.moveBack(delta);
				return;
			}
			return;
		}
		else if(in.isKeyDown(Input.KEY_S)){
			s.moveDown(delta);
			if(in.isKeyDown(Input.KEY_D)){
				s.moveForward(delta);
				return;
			}
			else if(in.isKeyDown(Input.KEY_A)){
				s.moveBack(delta);
				return;
			}
			return;
		}
		else if(in.isKeyDown(Input.KEY_A)){
			s.moveBack(delta);
			return;
		}
		else if(in.isKeyDown(Input.KEY_D)){
			s.moveForward(delta);
			return;
		}
		else{
			s.setNotMoving();
		}
	}

	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		this.init(gc, sbg);
		gc.resume();
		super.enter(gc, sbg);
		
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.leave(container, game);
	}

}
