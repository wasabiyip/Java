import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class connector {

	public static void main(String args[]) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		String line = "";
		
		Connection conn = null;
		try {
			BufferedReader rd = new BufferedReader(new FileReader(
					"H:\\research\\pengyu\\movie_titles.txt"));
			String userName = "root";
			String password = "yll";
			String url = "jdbc:mysql://localhost/experiment";
			Class.forName("com.mysql.jdbc.Driver").newInstance();// ?
			conn = DriverManager.getConnection(url, userName, password);
			System.out.println("Database connection established");
			Statement stmt = conn.createStatement();
			String command = "insert into movie value(";
			String end = ");";
			String temp = "";
			int l = 0;
			while (null != (line = rd.readLine())) {
				if (line.indexOf("\"") > 0) {
					String[] a = line.split("\"");
					int k = line.split("\"").length;
					line = "";
					for (int i = 0; i < k - 1; i++) {
						line += a[i] + "\\\"";
					}

					line += a[k - 1];
				}

				temp = "";
				l = line.split(",").length;
				for (int i = 2; i < l; i++) {
					temp += line.split(",")[i];
				}
				temp += " (";
				temp += line.split(",")[1];
				temp += ")";
				String st = command + line.split(",")[0] + ",\"" + temp + "\""
						+ end;
				System.out.println(st);
				stmt.execute(st);
			}
			conn.close();
			System.out.println("end!");
			rd.close();
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
