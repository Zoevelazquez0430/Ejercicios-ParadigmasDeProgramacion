package library2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class LibraryTest {

  private static String SI_FY = "SiFy";
  private static String ANONIMOUS = "Anonimous";
  private static String PYTHON_FOR_DUMMIES = "pythonForDummies";
  private static String CHAMOND_LIU = "Chamond Liu";
  private static String SI_FI = "SiFi";
  private static String TOLKIEN_UNIVERSE = "TolkienUniverse";
  private static String JAVA4_DUMMIES = "Java4Dummies";
  private static String IT = "IT";
  private static String MARTIN_FOWLER = "Martin Fowler";

@Test public void testNewLibraryHasNoAuthors() {
    assertTrue( library().searchAvailableByAuthor( ANONIMOUS ).isEmpty() );
  }

  @Test public void testNewLibraryHasNoGenres() {
    assertTrue( library().searchAvailableByGenre( SI_FY ).isEmpty() );
  }

  @Test public void testNewLibraryHasNoStock() {
    assertEquals( 0, library().available() );
  }

  @Test public void testLibraryWhitABookHasStock() {
    assertEquals( 1, addBook(java4dummies()).available() );
  }

  @Test public void testLibraryWhitABookFindsItsGenre() {
    assertTrue( addBook(java4dummies()).searchAvailableByGenre( java4dummies().getGenre() ).contains( java4dummies() ) );
  }

  @Test public void testLibraryWhitABookFindsItsAuthor() {
    assertTrue( addBook(java4dummies()).searchAvailableByAuthor( java4dummies().getAuthor() ).contains( java4dummies() ) );
  }

  @Test public void testLibraryWhitABookRemoved() {
    Library library = addBook(java4dummies());
    assertLibrarySize(1,library);
    library.removeBook( java4dummies() );
    assertLibrarySize(0,library);
  }
  
  @Test public void testLibraryWhitNoBooktoRemove() {
    assertThrowsLike(Library.CANNOT_REMOVE_BOOK_NOT_REGISTERED, ()->library().removeBook( java4dummies() ));
    assertLibrarySize(0,library());
  }
  
  @Test public void testLibraryWhitTwiceABook() {
	assertThrowsLike(Library.BOOK_ALREADY_REGISTERED, ()->addBook(java4dummies()).addBook( java4dummies() ));
  }

  @Test public void testFiltersAuthorsOnLibrary() {
    assertLibrarySize(3,fullLibrary());
    assertTrue( searchAvailabilityByMartinFowler(fullLibrary(), java4dummies()) );
    assertTrue( searchAvailabilityByMartinFowler(fullLibrary(), tolkienUniverse()) );
    assertFalse( searchAvailabilityByMartinFowler(fullLibrary(), python4Dummies()) );
  }

  @Test public void testFiltersGenreOnLibrary() {
    assertLibrarySize(3,fullLibrary());
    assertTrue( searchAvailabilityByGenreIT(fullLibrary(),java4dummies()) );
    assertTrue( searchAvailabilityByGenreIT(fullLibrary(),python4Dummies()) );
    assertFalse( searchAvailabilityByGenreIT(fullLibrary(),tolkienUniverse()) );
  }

  // nuevo feature, alquiler de libros!
  
  @Test public void testSucessfulRent() {
	assertLibrarySize(0,addAndRentBook(java4dummies()));
  }

  @Test public void testUnexistentRent() {
	assertThrowsLike(Library.BOOK_UNAVAILABLE, ()->library().rentBook( java4dummies() ));
  }

  @Test public void testRentTwice() {
	assertThrowsLike(Library.BOOK_UNAVAILABLE, ()->addAndRentBook(java4dummies()).rentBook( java4dummies() ));
  }

  @Test public void testRestoreRented() {
    Library library = addAndRentBook(java4dummies());
    library.returnBook( java4dummies() );
    assertLibrarySize(1,library);
  }

  @Test public void testRestoreUnrented() {
	//Library library = addBook(java4dummies());
	assertThrowsLike(Library.BOOK_NOT_RENTED, ()->addBook(java4dummies()).returnBook( java4dummies() ));
    assertLibrarySize(1,addBook(java4dummies()));
  }

  @Test public void testLibraryWhitARentedBookRemoved() {
	Library library= addAndRentBook(java4dummies());
	library.removeBook(java4dummies());
	assertThrowsLike(Library.BOOK_NOT_RENTED, ()->library.returnBook( java4dummies() )); 
  }


  //refactors

public Book java4dummies() {
	return new Book( MARTIN_FOWLER, JAVA4_DUMMIES, IT );
}
public Book tolkienUniverse() {
	return new Book( MARTIN_FOWLER, TOLKIEN_UNIVERSE, SI_FI );
}

public Book python4Dummies() {
	return new Book( CHAMOND_LIU, PYTHON_FOR_DUMMIES, IT );
}

public Library addBook(Book book) {
	Library library = library();
    library.addBook( book );
	return library;
}

private Library library() {
	return new Library();
}

public Library fullLibrary() {
	Library library = addBook(java4dummies());
    library.addBook( python4Dummies() );
    library.addBook( tolkienUniverse() );
	return library;
}

public Library addAndRentBook(Book book) {
	Library library = library();
    library.addBook( book );

    library.rentBook( book );
	return library;
}

private void assertThrowsLike(String msg, Executable codeToRun) {
	assertEquals(msg, assertThrows(Exception.class, codeToRun).getMessage());
	}

public void assertLibrarySize(int size, Library library) {
	assertEquals( size, library.available() );
}

public boolean searchAvailabilityByMartinFowler(Library library, Book book) {
	return library.searchAvailableByAuthor( MARTIN_FOWLER ).contains( book );
}

public boolean searchAvailabilityByGenreIT(Library library, Book book) {
	return library.searchAvailableByGenre( IT ).contains( book );
}
}
