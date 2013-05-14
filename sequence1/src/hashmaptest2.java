import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class hashmaptest2 {
	public static void main(String[] args) throws IOException {
		BufferedReader rd = new BufferedReader(new FileReader(
				"H:\\idnameyeardirector.txt"));
		BufferedReader rd2 = new BufferedReader(new FileReader(
				"H:\\producer.txt"));
		BufferedWriter fw = new BufferedWriter(new FileWriter(
				"H:\\testtest.txt"));
		HashMap<String, String> map = new HashMap<String, String>();
		String line = "";
		String title = "";
		String titleyear = "";

		while (null != (line = rd.readLine())) {
			title = "";
			titleyear = "";

			title = line;
			titleyear = line.split("\t")[1];
			titleyear += " (";
			titleyear += line.split("\t")[2];
			titleyear += ")";

			map.put(titleyear, title);
			System.out.println(title);

		}
		int i = 0;
		while (null != (line = rd2.readLine())) {
			if (map.containsKey(line.split("\t")[1])) {
				fw.write(map.get(line.split("\t")[1]) + "\t"
						+ line.split("\t")[0] + "\n");
				System.out.println(map.get(line.split("\t")[1]) + "\t"
						+ line.split("\t")[0] + "\n");
			}
			if (i++ % 1000 == 0) {
				System.out.println(i);
			}
		}
		rd.close();
		rd2.close();
		fw.close();
		System.out.println("end");

	}
}
