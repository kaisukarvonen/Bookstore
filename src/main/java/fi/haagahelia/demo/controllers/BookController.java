package fi.haagahelia.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.demo.models.Book;
import fi.haagahelia.demo.repositories.BookRepository;

@Controller
public class BookController {
	
	@Autowired
	BookRepository bookrepo;
	
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
    @RequestMapping(value="/books", method=RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) bookrepo.findAll();
    }    
    
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Book findBookById(@PathVariable("id") Long id) {	
    	return bookrepo.findOne(id);
    }    
    
    
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
	
	@RequestMapping(value="/delete/{id}")
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		bookrepo.delete(id);
		System.out.println("here");
		return "redirect:../booklist";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addPage(Model model) {
		model.addAttribute("book", new Book());
		return "addbook";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addBook(Book book) {
		bookrepo.save(book);
		return "redirect:booklist";
	}
	
	
	
	

}
