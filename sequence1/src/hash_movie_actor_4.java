import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class hash_movie_actor_4 {
	public static void main(String[] args) throws IOException {
		BufferedReader rd = new BufferedReader(new FileReader(
				"H:\\movieactor2.txt"));
		BufferedWriter fw = new BufferedWriter(new FileWriter(
				"H:\\movieactor_combine2.txt"));
		HashMap<String, movie_actor_4> map = new HashMap<String, movie_actor_4>();
		String line = "";
		movie_actor_4 data[] = new movie_actor_4[17771];
		movie_actor_4 temp = new movie_actor_4();
		while (null != (line = rd.readLine())) {
			temp = null;
			int index = Integer.parseInt(line.split("\t")[0]);
			// data[index]=new movie_actor_4();
			if (data[index] != null) {
				if (data[index].full == 5) {
					data[index].actor2 = line.split("\t")[5];
					data[index].full++;

				} else if (data[index].full == 6) {
					data[index].actor3 = line.split("\t")[5];
					data[index].full++;

				} else if (data[index].full == 7) {
					data[index].actor4 = line.split("\t")[5];
					data[index].full++;
				} else if (data[index].full == 8) {
					data[index].actor5 = line.split("\t")[5];
					data[index].full++;
				} else if (data[index].full == 9) {
					data[index].actor6 = line.split("\t")[5];
					data[index].full++;
				} else if (data[index].full == 10) {
					data[index].actor7 = line.split("\t")[5];
					data[index].full++;
				}

			} else {
				data[index] = new movie_actor_4();
				System.out.println(line.split("\t")[0]);
				data[index].mid = line.split("\t")[0];
				data[index].name = line.split("\t")[1];
				// no year
				data[index].producer = line.split("\t")[3];
				data[index].director = line.split("\t")[4];
				data[index].actor1 = line.split("\t")[5];
				data[index].full = 5;
			}

			/*
			 * if(map.get(line.split("\t")[0]) != null){
			 * if(map.get(line.split("\t")[0]).full==5){
			 * map.get(line.split("\t")[0]).actor1=line.split("\t")[5];
			 * map.get(line.split("\t")[0]).full++; } else
			 * if(map.get(line.split("\t")[0]).full==6){} else
			 * if(map.get(line.split("\t")[0]).full==7){} else
			 * if(map.get(line.split("\t")[0]).full==8){}
			 * 
			 * 
			 * } else{ temp.mid=line.split("\t")[0];
			 * temp.name=line.split("\t")[1]; temp.year=line.split("\t")[2];
			 * temp.producer=line.split("\t")[3]; temp.director
			 * =line.split("\t")[4]; temp.full=5;
			 * 
			 * map.put(line.split("\t")[0], temp); }
			 */
		}
		String output = "";
		for (int i = 1; i < 17771; i++) {
			if (data[i] != null) {
				output = data[i].mid + "\t" + data[i].name + "\t"
						+ data[i].producer + "\t" + data[i].director + "\t"
						+ data[i].actor1 + "\t" + data[i].actor2 + "\t"
						+ data[i].actor3 + "\t" + data[i].actor4 +"\t"
						+ data[i].actor5 + "\t" + data[i].actor6 +"\t"
						+ data[i].actor7 + "\n";
				fw.write(output);
			}

		}
		rd.close();
		fw.close();
		System.out.println("end");
	}
}
