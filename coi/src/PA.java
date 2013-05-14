import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.io.*;
import java.util.HashMap;
public class PA {
 public static void main(String arg[]){
	 try {
		BufferedReader right = new BufferedReader(new FileReader("all.txt"));
		BufferedReader wrong = new BufferedReader(new FileReader("wrongname_id.txt"));
		BufferedReader author = new BufferedReader(new FileReader("paperauthor"));
		
		BufferedWriter paperauthor = new BufferedWriter(new FileWriter("paperauthor_m"));
		BufferedWriter authorpaper = new BufferedWriter(new FileWriter("authorpaper_m"));
		
		
		HashMap r=new HashMap();
		HashMap w= new HashMap();
		HashMap ap= new HashMap();
		String temp="";
		String [] result;
		while((temp=right.readLine())!=null){
			result=temp.split("\t");
			r.put(result[0], result[1]);
		}
		while((temp=wrong.readLine())!=null){
			result=temp.split("\t");
			r.put(result[0], result[1]);
		}
		int num=1;
		ArrayList<String> a;
		while((temp=author.readLine())!=null){
			result=temp.split("\t");
			paperauthor.write(num+"\t");
			for (int i=0;i<result.length;i++){
				//System.out.println(result[i]+"\t");
				//String ID;
				if(r.get(result[i])!=null){
					String te= (String) r.get(result[i]);
					paperauthor.write(te+"\t");
					
					if(ap.get(te)!=null){
						a= (ArrayList<String>) ap.get(te);
						a.add(Integer.toString(num));
					}
					
					else{
						ArrayList<String> newau =  new ArrayList<String>();
						newau.add(te);
						newau.add(Integer.toString(num));
						ap.put(te, newau);
						
						
					}
					//System.out.println(result[i]);
				}
				else if(w.get(result[i])!=null){
					paperauthor.write(r.get(result[i])+"\t");
					System.out.println(result[i]);
				
				}/*
				else {
					System.out.println(result[i]);
				}
			
			
			System.out.println("paper "+num+"\t");
			System.out.println(result.length);
			for (int i=0;i<result.length;i++){
				System.out.println(result[i]+"\t");
			}*/
				
			
			}
			paperauthor.write("\n");
			num++;
		}
		
 
		Collection c = ap.values();
 
 
		Iterator itr = c.iterator();
		while(itr.hasNext())
		{
			ArrayList l=(ArrayList) itr.next();
			for (int k=0;k<l.size();k++)
				authorpaper.write(l.get(k).toString()+"\t");
			authorpaper.write("\n");
			// System.out.println(l.);
		}
		
		//count ap
		
		
		right.close();
		wrong.close();
		author.close();
		paperauthor.close();
		authorpaper.close();
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	 
	 
	 
	 
	 
 }
	
	
	
	
	
}
