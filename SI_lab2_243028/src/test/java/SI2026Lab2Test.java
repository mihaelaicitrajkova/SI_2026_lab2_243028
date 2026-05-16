import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class SI2026Lab2Test {

    @Test
    void searchBookEveryStatementTest() {
        Library library = new Library();
        library.addBook(new Book("Clean Code", "Robert C. Martin", "Programming"));
        library.addBook(new Book("Effective Java", "Joshua Bloch", "Programming"));

        //Test case 1 title=""
        assertThrows(IllegalArgumentException.class, () ->{
           library.searchBookByTitle("");
        });

        //Test case 2 title="Clean Code"
        List<Book>books=library.searchBookByTitle("Clean Code");
        assertNotNull(books);

        //Test case 3 title="Anna Karenina"
        assertNull(library.searchBookByTitle("Anna Karenina"));
    }

    @Test
    void borrowBookEveryBranchTest(){
        Library library = new Library();
        Book book1= new Book("Clean Code", "Robert C. Martin", "Programming");
        Book book2= new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy");
        Book book3= new Book("1984", "George Orwell", "Dystopian");
        book2.setBorrowed(true);
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        //Test case 1 title = "" author = ""
        assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("", "");
        });
        //Test case 2 title = "The Hobbit" author = "J.R.R. Tolkien"
        assertThrows(RuntimeException.class, () -> {
            library.borrowBook("The Hobbit", "J.R.R. Tolkien");
        });

        //Test case 3 title = "Anna Karenina" author = "Lev Tolstoy"
        assertThrows(RuntimeException.class, () -> {
            library.borrowBook("Anna Karenina", "Lev Tolstoy");
        });

        //Test case 4 title = "1984", author = "George Orwell"
        library.borrowBook("1984", "George Orwell");
        assertTrue(book3.isBorrowed());
    }


    @Test
    void searchBookMultipleConditionTest(){
        Library library = new Library();
        //if (book.getTitle().equalsIgnoreCase(title) && !book.isBorrowed())

        //T && T
            Book book1= new Book("Clean Code", "Robert C. Martin", "Programming");
        //T && F
        Book book2= new Book("Clean Code", "Robert C. Martin", "Programming");
        book2.setBorrowed(true);
        //F && neshto
        Book book3=new Book("1984", "George Orwell", "Dystopian");

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        List<Book>results=library.searchBookByTitle("Clean Code");
        assertNotNull(results);
        assertEquals(1,results.size());
        assertEquals("Clean Code",results.get(0).getTitle());
    }


    @Test
    void borrowBookMultipleConditionTest(){
        Library library=new Library();
        //if (title.isEmpty() || author.isEmpty())

        //T || neshto
        assertThrows(IllegalArgumentException.class, () ->{
            library.borrowBook("", "neshto");
        });

        //F || F
        assertThrows(IllegalArgumentException.class, () ->{
            library.borrowBook("neshto", "");
        });

        //F || T
        assertThrows(RuntimeException.class, () ->{
            library.borrowBook("neshto", "neshto");
        });
    }
}


