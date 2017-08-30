package fi.haagahelia.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		List<Book> books = bookrepo.findAll(); 
		System.out.println("list has "+books.size()+" items");
		for (Book b : bookrepo.findAll()) {
			System.out.println(b.toString());
		}
		return "index";
	}
	
	@RequestMapping(value="/booklist")
	public String booklist(Model model) {
		List<Book> books = bookrepo.findAll(); 
		model.addAttribute("books", books);
		return "booklist";
	}

}
