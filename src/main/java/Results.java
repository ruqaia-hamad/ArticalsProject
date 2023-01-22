
public class Results {
private String published_date;
private String title;
private String source;
public String getPublished_date() {
	return published_date;
}
public void setPublished_date(String published_date) {
	this.published_date = published_date;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getSource() {
	return source;
}
public void setSource(String source) {
	this.source = source;
}
private Books[] books;

public Books[] getBooks() {
	return books;
}

public void setBooks(Books[] books) {
	this.books = books;
}
}