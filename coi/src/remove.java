import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.io.*;
public class remove {

	public static void main (String args[]){
		try{
		BufferedReader stop = new BufferedReader(new FileReader("stopword") );
		BufferedReader f = new BufferedReader(new FileReader("test"));
		BufferedWriter w =new BufferedWriter(new FileWriter("output")); 
		String a = "";
		Set set = new HashSet();
		while((a=stop.readLine())!=null){
			set.add(a);			
		}
		String[] result;
		while((a=f.readLine())!=null){
			result = a.split(" ");
			for (int i =0; i<result.length;i++){
				// i can use the regular expression here
				result[i]=result[i].replace(".", "");
				result[i]=result[i].replace(",", "");
				result[i]=result[i].replace(":", "");
				result[i]=result[i].replace("!", "");
				result[i]=result[i].replace("\n", "");
				System.out.println(result[i]);
				if(!set.contains(result[i]))
					w.write(result[i]+" ");
				
			}
			//set.contains(o);
		}
		stop.close();
		f.close();
		w.close();
		
		
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
