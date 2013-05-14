import java.util.*;
import java.io.*;

public class e {
	// data
	static String greedy = "/greedy_coi4/assign.txt";
	static String greedy_no = "/greedy_coi0/assign.txt";
	static String greedy2 = "/greedy2_coi4/assign.txt";
	static String greedy2_no = "/greedy2_coi0/assign.txt";
	static String paper_ID = "/PID.txt";
	

	// output
	static String average_different_greedy = "/e1/average_different_greedy.txt";
	static String distribution_different_greedy = "/e1/distribution_different_greedy.txt";
	static String total_different_greedy = "/e1/total_different_greedy.txt";

	static String average_different_greedy2 = "/e1/average_different_greedy2.txt";
	static String distribution_different_greedy2 = "/e1/distribution_different_greedy2.txt";
	static String total_different_greedy2 = "/e1/total_different_greedy2.txt";

	public static void main(String arg[]) {
		// different assignment
		// distribution
		// percentage
		
		  if (arg.length<=0){
		  System.out.println("Please input the parameter again!"); } 
		  else{
		  
		  greedy=arg[0]+greedy; 
		  greedy_no=arg[0]+greedy_no;
		  greedy2=arg[0]+greedy2; 
		  greedy2_no=arg[0]+greedy2_no;
		  paper_ID=arg[0]+paper_ID;
		  
		  //output average_different_greedy=arg[0]+average_different_greedy;
		  average_different_greedy=arg[0]+average_different_greedy;
		  distribution_different_greedy=arg[0]+distribution_different_greedy;
		  total_different_greedy=arg[0]+total_different_greedy;
		  
		  average_different_greedy2=arg[0]+average_different_greedy2;
		  distribution_different_greedy2=arg[0]+distribution_different_greedy2;
		  total_different_greedy2=arg[0]+total_different_greedy2;
		 

		// Set hs = new HashSet();
/*		greedy = "e1test/" + greedy;
		greedy_no = "e1test/" + greedy_no;
		greedy2 = "e1test/" + greedy2;
		greedy2_no = "e1test/" + greedy2_no;
		paper_ID="e1test/"+paper_ID;

		// output
		average_different_greedy = "e1test/" + average_different_greedy;
		distribution_different_greedy = "e1test/"
				+ distribution_different_greedy;
		total_different_greedy = "e1test/" + total_different_greedy;

		average_different_greedy2 = "e1test/" + average_different_greedy2;
		distribution_different_greedy2 = "e1test/"
				+ distribution_different_greedy2;
		total_different_greedy2 = "e1test/" + total_different_greedy2;*/

		// read the four assignments
		String weight = "";
		String[] temp;
		HashMap<String, List<String>> greedy_l = new HashMap<String, List<String>>();
		HashMap<String, List<String>> greedy_no_l = new HashMap<String, List<String>>();
		HashMap<String, List<String>> greedy2_l = new HashMap<String, List<String>>();
		HashMap<String, List<String>> greedy2_no_l = new HashMap<String, List<String>>();
		List<String> templist = new ArrayList<String>();

		// ArrayList<Integer> greedy_no_l = new ArrayList<Integer>();
		// ArrayList<Integer> greedy2_l = new ArrayList<Integer>();
		// ArrayList<Integer> greedy2_no_l = new ArrayList<Integer>();

		BufferedReader gl;
		try {
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

			// ///
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

			// //
			BufferedReader gl2 = new BufferedReader(new FileReader(greedy2));
			while ((weight = gl2.readLine()) != null) {
				temp = weight.split(" ");
				if (greedy2_l.containsKey(temp[0])) {
					templist = greedy2_l.get(temp[0]);
					templist.add(temp[1]);
					greedy2_l.remove(temp[0]);
					greedy2_l.put(temp[0], templist);

				} else {
					templist = new ArrayList<String>();
					templist.add(temp[1]);
					greedy2_l.put(temp[0], templist);

				}

			}
			gl2.close();

			// ////
			BufferedReader g2_no_l = new BufferedReader(new FileReader(
					greedy2_no));
			while ((weight = g2_no_l.readLine()) != null) {
				temp = weight.split(" ");
				if (greedy2_no_l.containsKey(temp[0])) {
					templist = greedy2_no_l.get(temp[0]);
					templist.add(temp[1]);
					greedy2_no_l.remove(temp[0]);
					greedy2_no_l.put(temp[0], templist);

				} else {
					templist = new ArrayList<String>();
					templist.add(temp[1]);
					greedy2_no_l.put(temp[0], templist);

				}

			}
			g2_no_l.close();
			// /////////////
			// read paper_ID
			BufferedReader paper_ID_file = new BufferedReader(new FileReader(
					paper_ID));
			String temp2 = "";
			ArrayList<String> PI = new ArrayList<String>();
			while ((temp2 = paper_ID_file.readLine()) != null) {
				PI.add(temp2.trim());
			}
			paper_ID_file.close();

			// ////greedy0&greedy_no greedy2&greedy2_no
			for (int m = 0; m < PI.size(); m++) {
				greedy_l.get((PI).get(m)).retainAll(greedy_no_l.get(PI.get(m)));
				greedy2_l.get((PI).get(m)).retainAll(
						greedy2_no_l.get(PI.get(m)));
			}
			// size array a[],b[]
			int[] a = new int[PI.size()];
			int[] b = new int[PI.size()];
			for (int m = 0; m < PI.size(); m++) {
				a[m] = greedy_l.get(PI.get(m)).size();
				b[m] = greedy2_l.get(PI.get(m)).size();
			}

			HashMap<Integer, Integer> count_greedy = new HashMap<Integer, Integer>();
			HashMap<Integer, Integer> count_greedy2 = new HashMap<Integer, Integer>();
			for (int m = 0; m < PI.size(); m++) {
				if (count_greedy.containsKey(a[m])) {
					int l = count_greedy.get(a[m]);
					l++;
					count_greedy.put(a[m], l);
				} else {
					count_greedy.put((a[m]), 1);
				}
				if (count_greedy2.containsKey(b[m])) {
					int l = count_greedy2.get(b[m]);
					l++;
					count_greedy2.put(b[m], l);
				} else {
					count_greedy2.put((b[m]), 1);
				}
			}

			Iterator iter;
			int n;
			// print distribution
			Integer total=0;
			int average=0;
			iter = count_greedy.entrySet().iterator();
			BufferedWriter g = new BufferedWriter(new FileWriter(
					distribution_different_greedy));
			BufferedWriter g_average = new BufferedWriter(new FileWriter(
					average_different_greedy));
			BufferedWriter g_total = new BufferedWriter(new FileWriter(
					total_different_greedy));
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				Object key = entry.getKey();
				Object val = entry.getValue();
				if (Integer.parseInt(key.toString())>0)
					total=total+Integer.parseInt(val.toString());
				average=average+Integer.parseInt(key.toString())*Integer.parseInt(val.toString());
				g.write(key.toString() + " " + val.toString() + "\n");
			}
			Double ll= (double)average/PI.size();
			g_average.write(ll.toString());
			g_average.close();
			
			g_total.write(total.toString());
			g_total.close();			
			g.close();


			total=0;
			average=0;
			iter = count_greedy2.entrySet().iterator();
			BufferedWriter g2 = new BufferedWriter(new FileWriter(
					distribution_different_greedy2));
			BufferedWriter g2_average = new BufferedWriter(new FileWriter(
					average_different_greedy2));
			BufferedWriter g2_total = new BufferedWriter(new FileWriter(
					total_different_greedy2));			
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				Object key = entry.getKey();
				Object val = entry.getValue();
				if (Integer.parseInt(key.toString())>0)
					total=total+Integer.parseInt(val.toString());
				average=average+Integer.parseInt(key.toString())*Integer.parseInt(val.toString());
				g2.write(key.toString() + " " + val.toString() + "\n");
			}
						
			ll= (double)average/PI.size();
			g2_average.write(ll.toString());
			g2_average.close();
			
			g2_total.write(total.toString());
			g2_total.close();	
			
			g2.close();
			
			
			
			// greedy_l
			// greedy_no_l
			// greedy2_l
			// greedy2_no_l

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
