public class Book {
    private final String date;
    private final String title;

    public Book(String date, String title) {
        this.date = date;
        this.title = title;
    }

    public String getDate() { return date; }
    public String getTitle() { return title; }
}