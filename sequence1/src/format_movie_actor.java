import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class format_movie_actor {
	public static void main(String [] args) throws IOException{
		BufferedReader rd=new BufferedReader (new FileReader("H:\\mocie_actor.txt"));
		BufferedWriter fw=new BufferedWriter (new FileWriter("H:\\unique_movie_actor.txt"));
		String line="";
		//unique_movie_actor.txt:(movie_id, name, director, actor)
		boolean []a=new boolean [17771];
		for(int i=0;i<17770;i++){
			a[i]=false;
		}
		int temp;
		while(null!=(line=rd.readLine())){
			temp=Integer.parseInt(line.split("\t")[0]);
			if(a[temp]==false){
				a[temp]=true;
				fw.write(line+"\n");
			}
			
		}
		rd.close();
		fw.close();
		System.out.println("end");
		
		
	}

}
