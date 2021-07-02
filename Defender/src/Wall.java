public class Wall {
	int x,y,w,h,hp;
	
	public Wall(int x, int y, int w, int h, int hp) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.hp = hp;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getW() {
		return w;
	}
	
	public int getH() {
		return h;
	}
	
	public int getHp() {
		return hp;
	}
	
	public void setHp(int Hp) {
		hp = Hp;
	}
	
	public int HpLost(int dmg) {
		hp -= dmg;
		return hp;
	}
}
