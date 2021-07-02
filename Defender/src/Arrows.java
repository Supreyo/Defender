import java.util.ArrayList;

public class Arrows{
	int a,b,c,d;
	
	public Arrows(ArrayList<Integer> a) {
		this.a = a.get(0);
		this.b = a.get(1);
		this.c = a.get(2);
		this.d = a.get(3);
	}
			
	public int getA() {
		return a;
	}
	
	public int getB() {
		return b;
	}
	
	public int getC() {
		return c;
	}
	
	public int getD() {
		return d;
	}
}
