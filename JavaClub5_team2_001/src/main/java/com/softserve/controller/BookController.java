package com.softserve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.entity.Book;
import com.softserve.service.BookService;
import com.softserve.exceptions.ResourceNotFoundException;

@Controller
@RequestMapping("/book")
public class BookController {

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @GetMapping("/list")
    public String listBooks(Model theModel) {
        LOG.debug("Show Books handler method");
        List<Book> theBooks = bookService.getBooks();
        theModel.addAttribute("books", theBooks);
        return "list-books";
    }

    @GetMapping("/showForm")
    public String showFormForAdd(Model theModel) {
        LOG.debug("Inside show book-form handler method");
        Book theBook = new Book();
        theModel.addAttribute("book", theBook);
        return "book-form";
    }

    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute("book") Book theBook) {
        LOG.debug("Save Book handler method");
        bookService.saveBook(theBook);
        return "redirect:/book/list";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("bookID") long theId,
                                    Model theModel) throws ResourceNotFoundException {
        LOG.debug("Update Book handler method");
        Book theBook = bookService.getBook(theId);
        theModel.addAttribute("book", theBook);
        return "book-form";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("bookID") long theId) throws ResourceNotFoundException {
        LOG.debug("Delete Book handler method");
        bookService.deleteBook(theId);
        return "redirect:/book/list";
    }

}
