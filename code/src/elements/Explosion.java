package elements;

import org.newdawn.slick.Animation;

import extras.AnimationImages;

public class Explosion extends AbstractElement{

	protected Animation a;
	protected int index;
	
	public Explosion(AnimationImages ai, float x, float y){
		this.x = x;
		this.y = y;
		this.a = ai.getAnimation();
		this.a.setLooping(false);
		this.index = 0;
	}
	
	public boolean draw() {
		a.draw(this.x, this.y);
		if (this.a.getFrame() == this.a.getFrameCount() - 1) {
			return true;
		}
		return false;
	}
	
}
