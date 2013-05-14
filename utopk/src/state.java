import java.util.ArrayList;


public class state {
	public state(){}
	public void copy(ArrayList<Tuple> t)
	{
		for(int i=0;i<t.size();i++)
		{
			s.add(t.get(i));
		}
		
	}
    public double probability;
    public int l;//appear tuples
    public int i;//handl to the i th tuple in database
    //return the aggregate pro
    public ArrayList <Tuple> s =new ArrayList<Tuple>();
}
