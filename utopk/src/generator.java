import java.util.PriorityQueue;


public class generator {
	public generator(){}
	
	public PriorityQueue<Tuple> g(){
		PriorityQueue<Tuple> a= new PriorityQueue<Tuple> (1000,com.getComparator());
		
		  for(int i=0;i<1000;i++){
			  Tuple b=new Tuple();
			  b.rank=i;
				b.pro=Math.random();
		    	a.add(b);
		    	System.out.print(i+" "+b.pro+"\n");
		    	try {Thread.sleep(50);} 
		    	catch(Exception e) {}

		  }
		return a;
		
	}

}
