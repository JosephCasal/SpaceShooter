package functions;

public class LinearPath extends Path{
	
	public Vector xv;
	public Vector yv;
	protected float targetx;
	protected float targety;
	
	public LinearPath(float sx, float sy, float speed, float tx, float ty){
		float dx = tx - sx;
		float dy = ty - sy;
		float angle;
	
		if(dx == 0 ){
			angle = (float) (((dy > 0)?Math.PI/2: (3*Math.PI/2)));
		}
		else if(dy == 0 ){
			angle = (float)((dx > 0)?0: (Math.PI));
		}else{
			angle = (float) Math.atan(dy/dx);
			
			if((dx < 0 && dy > 0) || (dx < 0 && dy < 0))
				angle += Math.PI;
			if(dx > 0 && dy < 0 )
				angle += 2*Math.PI;
		}
		
		this.xv = new Vector(speed, sx, (float) Math.cos(angle));
		this.yv = new Vector(speed, sy, (float) Math.sin(angle));
		this.targetx = tx;
		this.targety = ty;
	}
	
	@Override
	public boolean setXY() {
		this.x = this.xv.get();
		this.y = this.yv.get();
		if((this.x <= targetx+0.5 && this.x >= targetx-0.5) || (this.y <= targety+0.5 && this.y >= targety-0.5)){
			return false;
		}
		return true;
	}
	
}
