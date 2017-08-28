package fi.haagahelia.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.demo.models.Book;
import fi.haagahelia.demo.repositories.BookRepository;

@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	/*
	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			repository.save(new Book("Examples of Life", "John Doe", 2002, "ABCD123", 12));
			repository.save(new Book("Fine dining", "Jamie Oliver", 2012, "CCFF002", 19.5));
			for (Book b : repository.findAll()) {
				System.out.println(b.toString());
			}
		};
		
	}*/
}
