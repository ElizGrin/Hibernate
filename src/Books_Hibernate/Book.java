package Books_Hibernate;

public class Book {

    private int isbn;
    private String author;
    private String bookName;
    private String publisher;
    private String year;

    public Book(){

    }

    public Book(int isbn, String author, String name, String publisher, String year){
        this.isbn = isbn;
        this.author = author;
        this.bookName = name;
        this.publisher = publisher;
        this.year = year;
    }

    public int getIsbn(){
        return isbn;
    }

    public void setIsbn(int num){
        this.isbn = num;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String auth){
        this.author = auth;
    }

    public String getBook(){
        return bookName;
    }

    public void setBookName(String bookName){
        this.bookName = bookName;
    }

    public String getPublisher(){
        return publisher;
    }

    public void setPublisher(String publ){
        this.publisher = publ;
    }

    public String getYear(){
        return year;
    }

    public void setYear(String year){
        this.year = year;
    }

    public String toString(){
        return "Book { ISBN: " + isbn + "; Author: " + author + "; Book: "+ bookName +
                "; Publisher: "+ publisher + "; Year: "+ year+ " }";
    }

}
