package de.workshops.bookshelf.book;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface BookJpaRepository extends ListCrudRepository<Book, Long> {
    Book findByIsbn(String isbn);

    Book findByAuthorContaining(String author);

    @Query("select b from Book b where b.isbn=?1 or b.author like ?2")
    List<Book> searchBook(String isbn, String author);

    List<Book> findByIsbnOrAuthorContaining(String isbn, String author);
}
