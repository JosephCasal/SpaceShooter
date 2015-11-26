package extras;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Background {

	private Image map;
	private Image map2;
	private float mapX;
	private float map2X;
	private final int Y;
	private float speed;
	
	public Background(float speed, Image image) throws SlickException {
		this.mapX = 0;
		this.Y = 0;
		this.map = image;
		this.map2 = image;
		this.map2X = map.getWidth() - 1;
		this.speed = speed;
	}
	
	public void play(){
		map.draw(mapX, Y);
		map2.draw(map2X, Y);
	}
	
	public void update(int delta){
		mapX = mapX - delta*this.speed;
		map2X = map2X - delta*this.speed;
		if(mapX < (-1*map.getWidth())){
			mapX = map2X + map.getWidth() - 1;
		}
		if(map2X < (-1*map.getWidth())){
			map2X = mapX + map.getWidth() - 1;
		}
	}

	public float getMapX() {
		return mapX;
	}

	public float getMap2X() {
		return map2X;
	}
	
}
