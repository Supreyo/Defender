public class Enemy {
	int x,y,w,h,s, type;
	int damage;
	boolean dead = false;
	boolean attacked = false;
	
	int attackTimer;
	int fireRate;
	int reset;
	
	float hp,tHP;
	
	public Enemy(int x, int y, int w, int h, int s, float hp, float tHP, int type, int damage, boolean dead, boolean attacked, int attackTimer, int fireRate) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h= h;
		this.s = s;
		this.hp = hp;
		this.tHP = tHP;
		this.type = type;
		this.damage = damage;
		this.dead = dead;
		this.attacked = attacked;
		
		this.attackTimer = attackTimer;
		this.fireRate = fireRate;
		this.reset = attackTimer;

	}
	
	public boolean getAttacked() {
		return attacked;
	}
	
	public void setAttacked(Boolean a) {
		attacked = a;
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
	
	public float getTHP() {
		return tHP;
	}
	
	public int getS() {
		return s;
	}
	
	public float getHp() {
		return hp;
	}
	
	public double percentHP() {
		return hp/tHP;
	}
	
	public int getType() {
		return type;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public float HpLost(int dmg) {
		hp -= dmg;
		return hp;
	}
	
	public boolean at850x() {
		if (x > 850) {
			return false; 
		}
		return true;
	}

	public boolean dead() {
		return dead;
	}
	
	public void setDead(Boolean dead) {
		this.dead = dead;
	}
	
	
	public int getAttackTimer() {
		return attackTimer;
	}
	
	public int getFireRate() {
		return fireRate;
	}
	
	public void fire(int r) {
		reset -= r;
	}
	
	public int getReset() {
		return reset;
	}
	
	public void setReset(int s) {
		reset = s;
	}
	
	public void move() {
		if (type >= 0 && type < 3) {
			if (x >= 250) {
				x -= s; 
			}
		}
		
		else if (type >= 3) {
			if (x >= 850) {
				x -= s; 
			}
		}
	}

	public boolean collides(int x, int y, int w, int h) {
		return this.x < x + w && this.x + this.w > x && this.y < y + h && this.y + this.h > y;  //Boolean method used to check for collision with player and																			
	}
}
