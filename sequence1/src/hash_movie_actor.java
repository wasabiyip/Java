import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class hash_movie_actor {
	public static void main(String []args) throws IOException{
	BufferedReader rd=new BufferedReader (new FileReader("H:\\unique_movie_actor.txt"));
	BufferedWriter fw=new BufferedWriter (new FileWriter("H:\\n_unique_movie_actor.txt"));
	HashMap<String,Integer>map_name=new HashMap <String,Integer>();
	HashMap<String,Integer>map_director=new HashMap <String,Integer>();
	HashMap<String,Integer>map_actor=new HashMap <String,Integer>();
	
	String line="";
	int i=1;
	while(null!=(line=rd.readLine())){
		map_name.put(line.split("\t")[1], i);
		map_director.put(line.split("\t")[2],i+7653);
		map_actor.put(line.split("\t")[3],i+7653*2 );
		i++;
	}
	rd.close();
	rd=new BufferedReader (new FileReader("H:\\unique_movie_actor.txt"));
	String output="";
	while(null!=(line=rd.readLine())){
		output = line.split("\t")[0]+"\t"+map_name.get(line.split("\t")[1])+"\t"+map_director.get(line.split("\t")[2])+"\t"+map_actor.get(line.split("\t")[3])+"\n";
		fw.write(output);
	}
	
	rd.close();
	fw.close();
	
	System.out.println("end");
}

}
