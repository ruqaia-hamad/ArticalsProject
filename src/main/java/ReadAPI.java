import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ReadAPI {

	public static void readArticals() throws IOException, InterruptedException {

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

		for (Integer x = 0; x < articals.getResults().length; x++) {
			System.out.println("Artical publised date :" + articals.getResults()[x].getPublished_date() + "\n");
			System.out.println("Artical Title :" + articals.getResults()[x].getTitle() + "\n");
			System.out.println("Artical Source :" + articals.getResults()[x].getSource() + "\n");

		}
	}

	public static void readSection() throws IOException, InterruptedException {

		String jsonUrl = "https://api.nytimes.com/svc/search/v2/articlesearch.json?q=election&api-key=FcDfZ3AZLbdehhTWZ8KlzhVQSFnsqYLC";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(jsonUrl)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//			System.out.println(response.body());
		String responsee = response.body();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(responsee);
		String prettyJsonString = gson.toJson(je);
//			System.out.println(prettyJsonString);
		Sections section = new Gson().fromJson(prettyJsonString, Sections.class);

		for (Integer x = 0; x < section.getResponse().getDocs().length; x++) {
			System.out.println("Section name :" + section.getResponse().getDocs()[x].getSection_name() + "\n");
			System.out.println("subsection name :" + section.getResponse().getDocs()[x].getSubsection_name() + "\n");
			System.out.println("Document type :" + section.getResponse().getDocs()[x].getDocument_type() + "\n");

		}
	}

	public static void readAuthors() throws IOException, InterruptedException {

		String jsonUrl = "https://api.nytimes.com/svc/books/v3/lists/current/hardcover-fiction.json?api-key=FcDfZ3AZLbdehhTWZ8KlzhVQSFnsqYLC";

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(jsonUrl)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//			System.out.println(response.body());
		String responsee = response.body();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(responsee);
		String prettyJsonString = gson.toJson(je);
//			System.out.println(prettyJsonString);
		Authors authors = new Gson().fromJson(prettyJsonString, Authors.class);

		for (Integer x = 0; x < authors.getResults().getBooks().length; x++) {
			System.out.println("Author name:" + authors.getResults().getBooks()[x].getAuthor() + "\n");
			System.out.println("Artical Title :" + authors.getResults().getBooks()[x].getTitle() + "\n");
			System.out.println("Artical description :" + authors.getResults().getBooks()[x].getDescription() + "\n");
			System.out.println("Contributor :" + authors.getResults().getBooks()[x].getContributor() + "\n");
		}
	}

}
