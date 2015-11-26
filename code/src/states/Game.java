package states;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame{
	
	private static final String gameName = "Space Shooter 2.0";
	private static final int menu = 0;
	private static final int play = 1;
	private static final int width = 1280;
	private static final int height = 720;

	public Game(String gameName){
		super(gameName);
		this.addState(new Menu(menu));
		this.addState(new Play(play));
	}
	
	public void initStatesList(GameContainer gc) throws SlickException{
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.enterState(menu);
	}
	
	public static void main(String[] args) throws LWJGLException {
		
		AppGameContainer appgc;
		try{
			appgc = new AppGameContainer(new Game(gameName));
//			DisplayMode[] modes = Display.getAvailableDisplayModes();
//			for (int i = 0; i < modes.length; i++) {
//				DisplayMode x = modes[i];
//				System.out.println(x.getWidth() + " " + x.getHeight() + " " + x.getBitsPerPixel() + " " + x.getFrequency());
//			}
			appgc.setDisplayMode(width, height, true);
			appgc.start();
		}catch(SlickException e){
			e.printStackTrace();
		}

	}

	public static int getScreenWidth(){
		return width;
	}
	
	public static int getScreenHeight(){
		return height;
	}
	
}
