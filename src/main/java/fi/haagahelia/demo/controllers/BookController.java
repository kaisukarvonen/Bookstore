package fi.haagahelia.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fi.haagahelia.demo.models.Book;
import fi.haagahelia.demo.repositories.BookRepository;

@Controller
public class BookController {
	
	@Autowired
	BookRepository bookrepo;
	
	@RequestMapping(value="/index")
	public String index() {
		bookrepo.save(new Book("Examples of Life", "John Doe", 2002, "ABCD123", 12));
		bookrepo.save(new Book("Fine dining", "Jamie Oliver", 2012, "CCFF002", 19.5));
		List<Book> books = bookrepo.findAll();
		System.out.println("list has "+books.size()+" items");
		for (Book b : bookrepo.findAll()) {
			System.out.println(b.toString());
		}
		return "index";
	}

}
