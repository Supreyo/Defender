import java.io.IOException;
import java.io.PrintWriter;

public class Writer {	
	public Writer() {
		
	}

	public void write(int n1, int n2, int n3, int n4, String path) throws IOException{
		PrintWriter writer = new PrintWriter(path);
		writer.println(n1);
		writer.println(n2);
		writer.println(n3);
		writer.println(n4);
		writer.close();
	}
}

//Bow Damage Level
//Bow Damage
//Arrow Speed Level
//Arrow Speed
//Arrow Frequency level
//Arrow Frequency
//
//
//
//
//
//