import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class movieactor {

	public static void main(String args[]) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		String line = "";
		String insert = "";

		Connection conn = null;
		try {
			BufferedReader rd = new BufferedReader(new FileReader(
					"H:\\research\\pengyu\\producers.list"));
			
			FileWriter fw = null;
			fw = new FileWriter("h:\\testtest1.txt");

			// BufferedReader rd2 = new BufferedReader(new FileReader(
			// "H:\\research\\pengyu\\actresses.list"));

			String userName = "root";
			String password = "yll";
			String url = "jdbc:mysql://localhost/experiment";
			Class.forName("com.mysql.jdbc.Driver").newInstance();// ?
			conn = DriverManager.getConnection(url, userName, password);
			System.out.println("Database connection established");
			Statement stmt = conn.createStatement();

			String command = "insert into movieactornesstemp  value(";
			String end = ");";
			String temp = "";
			int l = 0;
			String name=null;
			String st=null;
			int i=0;
			while (null != (line = rd.readLine())) {
	
				if (line.length() == 0) {
					line = rd.readLine();
					/*
					if (line.indexOf("\"") >= 0) {
						String[] a = line.split("\"");
						int k = line.split("\"").length;
						line = "";
						for (int j = 0; j < k - 1; j++) {
							line += a[j] + "\\\"";
						}

						line += a[k - 1];
					}*/
					int v = line.split("\t").length;
					String[] b = line.split("\t");
					//System.out.println(b[0]);
					name=b[0];
					String []bb=b[v-1].split("[)]");
					//st=command+"\""+b[0]+"\",\""+bb[0] + ")\""+end;
					fw.write(b[0]+"\t"+bb[0] + ")\r\n");
					
				} else {
					/*
					if (line.indexOf("\"") >= 0) {
						String[] a = line.split("\"");
						int k = line.split("\"").length;
						line = "";
						for (int j = 0; j < k - 1; j++) {
							line += a[j] + "\\\"";
						}

						line += a[k - 1];
					}
					*/
					int v = line.split("\t").length;
					String []b = line.split("\t");					
					//System.out.println(b[v-1]);
					String []bb=b[v-1].split("[)]");
					//st=command+"\""+name+"\",\""+bb[0] + ")\""+end;
					fw.write(name+"\t"+bb[0] + ")\r\n");
					
				}
				//fw.write(st+"\r\n");
				//System.out.println(st);
				//stmt.execute(st);
				i++;
			}
			fw.close();
			conn.close();
			System.out.println("end!");
			rd.close();
			// rd2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
