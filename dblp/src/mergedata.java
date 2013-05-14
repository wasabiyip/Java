import java.io.*;
public class mergedata {
	public static void main (String args[]) throws IOException{
		
		
			BufferedWriter wr = new BufferedWriter(new FileWriter("C:\\Users\\yll\\Desktop\\topics\\topics\\total.txt"));
			for (int i = 1; i <= 53; i++){
			try {
				String name="C:\\Users\\yll\\Desktop\\topics\\topics\\"+i+".txt";
				BufferedReader file=new BufferedReader(new FileReader(name));
				String line = null ;
				String temp = null ;
				
				int j =1;
				while((line=file.readLine())!=null){
					temp=line.split("\t")[0]+"\n";
					wr.write(temp);
					System.out.println(j+" "+temp);
					j++;
					
				}
				file.close();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 	
		}
			wr.close();

	}

}
