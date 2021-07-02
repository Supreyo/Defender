import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
	ArrayList<Integer> stats;
	
	public Reader(){
		
	}
	
	public ArrayList<Integer> reader(String path) throws IOException{
		Scanner inFile = new Scanner(new BufferedReader(new FileReader(path)));
		this.stats = new ArrayList<Integer>();
		
		while (inFile.hasNextInt()) {
			stats.add(inFile.nextInt());
		}
		
		return stats;
	}
}