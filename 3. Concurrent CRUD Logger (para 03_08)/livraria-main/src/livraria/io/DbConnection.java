package livraria.io;

import java.sql.DriverManager;

import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;

public class DbConnection {
	private String userName, password, url, driver;
	private Connection con;
	private Statement st;

	public DbConnection() {
		userName = "root";
		password = "root";
		url = "jdbc:mariadb://localhost:3306/livraria";
		driver = "org.mariadb.jdbc.Driver";
		try {
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url, userName, password);
			st = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getCon() {
		return con;
	}

	public Statement getSt() {
		return st;
	}

}
