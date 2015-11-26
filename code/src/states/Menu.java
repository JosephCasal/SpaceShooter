package states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import elements.Button;
import elements.Ship;
import extras.AnimationImages;
import extras.Background;

public class Menu extends BasicGameState{
	
	private Ship s;
	private Button playButton, exitButton;
	private Background bg, stars;

	public Menu(int state) {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		bg = new Background(0.03f, new Image("res/backgrounds/farback.png"));
		stars = new Background(0.1f, new Image("res/backgrounds/Starfield.png"));
		
		Image play = new Image("res/buttons/playNow.png");
		playButton = new Button((gc.getWidth()/2) - (play.getWidth()/2),(gc.getHeight()/2) - (play.getHeight()/2) - 30, play);
		
		Image exit = new Image("res/buttons/exitGame.png");
		exitButton = new Button((gc.getWidth()/2) - (exit.getWidth()/2), (gc.getHeight()/2) - (exit.getHeight()/2) + 30, exit);
		
		float[] points = {(gc.getWidth()/4) + 30, (gc.getHeight()/4) + (gc.getHeight()/2),
				(gc.getWidth()/4) + 30 + 34, (gc.getHeight()/4) + (gc.getHeight()/2) + 14,
				(gc.getWidth()/4) + 30, (gc.getHeight()/4) + (gc.getHeight()/2) + 28};
		Shape shape = new Polygon(points);
		
//		s = new Ship(gc.getWidth()/4, (gc.getHeight()/4) + (gc.getHeight()/2), 100);
		s = new Ship(new AnimationImages(new String[]{"res/playerShips/type1/red/f1.png",
				"res/playerShips/type1/red/f2.png", "res/playerShips/type1/red/f3.png",
				"res/playerShips/type1/red/f4.png"}, 75, true), new AnimationImages(new String[]{
						"res/playerShips/type1/red/shipMovingBack.png"}, 75, true),
				gc.getWidth()/4, (gc.getHeight()/4) + (gc.getHeight()/2), 100, 0.35f, shape, points);
		s.setMoving();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		bg.play();
		stars.play();
		playButton.draw();
		exitButton.draw();
		s.getAnimation().draw(s.getX(), s.getY());
//		g.drawString("mouseX: " + x + "\nmouseY: " + y, 200, 200);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		bg.update(delta);
		stars.update(delta);
		
		Input in = gc.getInput();
//		x = in.getMouseX();
//		y = in.getMouseY();
		if(playButton.clicked(in))
			sbg.enterState(1);
		if(exitButton.clicked(in))
			System.exit(0);
	}

	@Override
	public int getID() {
		return 0;
	}

	
	
}
