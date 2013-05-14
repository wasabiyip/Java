import java.util.*;
import java.io.*;

public class e4 {
	// input assignment
	static String greedy = "/greedy_coi5/assign.txt";
	static String greedy_no = "/greedy_coi0/assign.txt";
	static String greedy2 = "/greedy2_coi5/assign.txt";
	static String greedy2_no = "/greedy2_coi0/assign.txt";
	
	static String paper_topic = "/PT1.txt";// matrix paper topic
	static String topic_expert = "topic/";//////////////////////////////////////////
	static String paper_ID = "/PID.txt";
	// output
	static String greedy_e2 = "/e4/greedy.txt";
	static String greedy_no_e2 = "/e4/greedy_no.txt";
	static String greedy2_e2 = "/e4/greedy2.txt";
	static String greedy2_no_e2 = "/e4/greedy2_no.txt";

	public static void main(String arg[]) {
		// generate paper expert list
		// read pt matrix and topic-expert
		// check the assignment
		// get the distribution
			
		if (arg.length <= 0) {
			System.out.println("Please input the parameter again!");
		} else {
			paper_ID=arg[0]+paper_ID;
			paper_topic = arg[0] + paper_topic;
			greedy = arg[0] + greedy;
			greedy_no = arg[0] + greedy_no;
			greedy2 = arg[0] + greedy2;
			greedy2_no = arg[0] + greedy2_no;

			// output
			greedy_e2 = arg[0] + greedy_e2;
			greedy_no_e2 = arg[0] + greedy_no_e2;
			greedy2_e2 = arg[0] + greedy2_e2;
			greedy2_no_e2 = arg[0] + greedy2_no_e2;
			
/*			paper_ID="e2test/"+paper_ID;
			paper_topic = "e2test/" + paper_topic;
			greedy = "e2test/" + greedy;
			greedy_no = "e2test/" + greedy_no;
			greedy2 = "e2test/" + greedy2;
			greedy2_no = "e2test/" + greedy2_no;

			// output
			greedy_e2 = "e2test/" + greedy_e2;
			greedy_no_e2 = "e2test/" + greedy_no_e2;
			greedy2_e2 = "e2test/" + greedy2_e2;
			greedy2_no_e2 = "e2test/" + greedy2_no_e2;*/

			try {

				String weight = "";
				String[] temp;
				int size=0;

// read the four assignments
				HashMap<String, List<String>> greedy_l = new HashMap<String, List<String>>();
				HashMap<String, List<String>> greedy_no_l = new HashMap<String, List<String>>();
				HashMap<String, List<String>> greedy2_l = new HashMap<String, List<String>>();
				HashMap<String, List<String>> greedy2_no_l = new HashMap<String, List<String>>();
				List<String> templist = new ArrayList<String>();

				// ArrayList<Integer> greedy_no_l = new ArrayList<Integer>();
				// ArrayList<Integer> greedy2_l = new ArrayList<Integer>();
				// ArrayList<Integer> greedy2_no_l = new ArrayList<Integer>();

				BufferedReader gl = new BufferedReader(new FileReader(greedy));
				while ((weight = gl.readLine()) != null) {
					temp = weight.split(" ");
					if (greedy_l.containsKey(temp[0])) {
						templist=greedy_l.get(temp[0]);
						templist.add(temp[1]);
			            greedy_l.remove(temp[0]);
			            greedy_l.put(temp[0], templist);					

					} else {
						templist=new ArrayList<String>();
						templist.add(temp[1]);
						greedy_l.put(temp[0], templist);
					}

				}
			//	greedy_l.get(key)
				gl.close();

				// ///

				BufferedReader g_no_l = new BufferedReader(new FileReader(
						greedy_no));
				while ((weight = g_no_l.readLine()) != null) {
					temp = weight.split(" ");
					if (greedy_no_l.containsKey(temp[0])) {
						templist=greedy_no_l.get(temp[0]);
						templist.add(temp[1]);
						greedy_no_l.remove(temp[0]);
						greedy_no_l.put(temp[0], templist);

					} else {
						templist=new ArrayList<String>();
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
						templist=greedy2_l.get(temp[0]);
						templist.add(temp[1]);
						greedy2_l.remove(temp[0]);
						greedy2_l.put(temp[0], templist);

					} else {
						templist=new ArrayList<String>();
						templist.add(temp[1]);
						greedy2_l.put(temp[0], templist);

					}

				}
				gl2.close();

				//////
				
				BufferedReader g2_no_l = new BufferedReader(new FileReader(
						greedy2_no));
				while ((weight = g2_no_l.readLine()) != null) {
					temp = weight.split(" ");
					if (greedy2_no_l.containsKey(temp[0])) {
						templist=greedy2_no_l.get(temp[0]);
						templist.add(temp[1]);
						greedy2_no_l.remove(temp[0]);
						greedy2_no_l.put(temp[0], templist);

					} else {
						templist=new ArrayList<String>();
						templist.add(temp[1]);
						greedy2_no_l.put(temp[0], templist);

					}

				}
				g2_no_l.close();
///////////////

				
// read paper*topic 
				ArrayList<Integer> ca = new ArrayList<Integer>();
				ArrayList<Integer> ca_sort = new ArrayList<Integer>();

				int paper = 0, sum = 0;
				BufferedReader paper_topic_file;
				paper_topic_file = new BufferedReader(new FileReader(
						paper_topic));
				List<List<Integer>> aList_pt = new ArrayList<List<Integer>>();
				while ((weight = paper_topic_file.readLine()) != null) {
					temp = weight.split(" ");
					List<Integer> aListData_pt = new ArrayList<Integer>();
					sum = 0;
					for (int i = 0; i < temp.length; i++) {
						paper = Integer.parseInt(new String(temp[i]));
						sum += paper;
						aListData_pt.add(paper);
					}
					// System.out.println(sum);
					ca.add(sum);
					ca_sort.add(sum);
					aList_pt.add(aListData_pt);
				}
				paper_topic_file.close();

// read paper_ID
BufferedReader paper_ID_file = new BufferedReader(new FileReader(
		paper_ID));
String temp2 = "";
ArrayList<String> PI = new ArrayList<String>();
while ((temp2 = paper_ID_file.readLine()) != null) {
	PI.add(temp2.trim());
}
paper_ID_file.close();				

				
templist=greedy_l.get(PI.get(0));
size=templist.size();
// read topic file
// generate the paper expert file
// check and count
				String tem;				
				HashMap<String, Integer> greedy_l_c = new HashMap<String, Integer>();
				HashMap<String, Integer> greedy_no_l_c = new HashMap<String, Integer>();
				HashMap<String, Integer> greedy2_l_c = new HashMap<String, Integer>();
				HashMap<String, Integer> greedy2_no_l_c = new HashMap<String, Integer>();
				
			//	System.out.println(aList_pt.size());
				for (int m = 0; m < aList_pt.size(); m++) { // every paper
					List<String> paperexpert = new ArrayList<String>();
					for (int l = 0; l < aList_pt.get(m).size(); l++) {// every topic
						if (aList_pt.get(m).get(l) == 1) {
							BufferedReader topic_file = new BufferedReader(
									new FileReader(topic_expert	+ Integer.toString(l+1)));
							while ((tem = topic_file.readLine()) != null) {
								paperexpert.add(tem);

							}
							topic_file.close();
						}
					}
// check the assignment greedy
					int tt=0;
					//System.out.println(PI.get(m));
					
//System.out.println(PI.get(m));
//System.out.println(templist.get(0));
					templist=greedy_l.get(PI.get(m));
					for(int k=0;k<templist.size();k++){
						if(paperexpert.contains(templist.get(k))){
							if(greedy_l_c.containsKey(PI.get(m))){
								tt=greedy_l_c.get(PI.get(m).toString());
								greedy_l_c.put(PI.get(m).toString(), tt+1);								
							}
							else{							
								
								greedy_l_c.put(PI.get(m).toString(), 1);
							}							
						}
						else if(!greedy_l_c.containsKey(PI.get(m))){
							greedy_l_c.put(PI.get(m).toString(), 0);
						}
					}
				
// check the assignment greedy_no					
					templist=greedy_no_l.get(PI.get(m));
					for(int k=0;k<templist.size();k++){
						if(paperexpert.contains(templist.get(k))){
							if(greedy_no_l_c.containsKey(PI.get(m))){
								tt=greedy_no_l_c.get(PI.get(m).toString());
								greedy_no_l_c.put(PI.get(m).toString(), tt+1);								
							}
							else{								
								greedy_no_l_c.put(PI.get(m).toString(), 1);
							}							
						}
						else if(!greedy_no_l_c.containsKey(PI.get(m))){
							greedy_no_l_c.put(PI.get(m).toString(), 0);
						}
					}
					
// check the assignment greedy2					
					templist=greedy2_l.get(PI.get(m));
					for(int k=0;k<templist.size();k++){
						if(paperexpert.contains(templist.get(k))){
							if(greedy2_l_c.containsKey(PI.get(m))){
								tt=greedy2_l_c.get(PI.get(m).toString());
								greedy2_l_c.put(PI.get(m).toString(), tt+1);								
							}
							else{								
								greedy2_l_c.put(PI.get(m).toString(), 1);
							}
							
						}
						else if(!greedy2_l_c.containsKey(PI.get(m))){
							greedy2_l_c.put(PI.get(m).toString(), 0);
						}
					}
// check the assignment greedy2_no	
					templist=greedy2_no_l.get(PI.get(m));
					for(int k=0;k<templist.size();k++){
						if(paperexpert.contains(templist.get(k))){
							if(greedy2_no_l_c.containsKey(PI.get(m))){
								tt=greedy2_no_l_c.get(PI.get(m).toString());
								greedy2_no_l_c.put(PI.get(m).toString(), tt+1);								
							}
							else{								
								greedy2_no_l_c.put(PI.get(m).toString(), 1);
							}
							
						}
						else if(!greedy2_no_l_c.containsKey(PI.get(m))){
							greedy2_no_l_c.put(PI.get(m).toString(), 0);
						}
					}			
					
					
				}

				
				//tranversal the four hasmap and print the result
				/*
static String greedy_e2 = "/e2/greedy.txt";
static String greedy_no_e2 = "/e2/greedy_no.txt";
static String greedy2_e2 = "/e2/greedy2.txt";
static String greedy2_no_e2 = "/e2/greedy2_no.txt";

HashMap<String, Integer> greedy_l_c = new HashMap<String, Integer>();
HashMap<String, Integer> greedy_no_l_c = new HashMap<String, Integer>();
HashMap<String, Integer> greedy2_l_c = new HashMap<String, Integer>();
HashMap<String, Integer> greedy2_no_l_c = new HashMap<String, Integer>();
				 */
				//count
				
				Iterator iter;
/////				
				iter = greedy_l_c.entrySet().iterator();
				//System.out.println(greedy_l_c.entrySet().size());
				HashMap<Integer, Integer> bt_g=new HashMap<Integer,Integer>();
				int n=0;
				while (iter.hasNext()) {
				    Map.Entry entry = (Map.Entry) iter.next();
				 //   Object key = entry.getKey();
				    Object val = entry.getValue();
				    if(bt_g.containsKey(val)){
				    	n=bt_g.get(val);
				    	n++;
				    	bt_g.remove((Integer)val);
				    	bt_g.put((Integer) val, n);
				    	
				    }
				    else{
				    	bt_g.put((Integer) val, 1);
				    }
				} 
				
/////
				iter = greedy_no_l_c.entrySet().iterator();
				HashMap<Integer, Integer> bt_gno=new HashMap<Integer,Integer>();
				n=0;
				while (iter.hasNext()) {
				    Map.Entry entry = (Map.Entry) iter.next();
				 //   Object key = entry.getKey();
				    Object val = entry.getValue();
				    if(bt_gno.containsKey(val)){
				    	n=bt_gno.get(val);
				    	n++;
				    	bt_gno.put((Integer) val, n);
				    	
				    }
				    else{
				    	bt_gno.put((Integer) val, 1);
				    }
				} 
/////
				iter = greedy2_l_c.entrySet().iterator();
				HashMap<Integer, Integer> bt_g2=new HashMap<Integer,Integer>();
				 n=0;
				while (iter.hasNext()) {
				    Map.Entry entry = (Map.Entry) iter.next();
				 //   Object key = entry.getKey();
				    Object val = entry.getValue();
				    if(bt_g2.containsKey(val)){
				    	n=bt_g2.get(val);
				    	n++;
				    	bt_g2.put((Integer) val, n);
				    	
				    }
				    else{
				    	bt_g2.put((Integer) val, 1);
				    }
				} 
//////
				iter = greedy2_no_l_c.entrySet().iterator();
				HashMap<Integer, Integer> bt_g2no=new HashMap<Integer,Integer>();
				n=0;
				while (iter.hasNext()) {
				    Map.Entry entry = (Map.Entry) iter.next();
				  //  Object key = entry.getKey();
				    Object val = entry.getValue();
				    if(bt_g2no.containsKey(val)){
				    	n=bt_g2no.get(val);
				    	n++;
				    	bt_g2no.put((Integer) val, n);
				    	
				    }
				    else{
				    	bt_g2no.put((Integer) val, 1);
				    }
				} 
				
				
				//output file
				/*
				static String greedy_e2 = "/e2/greedy.txt";
				static String greedy_no_e2 = "/e2/greedy_no.txt";
				static String greedy2_e2 = "/e2/greedy2.txt";
				static String greedy2_no_e2 = "/e2/greedy2_no.txt";*/
				BufferedWriter g = new BufferedWriter(new FileWriter(
						greedy_e2));
				iter = bt_g.entrySet().iterator();
				int add=0;
				while (iter.hasNext()) {
				    Map.Entry entry = (Map.Entry) iter.next();
				    Object key = entry.getKey();
				    Object val = entry.getValue();
				    add+=(size-Integer.parseInt(key.toString()))*Integer.parseInt(val.toString());
				   // g.write(key.toString()+" "+val.toString()+"\n");
				} 
				Double t=100*(double)add/(size*PI.size());
				g.write(t.toString());
				g.close();
				
				//*********************
/*				BufferedWriter gno = new BufferedWriter(new FileWriter(
						greedy_no_e2));	
				iter = bt_gno.entrySet().iterator();
				while (iter.hasNext()) {
				    Map.Entry entry = (Map.Entry) iter.next();
				    Object key = entry.getKey();
				    Object val = entry.getValue();
				    gno.write(key.toString()+" "+val.toString()+"\n");
				} 
				gno.close();*/
				
				//*********************
				add=0;
				BufferedWriter g2 = new BufferedWriter(new FileWriter(
						greedy2_e2));	
				iter = bt_g2.entrySet().iterator();
				while (iter.hasNext()) {
				    Map.Entry entry = (Map.Entry) iter.next();
				    Object key = entry.getKey();
				    Object val = entry.getValue();
				    add+=(size-Integer.parseInt(key.toString()))*Integer.parseInt(val.toString());
				 //   g2.write(key.toString()+" "+val.toString()+"\n");
				} 
				t=100*(double)add/(size*PI.size());
				g2.write(t.toString());
				g2.close();
				//*********************
/*				BufferedWriter g2no = new BufferedWriter(new FileWriter(
						greedy2_no_e2));
				iter = bt_g2no.entrySet().iterator();
				while (iter.hasNext()) {
				    Map.Entry entry = (Map.Entry) iter.next();
				    Object key = entry.getKey();
				    Object val = entry.getValue();
				    g2no.write(key.toString()+" "+val.toString()+"\n");
				} 
				g2no.close();
				
				
				
				*/

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
