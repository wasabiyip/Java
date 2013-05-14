import java.util.*;
import java.io.*;
import java.util.Calendar;

public class greedy {
	static int paper_number = 0;
	static int reviewer_number = 0;
	static int paper_q = 0;
	static int reviewer_q = 0;
	// input reviewer*paper, paper ID, reviewer ID
	static String reviewer_paper = "/PRC.txt";// matrix
	static String paper_topic = "/PT1.txt";// matrix paper topic
	static String reviewer_topic = "/RT1.txt";// matrix reviewer topic
	static String paper_ID = "/PID.txt";
	static String reviewer_ID = "/RID.txt";
	static String setting = "/config";// set the first four parameters
	
	// out
	static String out = "/assign.txt";

// evaluation
//	

static String running_time_read = "/running_time.txt";
static String distribution_assginment = "/distribution_assginment.txt";
static String distribution_coi = "/distribution_coi.txt";
static String average_coi = "/average_coi.txt";
static String average_topic = "/average_topic.txt";
static String average_topiccoverd = "/average_topiccoverd.txt";
static String distribution_coverdtopic = "/distribution_coverdtopic.txt";
static String poportion_t_c="/coverd-total.txt";
	 
/*static String running_time_read = "test/greedy/running_time.txt";
static String distribution_assginment = "test/greedy/distribution_assginment.txt";
static String distribution_coi = "test/greedy/distribution_coi.txt";
static String average_coi = "test/greedy/average_coi.txt";
static String average_topic = "test/greedy/average_topic.txt";
static String average_topiccoverd = "test/greedy/average_topiccoverd.txt";
static String distribution_coverdtopic = "test/greedy/distribution_coverdtopic.txt";*/

	public static void main(String[] arg) {
		/*
		 * aList is reviewer_paper al, al_sort is paper_topic_count PI RI
		 */
		// System.out.println(arg[0]);
		// real ex
		 if (arg.length<=0){
		 System.out.println("Please input the parameter again!"); } else{
			
			 reviewer_paper=arg[1]+reviewer_paper;
			
			 setting=arg[0]+setting; 
			// reviewer_paper=arg[0]+reviewer_paper;
			 paper_topic=arg[0]+paper_topic; 
			 reviewer_topic=arg[0]+reviewer_topic;
			 paper_ID=arg[0]+paper_ID; 
			 reviewer_ID=arg[0]+reviewer_ID; 
			 
			 out=arg[2]+out;
		  
			  running_time_read=arg[2]+running_time_read;
			  distribution_assginment=arg[2]+distribution_assginment;
			  distribution_coi=arg[2]+distribution_coi;
			  average_coi=arg[2]+average_coi;
			  average_topic=arg[2]+average_topic;
			  average_topiccoverd=arg[2]+average_topiccoverd;
			  distribution_coverdtopic=arg[2]+distribution_coverdtopic;
			  poportion_t_c=arg[2]+poportion_t_c;
		 
/*
		setting = "test" + setting;
		reviewer_paper = "test" + reviewer_paper;
		paper_topic = "test" + paper_topic;
		reviewer_topic = "test" + reviewer_topic;
		paper_ID = "test" + paper_ID;
		reviewer_ID = "test" + reviewer_ID;
		out = "test" + out;
		*/
		
		
		
		
		
		try {

			BufferedReader setfile = new BufferedReader(new InputStreamReader(
					new FileInputStream(setting)));
			String emp = setfile.readLine();
			emp = setfile.readLine();
			// reviewer_paper = setfile.readLine().trim();
			// paper_topic = setfile.readLine().trim();
			// reviewer_topic = setfile.readLine().trim();
			// paper_ID = setfile.readLine();
			// reviewer_ID = setfile.readLine().trim();
			paper_number = Integer.parseInt(setfile.readLine().trim());
			reviewer_number = Integer.parseInt(setfile.readLine().trim());
			paper_q = Integer.parseInt(setfile.readLine().trim());
			reviewer_q = Integer.parseInt(setfile.readLine().trim());

			
			
			
			// read reviewer_paper
			

			BufferedReader reviewer_paper_file = new BufferedReader(
					new FileReader(reviewer_paper));
			List<List<Integer>> aList = new ArrayList<List<Integer>>();
			String weight = "";
			String[] temp;
List<Integer> COI =new ArrayList<Integer>();
for(int m=0;m<reviewer_number;m++){
	COI.add(0);
}
int coi_t=0;
			while ((weight = reviewer_paper_file.readLine()) != null) {
				// System.out.println(weight);
				temp = weight.split(" ");
				List<Integer> aListData = new ArrayList<Integer>();
				for (int i = 0; i < temp.length; i++) {
					aListData.add(Integer.parseInt(new String(temp[i])));
if(Integer.parseInt(new String(temp[i]))==-1){
	coi_t=COI.get(i);
	COI.set(i, coi_t+1);

}
				}
				aList.add(aListData);

			}

			// aList.get(0).get(0) paper*reviewer
			/*
			 * // read paper_topic_count BufferedReader countarray = new
			 * BufferedReader(new FileReader( paper_topic_count)); String number
			 * = ""; ArrayList<Integer> ca = new ArrayList<Integer>();
			 * ArrayList<Integer> ca_sort = new ArrayList<Integer>(); while
			 * ((number = countarray.readLine()) != null) {
			 * ca.add(Integer.parseInt(number.trim()));
			 * ca_sort.add(Integer.parseInt(number.trim())); }
			 */

			
long startTime = System.currentTimeMillis();
			// read paper*topic and paper_topic_number
			ArrayList<Integer> ca = new ArrayList<Integer>();
			ArrayList<Integer> ca_sort = new ArrayList<Integer>();

			int paper = 0, sum = 0;
			BufferedReader paper_topic_file = new BufferedReader(
					new FileReader(paper_topic));
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

			// read reviewer*topic
			BufferedReader reviewer_topic_file = new BufferedReader(
					new FileReader(reviewer_topic));
			List<List<Integer>> aList_rt = new ArrayList<List<Integer>>();
			while ((weight = reviewer_topic_file.readLine()) != null) {
				temp = weight.split(" ");
				List<Integer> aListData_rt = new ArrayList<Integer>();
				for (int i = 0; i < temp.length; i++) {
					aListData_rt.add(Integer.parseInt(new String(temp[i])));

				}
				

				aList_rt.add(aListData_rt);
			}

			// read paper_ID
			BufferedReader paper_ID_file = new BufferedReader(new FileReader(
					paper_ID));
			String temp2 = "";
			ArrayList<Integer> PI = new ArrayList<Integer>();
			while ((temp2 = paper_ID_file.readLine()) != null) {
				PI.add(Integer.parseInt(temp2.trim()));
			}

			// read reviewer_ID
			BufferedReader reviewer_ID_file = new BufferedReader(
					new FileReader(reviewer_ID));
			String temp3 = "";
			ArrayList<Integer> RI = new ArrayList<Integer>();
			while ((temp3 = reviewer_ID_file.readLine()) != null) {
				RI.add(Integer.parseInt(temp3.trim()));
			}

long endTime = System.currentTimeMillis();

			// initial the paper_q and reviewer_q
			ArrayList<Integer> paper_q_l = new ArrayList<Integer>();
			ArrayList<Integer> reviewer_q_l = new ArrayList<Integer>();
			for (int i = 0; i < paper_number; i++) {
				paper_q_l.add(paper_q);
			}
			for (int i = 0; i < reviewer_number; i++) {
				reviewer_q_l.add(reviewer_q);
			}
			/*
			 * 
			 * // sort the paper according to topics number
			 * Collections.sort(ca_sort); ArrayList<Integer> pos = new
			 * ArrayList<Integer>(); for (int i = 0; i < ca_sort.size(); i++) {
			 * Object a = ca_sort.get(i); pos.add(ca.indexOf(a));
			 * 
			 * }
			 */

			// greedy

			BufferedWriter assign = new BufferedWriter(new FileWriter(out));
long startTime1 = System.currentTimeMillis();
			for (int i = 0; i < paper_number; i++) {
				// find the most topic paper in ca and decide which paper
				int temp5 = Collections.max(ca);
				

				int position = ca.indexOf(temp5);
				//System.out.println(i);
				for (int l = 0; l < paper_q; l++) {
					// find the max value in aList.get(position)
					// check reviewer quota
					// position is paper , index is reviewer
					int tempmax = 0;
					tempmax = Collections.max(aList.get(position));
					int index=0;// = aList.get(position).indexOf(tempmax);
/////////////////
					
					List<Integer> posall=new ArrayList<Integer>();
					for(int m =0;m<aList.get(position).size();m++){
						if (aList.get(position).get(m)>=tempmax){
							posall.add(m);
						}
					}
					//check quota
					int restquota=-1;
					int position1=0,temppos=0;
					for(int m=0;m<posall.size();m++){
						temppos=posall.get(m);
//						System.out.print(reviewer_q_l.size()+" "+temppos+"\n");
						if (reviewer_q_l.get(temppos)>restquota)
							{
							position1=temppos;
							restquota=reviewer_q_l.get(temppos);
							}
					}
					index=position1;
////////////////
					
					
					// int control=1; &&control<RI.size()
					while (RI.get(index) == -1) {
						int temptemp = 0;
						aList.get(position).set(index, -1);
						temptemp = Collections.max(aList.get(position));
						index = aList.get(position).indexOf(temptemp);
						// System.out.println(RI.get(index));
						// control++;
					}

					// reviewer ID>=0 assign and set reviewer*paper as -1
					assign
							.write(PI.get(position) + " " + RI.get(index)
									+ "\n");
					// System.out.println(PI.get(position) + "\t" +
					// RI.get(index)+ "\n");
					aList.get(position).set(index, -1);

					// check whether is 0 if it is set as the reviewer in RI as
					// -1 and update reviewer quota
					int templ = reviewer_q_l.get(index);
					templ -= 1;
					reviewer_q_l.set(index, templ);
					if ((templ) == 0) {
						// set reviewer[j] as -1
						// for (int m = 0; m < aList.size(); m++) {
						// aList.get(m).set(index, -1);
						// }
						RI.set(index, -1);

					}

					// update the topic coverage in paper*reviewer and paper
					// topic

					// update 1.paper topic 2. paper reviewer not -1
					// find communal set, while,update
					List<Integer> communal = new ArrayList<Integer>();
					List<Integer> p = aList_pt.get(position);
					List<Integer> r = aList_rt.get(index);
					for (int m = 0; m < p.size(); m++) {
						if (p.get(m) == 1 && r.get(m) == 1) {
							communal.add(m);
							aList_pt.get(position).set(m, 0);
						}

					}
					List<Integer> temp6 = aList.get(position);
					int tempk;
					for (int m = 0; m < reviewer_number; m++) {
						int w = temp6.get(m);// reivewer*paper
						if (w != -1 && w != 0) {
							for (int l1 = 0; l1 < communal.size(); l1++) {
								// get the weight
								tempk = communal.get(l1);
								if (aList_rt.get(m).get(tempk) == 1 && w >= 0) {
									w--;
								}
							}
							//System.out.println("aList.get(position).set(" + m
							//		+ "," + w + ");" + "position" + position);
							aList.get(position).set(m, w);
						}
					}

					/*
					 * int temp6 = reviewer_q_l.get(index);
					 * reviewer_q_l.set(index, temp6 - 1); if (temp6 - 1 == 0) {
					 * RI.set(index, -1); }
					 */

				}

				ca.set(position, -1);// find next paper

			}
// running time in MilliSecond
			long endTime1 = System.currentTimeMillis();
			long elapseTime = endTime - startTime;
			long elapseTime2 = endTime1 - startTime1;
		//	System.out.println("time" + elapseTime);
			BufferedWriter time_file = new BufferedWriter(new FileWriter(
					running_time_read));

			time_file.write("Time read data:" + elapseTime+"\n");
			time_file.write("Time for algorithm:" + elapseTime2);
			time_file.close();
			
//assignment distribution
			List<Integer> re=new ArrayList<Integer>();
			int []lt=new int[reviewer_q+1];
			for (int m=0;m<reviewer_q_l.size();m++){
				int iner=reviewer_q-reviewer_q_l.get(m);
//			System.out.println(m+" "+iner+" "+reviewer_q);
				re.add(m, iner);
/*				if(iner==reviewer_q+1)
					lt[reviewer_q]++;
				else*/
					lt[iner]++;
					
			}
		

			BufferedWriter assign_file = new BufferedWriter(new FileWriter(
					distribution_assginment));
			for(int m=0;m<reviewer_q+1;m++){
				assign_file.write((m)+" "+lt[m]+"\n");
			}
			assign_file.close();
			
//coi distribution + avg  //////////////////////////////////
			
			Collections.sort(COI);
			int last=-1;
			int count=0;
			int sumcoi=0;
			BufferedWriter distribution_coi_file = new BufferedWriter(new FileWriter(
					distribution_coi));
			BufferedWriter average_coi_file = new BufferedWriter(new FileWriter(
					average_coi));
			
/*			for(int m=0;m<COI.size();m++){
				System.out.println(COI.get(m));
			}*/
			
			for(int m=0;m<COI.size();m++){
				count++;
				sumcoi+=COI.get(m);
				//System.out.println(m+" "+count);
				if(COI.get(m)!=last){/////////////////////////
					//System.out.println(m+" "+count);

					if(m==0){count=2;last=COI.get(m);}
					else{
						distribution_coi_file.write(COI.get(m)+" "+(count-1)+"\n");
						count=1;
						last=COI.get(m);}
				}
				else if(m==COI.size()-1){
					distribution_coi_file.write(COI.get(m)+" "+count+"\n");
					
				}
			}
			
			
			
			//System.out.println("coi "+COI.size());
			//System.out.println(sumcoi);
			Double to=(double)sumcoi/COI.size();
			average_coi_file.write(to.toString());
			average_coi_file.close();
			distribution_coi_file.close();
			
//topic per paper//ca

	//average topic per paper
			BufferedWriter average_topic_file = new BufferedWriter(new FileWriter(
					average_topic));
			int casort=0;
			for(int m=0;m<ca_sort.size();m++){
				casort+=ca_sort.get(m);
			}
		//	System.out.println(casort);
			to=(double)casort/paper_number;
			int down = casort;
			average_topic_file.write(to.toString());
			average_topic_file.close();
			
			
	//average coverd topic per paper
			BufferedWriter average_topiccoverd_file = new BufferedWriter(new FileWriter(
					average_topiccoverd));			
			List<Integer> newca=new ArrayList<Integer>();
			int count1=0;
			for(int m=0;m<aList_pt.size();m++){
				count1=0;
				for (int l=0;l<aList_pt.get(m).size();l++){
					if(aList_pt.get(m).get(l)==1)
						count1++;
					//System.out.println(count1);
				}	
				
				newca.add(count1);
			}
			casort=0;
			for(int m=0;m<ca_sort.size();m++){
				casort+=ca_sort.get(m)-newca.get(m);
			}
			//System.out.println(casort);
			to=(double)casort/paper_number;
			int up=casort;
			average_topiccoverd_file.write(to.toString());
			average_topiccoverd_file.close();

			
			
			BufferedWriter poportion_t_c_file = new BufferedWriter(new FileWriter(
					poportion_t_c));		
			to = (double)up/down;
			poportion_t_c_file.write(to.toString());
			poportion_t_c_file.close();
			
	//percentage-paper
			List<Double> percentage=new ArrayList<Double>();
			for(int m=0;m<paper_number;m++){
				percentage.add((double)(ca_sort.get(m)-newca.get(m))/ca_sort.get(m));
				
			}
			int[] pp=new int[10];
			for(int m=0;m<pp.length;m++){
				pp[m]=0;
			}
			for(int m=0;m<paper_number;m++){
				if(0<percentage.get(m) && percentage.get(m)<=0.1 )
					pp[0]++;
				else if(percentage.get(m)<=0.2)
					pp[1]++;
				else if(percentage.get(m)<=0.3)
					pp[2]++;
				else if(percentage.get(m)<=0.4)
					pp[3]++;
				else if(percentage.get(m)<=0.5)
					pp[4]++;
				else if(percentage.get(m)<=0.6)
					pp[5]++;
				else if(percentage.get(m)<=0.7)
					pp[6]++;
				else if(percentage.get(m)<=0.8)
					pp[7]++;
				else if(percentage.get(m)<=0.9)
					pp[8]++;
				else if(percentage.get(m)<=1)
					pp[9]++;
				
			}
			BufferedWriter distribution_coverdtopic_file = new BufferedWriter(new FileWriter(
					distribution_coverdtopic));	
			for(int m=9;m<10;m++){
			distribution_coverdtopic_file.write("0."+m+" "+pp[m]+"\n");
			}
			distribution_coverdtopic_file.close();
			

			assign.close();
			setfile.close();
			reviewer_paper_file.close();
			paper_topic_file.close();
			reviewer_topic_file.close();
			paper_ID_file.close();
			reviewer_ID_file.close();

			/*
			 * aList is reviewer_paper al, al_sort is paper_topic_count PI RI
			 * paper_q_l reviewer_q_l
			 */

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		 }

	}

}
