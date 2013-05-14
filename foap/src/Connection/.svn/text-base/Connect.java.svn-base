package Connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {

	public static void main(String args[]) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		String line="";
		String insert ="";
		Connection conn=null;
		try {
			
			 String userName = "root";
             String password = "yll";
             String url = "jdbc:mysql://localhost/yll";
             Class.forName ("com.mysql.jdbc.Driver").newInstance ();
             conn = DriverManager.getConnection (url, userName, password);
             System.out.println ("Database connection established");
			Statement stmt =conn.createStatement();
          //open the sql insertion file
			BufferedReader in  = new BufferedReader(new java.io.FileReader("C:\\Users\\yll\\Desktop\\social network dataset\\foaf\\umbc-foafpub-2005-feb\\triple_person.sql"));
			
			//insert triples
			while(null!=(line=in.readLine())){
				if (line.startsWith("INSERT INTO triple_person")){
					if (insert.length()>0)
						stmt.execute(insert);
					insert = line;
				}else
					insert +="\n"+line;
			}
			//dbw.operateWrite(insert);

			in.close();
		}catch (SQLException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	
	
	
}
