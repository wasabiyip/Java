import java.util.*;
import java.io.*;

public class e1 {
	// data
    static String[] G = new String [2];	

	static String greedy="";
	static String greedy_no="";
	static String paper_ID = "/PID.txt";
	static String reviewer_ID="/RID.txt";
	static String reviewer_paper = "";
	// output
	static String average_different_greedy = "";
	
	public static void main(String arg[]) {
		// different assignment
		// distribution
		// percentage
		G[0] = "/greedy";		
		G[1]="/greedy2";
/*		
		  if (arg.length<=0){
		  System.out.println("Please input the parameter again!"); 
		  } 
		  else{
		 */
		String aaa="Total/8";
		  paper_ID=aaa+paper_ID;
		  reviewer_ID=aaa+reviewer_ID;
		  
		  
		try {
		  
		// read paper_ID
			BufferedReader paper_ID_file = new BufferedReader(new FileReader(
					paper_ID));
			String temp2 = "";
			ArrayList<String> PI = new ArrayList<String>();
			while ((temp2 = paper_ID_file.readLine()) != null) {
				PI.add(temp2.trim());
			}
			paper_ID_file.close();

			// read reviewer_ID
			BufferedReader reviewer_ID_file = new BufferedReader(
					new FileReader(reviewer_ID));
			String temp3 = "";
			ArrayList<Integer> RI = new ArrayList<Integer>();
			while ((temp3 = reviewer_ID_file.readLine()) != null) {
				RI.add(Integer.parseInt(temp3.trim()));
			}
			
			reviewer_ID_file.close();
			
			
			
			
	//		
		  for(Integer x=0;x<2;x++)
		  {
			  average_different_greedy=aaa+"/e1/average_assign_coi"+x.toString();
			  BufferedWriter g_average = new BufferedWriter(new FileWriter(
						average_different_greedy));
			  

			  for(Integer y=1;y<6;y++)
			  {
				  //greedy=aaa+G[x]+"_coi"+y.toString()+"/assign.txt";
				  greedy=aaa+G[x]+"_coi0/assign.txt";
				  reviewer_paper=aaa+"/"+y.toString()+"/PRC.txt";
				 // System.out.println(reviewer_paper);
				  
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
						System.out.print("weight");

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
					
					BufferedReader reviewer_paper_file = new BufferedReader(
							new FileReader(reviewer_paper));
					//HashMap<List<Integer>> aList = new ArrayList<List<Integer>>();
					String weight1 = "";
					String[] temp1;
/*		List<Integer> COI =new ArrayList<Integer>();
		for(int m=0;m<reviewer_number;m++){
			COI.add(0);
		}
		int coi_t=0;*/
					int count=0;
					while ((weight1 = reviewer_paper_file.readLine()) != null) {
						// System.out.println(weight);
						temp1 = weight1.split(" ");
						List<String> aListData = new ArrayList<String>();
						for (int i = 0; i < temp1.length; i++) {
							if (Integer.parseInt(new String(temp1[i]))==-1)
								{
								aListData.add(RI.get(i).toString());
								//System.out.println(RI.get(i).toString());
								}	
						}
						//aList.add(aListData);
						greedy_no_l.put(PI.get(count).toString(), aListData);
						count++;
					}
/*// ///read assign coi0
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
*/					
					
					// ////greedy0&greedy_no greedy2&greedy2_no
					for (int m = 0; m < PI.size(); m++) {
					/*	for (int l =0; l<greedy_l.get((PI).get(m)).size();l++){
							System.out.print(greedy_l.get(PI.get(m)).get(l));
							System.out.print("\t");
						}
						System.out.print("\n");
						
						for (int l =0; l<greedy_no_l.get((PI).get(m)).size();l++){
							System.out.print(greedy_no_l.get(PI.get(m)).get(l));
							System.out.print("\t");
						}
						System.out.print("\n");
				*/		
						if (greedy_l==null)
						System.out.print("greedy_l");
						if(greedy_no_l==null)
							System.out.print("greedy_no_l");
						if(PI==null)
							System.out.print("PI");
						greedy_l.get((PI).get(m)).retainAll(greedy_no_l.get(PI.get(m)));
						//remove the common elements
						
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
			  g_average.close();
		  }
			  
		


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 }
	}
//}

