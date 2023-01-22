import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SqlQuries {
	static String url = "jdbc:sqlserver://localhost:1433;databaseName=NYAPI;encrypt=true;trustServerCertificate=true";
	static String user = "sa";
	static String pass = "root";

	public static void addingId() throws Throwable {

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the title of Artical ");
		String title = sc.next();
		System.out.print("Enter the source of Artical ");
		String source = sc.next();
		String sql ="select id  from Authors where author = 'John Grisham'";
		String sql2="select id from Sections where section_name='U.S.' And document_type='article'";

		Connection con = null;

		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			// Registering drivers
			DriverManager.registerDriver(driver);

			// Reference to connection interface
			con = DriverManager.getConnection(url, user, pass);
			PreparedStatement pstmt = con.prepareStatement(sql);
			PreparedStatement pstmt2 = con.prepareStatement(sql2);
//			pstmt.executeUpdate();

	
			try {
			ResultSet rs = pstmt.executeQuery();
			ResultSet rs2 = pstmt2.executeQuery();
			if (rs.next() && rs2.next() ) {
				   int authorId = rs.getInt("id");
				   int sectionId = rs2.getInt("id");
	                sql = "INSERT INTO Articals (title ,source,published_date,Author_id , Section_id) VALUES (?,?,?,?,?)";
					try {
						PreparedStatement pstmt3 = con.prepareStatement(sql);
						 pstmt3.setString(1, title);
						 pstmt3.setString(2, source);
						 pstmt3.setDate(3, new Date(System.currentTimeMillis()));
						 pstmt3.setInt(4, authorId);
						 pstmt3.setInt(5, sectionId);
						 pstmt3.executeUpdate();
					}catch (SQLException e) {
						System.out.println(e);

			}
		} }catch (SQLException e) {
			System.out.println(e);
		}
			
		}catch (SQLException e) {
			System.out.println(e);
		}
			
		}
	}

	

