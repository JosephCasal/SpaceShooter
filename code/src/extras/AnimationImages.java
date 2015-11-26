package extras;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class AnimationImages {

	private Image[] images;
	private int[] duration;
	private boolean auotUpdate;
	private Animation animation;
	
	public AnimationImages(String[] images, int duration, boolean autoUpdate) throws SlickException{
		this.duration = new int[images.length];
		for (int i = 0; i < images.length; i++) {
			this.duration[i] = duration;
		}
		this.images = new Image[images.length];
		for (int i = 0; i < images.length; i++) {
			this.images[i] = new Image(images[i]);
		}
		this.setAuotUpdate(autoUpdate);
		this.animation = new Animation(this.images, this.duration, autoUpdate);
	}
	
	public AnimationImages(String[] images, int[] durations, boolean autoUpdate) throws SlickException{
		this.duration = durations;
		this.images = new Image[images.length];
		for (int i = 0; i < images.length; i++) {
			this.images[i] = new Image(images[i]);
		}
		this.setAuotUpdate(autoUpdate);
		this.animation = new Animation(this.images, this.duration, autoUpdate);
	}

	public Image[] getImages() {
		return images;
	}

	public Animation getAnimation() {
		return animation;
	}

	public boolean isAuotUpdate() {
		return auotUpdate;
	}

	public void setAuotUpdate(boolean auotUpdate) {
		this.auotUpdate = auotUpdate;
	}
	
}
