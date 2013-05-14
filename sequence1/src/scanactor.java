import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class scanactor {
	public static void main(String[] args) throws IOException {
		BufferedReader rd = new BufferedReader(new FileReader(
				"H:\\research\\pengyu\\result\\actorid.txt"));
		BufferedReader rd2 = new BufferedReader(new FileReader(
				"H:\\research\\pengyu\\result\\actornessid.txt"));
		BufferedWriter fw = new BufferedWriter(new FileWriter(
				"H:\\statistic_noactor_actorness.txt"));
		BufferedWriter fw2 = new BufferedWriter(new FileWriter(
		"H:\\statistic_haveactor_actorness.txt"));
		BufferedWriter fw3 = new BufferedWriter(new FileWriter(
		"H:\\statistic.txt"));
		int[] m = new int[17771];
		int[] f = new int[17771];
		for (int i = 0; i < 17771; i++) {
			m[i] = 0;
			f[i] = 0;
		}
		String line = "";
		int temp;
		while (null != (line = rd.readLine())) {
			temp = Integer.parseInt(line.split("\t")[0]);
			m[temp]++;
		}
		while (null != (line = rd2.readLine())) {
			temp = Integer.parseInt(line.split("\t")[0]);
			f[temp]++;
		}
		int min_actor=1;
		int min_actor_id=0;
		int min_actorness=1;
		int min_actorness_id=0;
		for(int i=1;i<17771;i++){
			if(m[i]==0&&f[i]==0){
				String b="\n";
				fw.write(i+b);
			}else{
				String b="\n";
				fw2.write(i+b);
			}
			
			if((m[i]!=0)&&(m[i]<=min_actor)){
				min_actor=m[i];
				min_actor_id=i;
			}
			if((f[i]!=0)&&(f[i]<=min_actorness)){
				min_actorness=f[i];
				min_actorness_id=i;
			}			
		}
		fw3.write("Actor min number and movie id is \n"+min_actor+"\t"+min_actor_id+"\n");
		fw3.write("Actorness min number and movie id  is \n"+min_actorness+"\t"+min_actorness_id+"\n");
		
		rd.close();
		rd2.close();
		fw.close();
		fw2.close();
		fw3.close();
		System.out.println("end");
		
		

	}
}
