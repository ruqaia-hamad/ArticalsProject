import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import jdk.internal.jmod.JmodFile.Section;

public class SqlQuries {
	static String url = "jdbc:sqlserver://localhost:1433;databaseName=NYAPI;encrypt=true;trustServerCertificate=true";
	static String user = "sa";
	static String pass = "root";

	public static void addingId(String author) throws Throwable {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter the title: ");
		String title = br.readLine();

		System.out.print("Enter the source of Artical ");
		String source = br.readLine();

		String sql = "select id  from Authors where author = ?";
		String sql2 = "select id from Sections where section_name='U.S.' And document_type='multimedia'";

		Connection con = null;

		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			// Registering drivers
			DriverManager.registerDriver(driver);

			// Reference to connection interface
			con = DriverManager.getConnection(url, user, pass);
			PreparedStatement pstmt = con.prepareStatement(sql);
			PreparedStatement pstmt2 = con.prepareStatement(sql2);
			pstmt.setString(1, author);
//			pstmt.executeUpdate();

			try {
				int authorId = 0;
				int sectionId = 0;

				ResultSet rs = pstmt.executeQuery();
				ResultSet rs2 = pstmt2.executeQuery();
				if (rs.next()) {
					authorId = rs.getInt("id");

				}
				if (rs2.next()) {
					sectionId = rs2.getInt("id");

				}
				if (authorId != 0 && sectionId != 0) {

					sql = "INSERT INTO Articals (title ,source,published_date,Author_id , Section_id) VALUES (?,?,?,?,?)";
					try {
						PreparedStatement pstmt3 = con.prepareStatement(sql);
						pstmt3.setString(1, title);
						pstmt3.setString(2, source);
						pstmt3.setDate(3, new Date(System.currentTimeMillis()));
						pstmt3.setInt(4, authorId);
						pstmt3.setInt(5, sectionId);
						pstmt3.executeUpdate();
						System.out.println("added successfully");
					} catch (SQLException e) {
						System.out.println(e);

					}
				}
			} catch (SQLException e) {
				System.out.println(e);
			}

		} catch (

		SQLException e) {
			System.out.println(e);
		}

	}

	// --------------------------------------------------------

	public static void listTopFive() throws Throwable {

		Scanner scanner = new Scanner(System.in);

		String sql = "SELECT TOP 5 Section_id, COUNT(*) as count\r\n"
				+ "FROM Articals\r\n"
				+ "where Section_id !=0\r\n"
				+ "GROUP BY section_id\r\n"
				+ "ORDER BY count DESC\r\n"
				+ "";

		Connection con = null;

		try {

			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

			DriverManager.registerDriver(driver);

			con = DriverManager.getConnection(url, user, pass);

			// Creating a statement
			Statement st = con.createStatement();

			// Executing query
			ResultSet m = st.executeQuery(sql);

			while (m.next()) {
				int id = m.getInt("Section_id");
				int count = m.getInt("count");
				// print the results
				System.out.println("Section id :" + id + "\n" + "Count :" + count + "\n");
			}
			st.close();
		} catch (Exception e) {
			System.out.println("Got an exception! ");
			System.out.println(e.getMessage());
		}
	}

	//---------------------------------------------------------------
	
	
	
	public static void listArticalsByAuthor() throws Throwable {

		Scanner scanner = new Scanner(System.in);

		String sql = "\r\n"
				+ "\r\n"
				+ "SELECT a.author, COUNT(*) as article_count\r\n"
				+ "FROM Articals ar\r\n"
				+ "JOIN Authors a ON ar.Author_id = a.id\r\n"
				+ "GROUP BY a.author;\r\n"
				+ "";

		Connection con = null;

		try {

			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

			DriverManager.registerDriver(driver);

			con = DriverManager.getConnection(url, user, pass);

			// Creating a statement
			Statement st = con.createStatement();

			// Executing query
			ResultSet m = st.executeQuery(sql);

			while (m.next()) {
				String Author_name = m.getString("author");
				int article_count = m.getInt("article_count");
				// print the results
				System.out.println("Author name :" + Author_name  + "\n" + "Article count :" + article_count + "\n");
			}
			st.close();
		} catch (Exception e) {
			System.out.println("Got an exception! ");
			System.out.println(e.getMessage());
		}
	}
}
