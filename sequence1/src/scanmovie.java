import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class scanmovie {
	public static void main(String[] args) throws IOException {

		BufferedReader rd = new BufferedReader(new FileReader(
				"H:\\research\\pengyu\\result\\idnameyeardirectorproducer.txt"));
		BufferedWriter fw= new BufferedWriter(new FileWriter("H:\\final_movie.txt"));
		BufferedWriter fw2= new BufferedWriter(new FileWriter("H:\\not_exit.txt"));
		boolean [] a=new boolean [17771] ;
		for(int i=1;i<17771;i++){
			a[i]=false;
		}
		String line="";
		int temp;
		while(null!=(line=rd.readLine())){
			temp=Integer.parseInt(line.split("\t")[0]);
			if(a[temp]==false){
				a[temp]=true;
				fw.write(line+"\n");				
			}
		}
		
		for(int i=1;i<17771;i++){
			if( a[i]==false){
				String t="\n";
				fw2.write(i+t);
		}
		}
		rd.close();
		fw.close();
		fw2.close();
		System.out.println("end");
	}
}
