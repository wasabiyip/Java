import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class test {
	
	public static void main(String args[]) throws FileNotFoundException{
		BufferedReader rd = new BufferedReader(new FileReader("H:\\research\\pengyu\\source1.txt"));
		try {
			String a=rd.readLine();
			String aa ="'El Francés', José	Alma gitana (1996)  <45>";
			String [] b= aa.split("\t");
			
			System.out.println(b[0]);
			System.out.println(b[1]);
			System.out.println(a);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
