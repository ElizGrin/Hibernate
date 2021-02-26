package Book_package;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;


public class BookRunner {
    private static SessionFactory sessionFactory;
    private static Scanner scanner = new Scanner(System.in);;
    private static List<Book> list;


    public static void main(String[] args) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        BookRunner br = new BookRunner();
        boolean flag = true;
        while (flag) {
            switch (menu()) {
                case 0:
                    System.out.println("\nExit.");
                    scanner.close();
                    flag = false;
                    break;
                case 1:
                    System.out.println("List of books:\n");
                    list = br.BookList();
                    for (Book book : list) {
                        System.out.println(book);
                    }
                    System.out.println("-----------------------------");
                    break;
                case 2:
                    System.out.println("Adding records to the DB");
                    System.out.println("ISBN: ");
                    String num = scanner.next();
                    scanner.nextLine();
                    System.out.println("Author: ");
                    String author = scanner.nextLine();
                    System.out.println("Bookname: ");
                    String bookN = scanner.nextLine();
                    System.out.println("Publisher: ");
                    String publ = scanner.nextLine();
                    System.out.println("Year: ");
                    String year = scanner.next();
                    br.add(num, author, bookN, publ, year);
                    System.out.println("-----------------------------");
                    break;
                case 3:
                    System.out.println("Updating all fields\n");
                    System.out.println("ISBN: ");
                    String number = scanner.nextLine();
                    System.out.println("Author: ");
                    String aut = scanner.nextLine();
                    System.out.println("Bookname: ");
                    String bkN = scanner.nextLine();
                    System.out.println("Publisher: ");
                    String pbl = scanner.nextLine();
                    System.out.println("Year: ");
                    String yr = scanner.nextLine();
                    br.update1(number,aut,bkN,pbl,yr);
                    System.out.println("-----------------------------");
                    break;
                case 4:
                    System.out.println("Updating publisher\n");
                    System.out.println("Existing Publisher: ");
                    String pblshr1 = scanner.nextLine();
                    System.out.println("For change Publisher: ");
                    String pblshr2 = scanner.nextLine();
                    br.update2(pblshr1,pblshr2);
                    System.out.println("-----------------------------");
                    break;
                case 5:
                    System.out.println("Updating publisher using ISBN\n");
                    System.out.println("ISBN: ");
                    String n = scanner.nextLine();
                    System.out.println("For change Publisher: ");
                    String p1 = scanner.nextLine();
                    br.update3(n,p1);
                    System.out.println("-----------------------------");
                    break;
                case 6:
                    System.out.println("Removing the record by ISBN\n");
                    System.out.println("ISBN: ");
                    String isbnN = scanner.nextLine();
                    br.remove1(isbnN);
                    System.out.println("-----------------------------");
                    break;
                case 7:
                    System.out.println("Removing the record by Publisher\n");
                    System.out.println("Publisher: ");
                    String p = scanner.nextLine();
                    br.remove2(p);
                    System.out.println("-----------------------------");
                    break;
                case 8:
                    System.out.println("Removing the record by Year\n");
                    System.out.println("Year: ");
                    String y = scanner.nextLine();
                    br.remove3(y);
                    System.out.println("-----------------------------");
                    break;
                case -1:
                    System.out.println("Wrong input!! Try again\n"); break;
            }
        }
    }

    public List<Book> searchYearGap(int year1, int year2){
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Query q = session.createQuery("FROM Book WHERE year BETWEEN = :y1 AND = :y2");
        q.setParameter("y1", year1);
        q.setParameter("y2", year2);
        List<Book> list = q.list();
        transaction.commit();
        session.close();
        return list;
    }

    public void add(String isbn, String author, String book, String publisher, String year) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Book b = new Book(isbn, author, book, publisher, year);
        session.save(b);
        transaction.commit();
        session.close();
        System.out.println("The record is added.\n");
    }

    public List<Book> BookList() {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        List<Book> list = session.createQuery("FROM Book").list();   //Создаёт новый экземпляр запроса (Query) для данной HQL-строки
        transaction.commit();
        session.close();
        return list;
    }

    public void update1(String isbn, String author, String book, String publisher, String year) { //все поля меняются по ключу
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Book b = (Book) session.get(Book.class, isbn);
        b.setAuthor(author);
        b.setBookName(book);
        b.setYear(year);
        b.setPublisher(publisher);
        session.update(b);      //Обновляет экземпляр с идентификатором, указанном в аргументе
        transaction.commit();
        session.close();
        System.out.println("Updated\n");
    }

    public void update2(String publisher1, String publisher2){
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Query q = session.createQuery("UPDATE Book SET publisher = :publisher2 WHERE publisher = :publisher1");
        q.setParameter("publisher1", publisher1);
        q.setParameter("publisher2", publisher2);
        q.executeUpdate();
        transaction.commit();
        session.close();
        System.out.println("Updated\n");
    }

    public void update3(String isbn, String publisher){
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Query q = session.createQuery("UPDATE Book SET publisher = :publisher WHERE isbn = :isbn");
        q.setParameter("isbn", isbn);
        q.setParameter("publisher", publisher);
        q.executeUpdate();
        transaction.commit();
        session.close();
        System.out.println("Updated\n");
    }

    public void remove1(String isbn) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Book b = (Book) session.get(Book.class, isbn);  //Возвращает созранённый экземпляр с указанными именем сущности и идентификатором. Если таких сохранённых экземпляров нет – возвращает null.
        session.delete(b);  //удаляет сохраненный объект
        transaction.commit();
        session.close();
        System.out.println("Deleted\n");
    }

    public void remove2(String publ) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Query q = session.createQuery("DELETE FROM Book WHERE publisher = :publ");
        q.setParameter("publ",publ);
        q.executeUpdate();
        transaction.commit();
        session.close();
        System.out.println("Deleted\n");
    }

    public void remove3(String year) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Query q = session.createQuery("DELETE FROM Book WHERE year = :year");
        q.setParameter("year",year);
        q.executeUpdate();
        transaction.commit();
        session.close();
        System.out.println("Deleted\n");
    }

    public static int menu() {
        System.out.println("1 - View all\n2 - Add a record\n3 - Edit a record\n4 - Delete a record\n" +
                "0 - Exit\nYour choice: ");
        int num = scanner.nextInt();
        scanner.nextLine();
        switch (num) {
            case 0: return num;
            case 1:
                return num;
            case 2:
                return num;
            case 3:
                System.out.println("3 - update all fields by ISBN\n4 - update publisher by Publisher\n" +
                        "5 - update year by ISBN+Publisher");
                int num1 = scanner.nextInt();
                scanner.nextLine();
                return num1;
            case 4:
                System.out.println("6 - delete by ISBN\n7 - delete by Publisher\n" +
                        "8 - delete by Year");
                int num2 = scanner.nextInt();
                scanner.nextLine();
                return num2;
        }
    return -1;
    }
}
