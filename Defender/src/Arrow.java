public class Arrow {
	private int w,h,s;
	private double x,y;
	private double vx,vy,angle;
	
	public Arrow(double x, double y, int w, int h, int s, double angle) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.s = s;
		
		this.angle = angle;
		vx = Math.cos(angle);
		vy = Math.sin(angle);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public int getW() {
		return w;
	}
	
	public int getH() {
		return h;
	}
	
	public int getS() {
		return s;
	}
	
	public double getAng() {
		return angle;
	}
	
	public void move() {
		x += s * vx;
		y += s * vy;
	}
}
