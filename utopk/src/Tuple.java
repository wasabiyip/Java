


public class Tuple {
	public Tuple(){};
	public Tuple(int rank_,double pro_)
	{
		this.pro=pro_;
		this.rank=rank_;
	}
	public void copy(Tuple a)
	{
		this.rank=a.rank;
		this.pro=a.pro;
	}
	public 	double pro;
	public	int rank=0;
	
	
	//public int l=0;
	//public int s=0;
	//ArrayList<Tuple> posSet=new ArrayList<Tuple>();
} 
