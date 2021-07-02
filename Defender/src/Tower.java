public class Tower {
	private int w,h,s;
	private double x,y;
	
	public Tower(double x, double y, int w, int h, int s) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.s = s;
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

	public void move(double enemyx, double enemyy) {
		double X = enemyx - this.x;
		double Y = enemyy - this.y;
		
		double h = (int)Math.sqrt(Math.pow(X, 2) + Math.pow(Y, 2));
		
		int xMove = (int)(X/h * s);
		int yMove = (int)(Y/h * s);
		
		x+=xMove;
		y+=yMove;
	}
	
	public boolean collides(int x, int y, int w, int h) {
		return this.x < x + w && this.x + this.w > x && this.y < y + h && this.y + this.h > y;  //Boolean method used to check for collision with player and																			
	}	
}
