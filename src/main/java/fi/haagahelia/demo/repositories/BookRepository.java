package fi.haagahelia.demo.repositories;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import fi.haagahelia.demo.models.Book;


public interface BookRepository extends CrudRepository<Book, Long>{
	
	List<Book> findByAuthor(String author);
	
	@Override
	List<Book> findAll();

}
