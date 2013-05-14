import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class test {
	public static void main(String [] arg){
		List<Integer>ca_sort = new ArrayList<Integer>();
		ca_sort.add(7);
		ca_sort.add(5);
		ca_sort.add(6);
		Collections.sort(ca_sort);
		for(int i=0;i<ca_sort.size();i++){

			System.out.println(ca_sort.get(i));
				
		}
		int a=9,b=7;
		double l=a/b;
		System.out.println(l);
	}

}
