import java.util.*;
import java.io.*;

public class e3 {
	// data
    static String[] G = new String [2];	

	static String greedy="";
	static String greedy_no="";
	static String paper_ID = "/PID.txt";	
	// output
	static String average_different_greedy = "/e3/average_different_greedy.txt";
	

	public static void main(String arg[]) {
		// different assignment
		// distribution
		// percentage
		G[0] = "/greedy";		
		G[1]="/greedy2";
		
		  if (arg.length<=0){
		  System.out.println("Please input the parameter again!"); 
		  } 
		  else{
		  
		  paper_ID=arg[0]+paper_ID;
		  average_different_greedy=arg[0]+average_different_greedy;
		  
		try {
		  BufferedWriter g_average = new BufferedWriter(new FileWriter(
					average_different_greedy));
		// read paper_ID
			BufferedReader paper_ID_file = new BufferedReader(new FileReader(
					paper_ID));
			String temp2 = "";
			ArrayList<String> PI = new ArrayList<String>();
			while ((temp2 = paper_ID_file.readLine()) != null) {
				PI.add(temp2.trim());
			}
			paper_ID_file.close();

			
	//		
		  for(Integer x=0;x<2;x++)
		  {
			  for(Integer y=1;y<6;y++)
			  {
				  greedy=arg[0]+G[x]+"_coi"+y.toString()+"/assign.txt";
				  greedy_no=arg[0]+G[x]+"_coi0/assign.txt";
				  
				String weight = "";
				String[] temp;
				HashMap<String, List<String>> greedy_l = new HashMap<String, List<String>>();
				HashMap<String, List<String>> greedy_no_l = new HashMap<String, List<String>>();
				List<String> templist = new ArrayList<String>();

				// ArrayList<Integer> greedy_no_l = new ArrayList<Integer>();
				// ArrayList<Integer> greedy2_l = new ArrayList<Integer>();
				// ArrayList<Integer> greedy2_no_l = new ArrayList<Integer>();
//read assign ?
				BufferedReader gl;
			
					gl = new BufferedReader(new FileReader(greedy));
					while ((weight = gl.readLine()) != null) {
						temp = weight.split(" ");
						if (greedy_l.containsKey(temp[0])) {
							templist = greedy_l.get(temp[0]);
							templist.add(temp[1]);
							greedy_l.remove(temp[0]);
							greedy_l.put(temp[0], templist);

						} else {
							templist = new ArrayList<String>();
							templist.add(temp[1]);
							greedy_l.put(temp[0], templist);
						}

					}

					gl.close();

// ///read assign coi0
					BufferedReader g_no_l = new BufferedReader(
							new FileReader(greedy_no));
					while ((weight = g_no_l.readLine()) != null) {
						temp = weight.split(" ");
						if (greedy_no_l.containsKey(temp[0])) {
							templist = greedy_no_l.get(temp[0]);
							templist.add(temp[1]);
							greedy_no_l.remove(temp[0]);
							greedy_no_l.put(temp[0], templist);

						} else {
							templist = new ArrayList<String>();
							templist.add(temp[1]);
							greedy_no_l.put(temp[0], templist);

						}

					}
					g_no_l.close();
					
					
					// ////greedy0&greedy_no greedy2&greedy2_no
					for (int m = 0; m < PI.size(); m++) {
						
						greedy_l.get((PI).get(m)).removeAll(greedy_no_l.get(PI.get(m)));
						
					}
					
					// size array a[],b[]
					int[] a = new int[PI.size()];
					for (int m = 0; m < PI.size(); m++) {
						a[m] = greedy_l.get(PI.get(m)).size();
					}

					HashMap<Integer, Integer> count_greedy = new HashMap<Integer, Integer>();
					for (int m = 0; m < PI.size(); m++) {
						if (count_greedy.containsKey(a[m])) {
							int l = count_greedy.get(a[m]);
							l++;
							count_greedy.put(a[m], l);
						} else {
							count_greedy.put((a[m]), 1);
						}
						
					}

					Iterator iter;
					int n;
					// print distribution
					Integer total=0;
					int average=0;
					iter = count_greedy.entrySet().iterator();					
					
					
					while (iter.hasNext()) {
						Map.Entry entry = (Map.Entry) iter.next();
						Object key = entry.getKey();
						Object val = entry.getValue();
						if (Integer.parseInt(key.toString())>0)
							total=total+Integer.parseInt(val.toString());
						average=average+Integer.parseInt(key.toString())*Integer.parseInt(val.toString());
						
					}
					Double ll= (double)average/PI.size();
					g_average.write(ll.toString()+"\t");
					
			  }
		  }
			  
		g_average.close();


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 }
	}
}

