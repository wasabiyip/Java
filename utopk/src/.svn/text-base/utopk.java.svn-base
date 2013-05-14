import java.util.ArrayList;
import java.util.PriorityQueue;


public class utopk {
	public utopk(){}
	
	double aa=1.0;
	public  state find(){
	int d=0;
	PriorityQueue<state> topkc = new PriorityQueue<state> (100,coms.getComparator());
		
	state begin =new state();
	begin.probability=1.0;
	begin.i=0;
	begin.l=0;
	topkc.add(begin);
	seen=new ArrayList<Tuple>();
	Tuple t=new Tuple();
 	while((!sourceQueue.isEmpty())&&(!topkc.isEmpty()) )
	{
		
		
		state mid=null;
		 mid=topkc.poll();
		
        if(mid.s.size()==k)
        	{ 
        	
        	return mid;
        	}///////////////////
        else{
        	
        	if(mid.i==d)
        		{
        		t = sourceQueue.poll();
        		d=d+1; 
        		Tuple temp=new Tuple();
        		temp.copy(t);
            	seen.add(temp);
        		}
        	else {   		
        		 t = new Tuple(seen.get(mid.i).rank,seen.get(mid.i).pro);///////
        		}       	
       
	        state extens1=new state();
	        state extens2=new state();
	       
	  
	        extens1.i=mid.i+1;
	        extens1.copy(mid.s);	        
	        extens1.probability=mid.probability*(aa-t.pro);
	        extens2.i=mid.i+1;
	        extens2.copy(mid.s);
	        extens2.s.add(t);
	        extens2.probability=mid.probability*t.pro;

	        topkc.add(extens2);
	        topkc.add(extens1);
	         }	
          }	
	
	return topkc.poll();	
	}
	
	public ArrayList<Tuple> seen;
    public int k;
	public PriorityQueue<Tuple> sourceQueue;


}
