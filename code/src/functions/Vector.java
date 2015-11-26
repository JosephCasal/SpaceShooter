package functions;

public class Vector {
	
	protected float velocity;
	public float position;
	protected float angle;
	public long startTime;
	
	public Vector(float velocity,float position, float angle){
		this.velocity = velocity * 1000;
		this.position = position;
		this.angle = angle;
		this.startTime = System.nanoTime();
	}
	
	public float get(){
		float time = (float) ((System.nanoTime() - this.startTime)/1000000000.0);
		float x = velocity*angle*(time) + position;
		return x;
	}

}
