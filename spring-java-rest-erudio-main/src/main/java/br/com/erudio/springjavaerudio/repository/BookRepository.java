package br.com.erudio.springjavaerudio.repository;

import br.com.erudio.springjavaerudio.model.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
