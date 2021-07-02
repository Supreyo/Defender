public class EnemyProjectile {
	int px,py,pw,ph,ps;
	
	public EnemyProjectile(int px, int py, int pw, int ph,int ps) {
		this.px = px;
		this.py = py;
		this.pw = pw;
		this.ph = ph;
		this.ps = ps;
	}
	
	public int getPx() {
		return px;
	}
	
	public int getPy() {
		return py;
	}
	
	public int getPw() {
		return pw;
	}
	
	public int getPh() {
		return ph;
	}
	
	public int getPs() {
		return ps;
	}
	
	public void shoot() {
		px -= ps;
	}
	
	public boolean collides(int x, int y, int w, int h) {
		return this.px < x + w && this.px + this.pw > x && this.py < y + h && this.py + this.ph > y;  //Boolean method used to check for collision with player and																			
	}
}
