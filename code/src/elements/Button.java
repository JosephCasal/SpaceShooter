package elements;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class Button {
	
	private int x;
	private int y;
	private Image image;
	private int rightEdge;
	private int botEdge;
	
	public Button(GameContainer gc, Image image){
		this.x = (gc.getWidth()/2) - (image.getWidth()/2);
		this.y = (gc.getHeight()/2) - (image.getHeight()/2) - 30;
		this.image = image;
		this.rightEdge = x + image.getWidth();
		this.botEdge = y + image.getHeight();
	}
	
	public Button(int x, int y, Image image){
		this.x = x;
		this.y = y;
		this.image = image;
		this.rightEdge = x + image.getWidth();
		this.botEdge = y + image.getHeight();
	}
	
	public boolean clicked(Input in){
		int mouseX = in.getMouseX();
		int mouseY = in.getMouseY();
		
		if((mouseX > x && mouseX < rightEdge) && (mouseY > y && mouseY < botEdge)){
			if(in.isMousePressed(0)){
				return true;
			}
		}
		
		return false;
	}
	
	public void draw(){
		this.image.draw(this.x, this.y);
	}

}
