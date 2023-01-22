import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class InsertToTables {

	static String url = "jdbc:sqlserver://localhost:1433;databaseName=NYAPI;encrypt=true;trustServerCertificate=true";
	static String user = "sa";
	static String pass = "root";

	public static void insertToArticals() throws IOException, InterruptedException, ReflectiveOperationException {

		String jsonUrl = "https://api.nytimes.com/svc/mostpopular/v2/shared/1/facebook.json?api-key=FcDfZ3AZLbdehhTWZ8KlzhVQSFnsqYLC";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(jsonUrl)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//	System.out.println(response.body());
		String responsee = response.body();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(responsee);
		String prettyJsonString = gson.toJson(je);
//	System.out.println(prettyJsonString);
		Articals articals = new Gson().fromJson(prettyJsonString, Articals.class);

		if (articals.getResults() != null) {
			for (Integer x = 0; x < articals.getResults().length; x++) {
				String published_date = articals.getResults()[x].getPublished_date();
				String title = articals.getResults()[x].getTitle();
				String source = articals.getResults()[x].getSource();

				String sql = "insert into Articals(published_date,title, source)" + "VALUES (?, ?, ?)";
				Connection con = null;
				try {
					Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
							.newInstance();
					DriverManager.registerDriver(driver);
					con = DriverManager.getConnection(url, user, pass);
					PreparedStatement stmt = con.prepareStatement(sql);

					stmt.setString(1, published_date);
					stmt.setString(2, title);
					stmt.setString(3, source);

					stmt.executeUpdate();
					System.out.println(" rows inserted.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void insertToAuthors() throws IOException, InterruptedException, Throwable, Exception, Throwable {

		String jsonUrl = "https://api.nytimes.com/svc/books/v3/lists/current/hardcover-fiction.json?api-key=FcDfZ3AZLbdehhTWZ8KlzhVQSFnsqYLC";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(jsonUrl)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//	System.out.println(response.body());
		String responsee = response.body();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(responsee);
		String prettyJsonString = gson.toJson(je);
//	System.out.println(prettyJsonString);
		Authors authors = new Gson().fromJson(prettyJsonString, Authors.class);

		if (authors.getResults().getBooks() != null) {
			for (Integer x = 0; x < authors.getResults().getBooks().length; x++) {
				String author = authors.getResults().getBooks()[x].getAuthor();
				String contributor = authors.getResults().getBooks()[x].getContributor();
				String title = authors.getResults().getBooks()[x].getTitle();
				String description = authors.getResults().getBooks()[x].getDescription();

				String sql = "insert into Authors(author,contributor, title,description)" + "VALUES (?, ?, ?, ?)";
				Connection con = null;
				try {
					Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
							.newInstance();
					DriverManager.registerDriver(driver);
					con = DriverManager.getConnection(url, user, pass);
					PreparedStatement stmt = con.prepareStatement(sql);

					stmt.setString(1, author);
					stmt.setString(2, contributor);
					stmt.setString(3, title);
					stmt.setString(4, description);

					int rowsInserted = stmt.executeUpdate();
					System.out.println(" rows inserted.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void insertToSections() throws InstantiationException, Throwable, ClassNotFoundException {

		String jsonUrl = "https://api.nytimes.com/svc/search/v2/articlesearch.json?q=election&api-key=FcDfZ3AZLbdehhTWZ8KlzhVQSFnsqYLC";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(jsonUrl)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//	System.out.println(response.body());
		String responsee = response.body();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(responsee);
		String prettyJsonString = gson.toJson(je);
//	System.out.println(prettyJsonString);
		Sections section = new Gson().fromJson(prettyJsonString, Sections.class);
		if (section.getResponse().getDocs() != null) {
			for (Integer x = 0; x < section.getResponse().getDocs().length; x++) {
				String section_name = section.getResponse().getDocs()[x].getSection_name();
				String subsection_name = section.getResponse().getDocs()[x].getSubsection_name();
				String document_type = section.getResponse().getDocs()[x].getDocument_type();

				String sql = "insert into Sections(section_name,subsection_name, document_type)" + "VALUES (?, ?, ?)";
				Connection con = null;
				try {
					Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
							.newInstance();
					DriverManager.registerDriver(driver);
					con = DriverManager.getConnection(url, user, pass);
					PreparedStatement stmt = con.prepareStatement(sql);

					stmt.setString(1, section_name);
					stmt.setString(2, subsection_name);
					stmt.setString(3, document_type);

					stmt.executeUpdate();
					System.out.println("rows inserted.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
