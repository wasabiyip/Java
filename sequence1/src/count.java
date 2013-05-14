import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class count {
	public static void main(String args[]) throws IOException {
		BufferedReader rd = new BufferedReader(new FileReader(
				"H:\\research\\pengyu\\result\\final_movie.txt"));
		//BufferedReader rd2 = new BufferedReader(new FileReader(
				//"H:\\research\\pengyu\\producers.list"));
		BufferedWriter fw = new BufferedWriter(new FileWriter("H:\\count.txt"));
		HashMap<String, String> map_di = new HashMap<String, String>();
		HashMap<String, String> map_pro = new HashMap<String, String>();
		String line = "";
		int director = 0;
		int producer = 0;
		while (null != (line = rd.readLine())) {
			map_di.put(line.split("\t")[3], "");
			map_pro.put(line.split("\t")[4], "");

		}
		
		
		fw.write("director:"+map_di.size()+"\n");
		fw.write("producer:"+map_pro.size()+"\n");
		
		rd.close();
		//rd2.close();
		fw.close();
		
		
		
	}
}
