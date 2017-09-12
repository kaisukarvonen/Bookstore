package fi.haagahelia.demo.repositories;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fi.haagahelia.demo.models.Book;

@RepositoryRestResource(collectionResourceRel = "books2", path = "books2")
public interface BookRepository extends PagingAndSortingRepository<Book, Long>{
	
	List<Book> findByAuthor(@Param("author") String author);
	
	@Override
	List<Book> findAll();
	

}
