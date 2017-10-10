package fi.haagahelia.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;

import fi.haagahelia.demo.models.Book;
import fi.haagahelia.demo.models.User;
import fi.haagahelia.demo.repositories.BookRepository;
import fi.haagahelia.demo.repositories.UserRepository;

@SpringBootApplication
public class BookstoreApplication {


	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository bookrepo, UserRepository userrepo) {
		return (args) -> {
			bookrepo.save(new Book("Examples of Life", "John Doe", 2002, "ABCD123", 12));
			bookrepo.save(new Book("Fine dining", "Jamie Oliver", 2012, "CCFF002", 19.5));
			bookrepo.save(new Book("This is great", "Someone Awesome", 2010, "CCFF037", 19.5));
			bookrepo.save(new Book("Life is living", "Jamie Oliver", 2008, "CCFF002", 22));
			bookrepo.save(new Book("This is stupid", "This Dude", 2010, "CCFF037", 19.5));
			userrepo.save(new User("user1", BCrypt.hashpw("userpw", BCrypt.gensalt()),"USER"));
			userrepo.save(new User("admin1", BCrypt.hashpw("adminpw", BCrypt.gensalt()),"ADMIN"));
			userrepo.save(new User("admin2", BCrypt.hashpw("adminpw", BCrypt.gensalt()),"ADMIN"));
			
		};
		
	}
}
