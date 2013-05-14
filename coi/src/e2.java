import java.util.*;
import java.io.*;
public class e2 {
	//data
	static String greedy="/greedy_coi";
	static String greedy2="/greedy2_coi";
	
	//out put
	static String greedy_o="/e2/greedy";
	static String greedy2_o="/e2/greedy2";	
	static String greedy_total="/e2/greedytotal";	
	public static void main (String []arg){
		  if (arg.length<=0){
			  System.out.println("Please input the parameter again!"); } 
			  else{
				  greedy=arg[0]+greedy;
				  greedy2=arg[0]+greedy2;
				  greedy_o=arg[0]+greedy_o;
				  greedy2_o=arg[0]+greedy2_o;
				  greedy_total=arg[0]+greedy_total;
				  String temp="";
				  String temp2="";
				  //open output
				  try {
					BufferedWriter greedy_o_file = new BufferedWriter(new FileWriter(
							  greedy_o));
					  BufferedWriter greedy2_o_file = new BufferedWriter(new FileWriter(
							  greedy2_o));
					  BufferedWriter greedy_total_file = new BufferedWriter(new FileWriter(
							  greedy_total));
					  String t="";
					  for (Integer m=0;m<6;m++){
						  temp=greedy+m.toString()+"/coverd-total.txt";
						  
						  //open data
						  BufferedReader tf = new BufferedReader(new FileReader(
								  temp));
						  
						  t=tf.readLine();
						  Double test=Double.parseDouble(t)*100;
						  greedy_o_file.write(test.toString()+"\t");
						  greedy_total_file.write(test.toString()+"\t");
						  
						  
						  tf.close();
						  
					  }
					  for (Integer m=0;m<6;m++){
						  
						  temp2=greedy2+m.toString()+"/coverd-total.txt";
						  //open data
						  
						  BufferedReader tf2 = new BufferedReader(new FileReader(
								  temp2));				  
						  
						  t=tf2.readLine();
						  Double test=Double.parseDouble(t)*100;
						  greedy2_o_file.write(test.toString()+"\t");		
						  greedy_total_file.write(test.toString()+"\t");					  
						  
						  tf2.close();
					  }
					  greedy_o_file.close();
					  greedy2_o_file.close();
					  greedy_total_file.close();
					  
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

					  
					
			  }
	}
}
