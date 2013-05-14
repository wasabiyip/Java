import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


public class coi1 {

	public static void main (String args[]){
		
	try {
		BufferedReader ID = new BufferedReader(new FileReader("all.txt"));
		BufferedReader coauthorstat   = new BufferedReader(new FileReader("coauthorstat.txt"));
		//BufferedReader  coauthortable= new BufferedReader(new FileReader("coauthor.txt"));
		BufferedReader  coauthortable= new BufferedReader(new FileReader("coauthor.txt"));
		OutputStreamWriter out = new OutputStreamWriter( new FileOutputStream("coauthor_num.txt"),"UTF-8");
		//FileWriter coauthortable_w=new FileWriter("coauthor_num.txt");
		//BufferedWriter coauthortable_w_= new BufferedWriter(coauthortable_w);
		String test="";
		HashMap<String, String> hm = new HashMap();
		String [] result;
		while((test=ID.readLine())!=null){
			result = test.split("\t");
			//System.out.println(result[0]);
			//System.out.println(result[1]);
			//(key,value)
			hm.put(result[0], result[1]);
			
			
		}
		/*
		String aa="23222";
		String  a = hm.get(aa);
		
		System.out.println(a);
		*/
	    String num="";
	    String co="";
	    String l;
		while((num=coauthorstat.readLine())!=null&&(co=coauthortable.readLine())!=null)
		{
			System.out.println(num);
			num.trim();
			out.write(num.split("\t")[1]+"\t");
			result = co.split("\t");
			 
			for (int i = 1;i<result.length;i++){
				l=hm.get(result[i]);
				out.write(l+"\t");
				//System.out.println(l);
			}
			out.write("\n");
		}
		
		
		
		/*
		String test= ID.readLine();
		System.out.println(test);
		 test= coauthorstat.readLine();
		 System.out.println(test);
		 test= coauthortable.readLine();
		 System.out.println(test);
		 */
		//out.write(test);
	//	out.flush();
		out.close();
		ID.close();
		coauthorstat.close();
		coauthortable.close();
		
		
		
		
		
		
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
}
}
