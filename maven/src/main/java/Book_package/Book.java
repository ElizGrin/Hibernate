package Book_package;


public class Book {

    private String isbn;
    private String author;
    private String bookName;
    private String publisher;
    private String year;

    public Book(){

    }

    public Book(String isbn, String author, String name, String publisher, String year){
        this.isbn = isbn;
        this.author = author;
        this.bookName = name;
        this.publisher = publisher;
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getYear() {
        return year;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAuthor(String auth){
        this.author = auth;
    }


    public void setBookName(String bookName){
        this.bookName = bookName;
    }


    public void setPublisher(String publ){
        this.publisher = publ;
    }


    public void setYear(String year){
        this.year = year;
    }

    public String toString(){
        return "Book { ISBN: " + isbn + "; Author: " + author + "; Book: "+ bookName +
                "; Publisher: "+ publisher + "; Year: "+ year+ " }";
    }

}

