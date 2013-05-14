import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class movid_actor {
	
	public static void main(String []args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException{
		String userName="root";
		String password = "yll";
		String url = "jdbc:mysql://localhost/experiment";
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn=(Connection) DriverManager.getConnection(url,userName,password);
		System.out.println("Database connection established");
		java.sql.Statement stmt =conn.createStatement();
		String command="insert into movieid value(";
		String command2="insert into actor value(";
		String end =");";
		BufferedReader rd= new BufferedReader(new FileReader ("H:\\research\\pengyu\\result\\final_movie.txt"));
		BufferedReader rd2= new BufferedReader(new FileReader ("H:\\research\\pengyu\\result\\actorid.txt"));
		BufferedReader rd3= new BufferedReader(new FileReader("H:\\research\\pengyu\\result\\actornessid.txt"));
		String line="";
		String st="";
		while(null!= (line=rd.readLine())){
			if (line.indexOf("\"") >= 0) {
				String[] a = line.split("\"");
				int k = line.split("\"").length;
				line = "";
				for (int i = 0; i < k - 1; i++) {
					line += a[i] + "\\\"";
				}

				line += a[k - 1];
			}
			//st=command2+line.split("\t")[0]+",\""+line.split("\t")[1]+"\""+end;//actorid
			st=command+line.split("\t")[0]+",\""+line.split("\t")[1]+"\","+line.split("\t")[2]+",\""+line.split("\t")[3]+"\",\""+line.split("\t")[4]+"\""+end;//movie
			System.out.println(st);
			stmt.execute(st);
			
			//movie_id, name, director, actor(mocie_actor.txt)
			                 
		}
		rd.close();
		rd2.close();
		rd3.close();
		
	}

}
