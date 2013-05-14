
public class com {
	public static java.util.Comparator<Tuple> getComparator() {   
	    return new java.util.Comparator<Tuple>() {   
	  
	      public int compare(Tuple o1, Tuple o2) {   
	        if(o1.rank<o2.rank)
	        {
	        	return 1;
	        }
	        else if(o1.rank>o2.rank)
	        {
	        	return -1;
	        }
	        else{
	        	return 0;
	        }
	      }   
	    };
	}
}
