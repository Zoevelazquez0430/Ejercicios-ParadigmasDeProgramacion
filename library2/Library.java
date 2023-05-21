package library2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Library {

  public static String CANNOT_REMOVE_BOOK_NOT_REGISTERED = "cannot remove, book not registered";
  public static String BOOK_NOT_RENTED = "book not rented";
  public static String BOOK_UNAVAILABLE = "book unavailable";
  public static String BOOK_ALREADY_REGISTERED = "book already registered";
  
  
  private List<Book> books;
  private List<Book> rented;

  
  public Library() {
    books = new ArrayList<>();
    rented = new ArrayList<>();
  }

  public void addBook( Book book ) {
    if (libraryContains(book) ) { 
      throw new RuntimeException( BOOK_ALREADY_REGISTERED );
    }
    books.add( book );
  }
  
  public void rentBook( Book book ) {
    if (!books.contains( book )) { 
      throw new RuntimeException( BOOK_UNAVAILABLE );
    }
    rented.add( book );
    books.remove( book );
  }

  public void returnBook( Book book ) {
    if (!rented.contains( book )) { 
      throw new RuntimeException( BOOK_NOT_RENTED );
    }
    books.add( book );
    rented.remove( book );
  }

  public void removeBook( Book book ) {
    if (!libraryContains(book) ) { 
      throw new RuntimeException( CANNOT_REMOVE_BOOK_NOT_REGISTERED ); 
    }
    
    books.remove( book );
    rented.remove( book );
        
  }

  public List<Book> searchAvailableByGenre( String genre ) {
    return filter((each)-> each.getGenre().equals( genre ));
  }

public List<Book> filter(Predicate<? super Book> predicate) {
	return books.stream()
    		.filter(predicate)
    		.collect(Collectors.toList());
}

  public List<Book> searchAvailableByAuthor( String author ) {
	  return filter((each)-> each.getAuthor().equals( author ));
  }

  public int available() {
    return books.size();
  }
  
  //refactors
  public boolean libraryContains(Book book) {
		return books.contains( book ) || rented.contains( book );
	}
}
