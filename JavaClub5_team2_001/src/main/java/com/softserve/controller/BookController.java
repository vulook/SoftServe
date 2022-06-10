package com.softserve.controller;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.entity.Book;
import com.softserve.service.BookService;
import com.softserve.service.UserService;

@Controller
public class BookController {

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    public static Long id;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    //shows all books to admin
    @GetMapping("/book/list")
    public String listBooks(Model theModel) {
        LOG.debug("Show Books handler method");
        List<Book> theBooks = bookService.findAll();
        theModel.addAttribute("books", theBooks);
        return "list-books";
    }

    //shows to all readers
    @RequestMapping("/books")
    public String show(Model theModel) {
        LOG.debug("Show Books handler method");
        List<Book> theBooks = bookService.findAll();
        theModel.addAttribute("books", theBooks);
        return "all-books";
    }

    //shows reader own books
    @RequestMapping("/books/my")
    public String showMy(Model theModel) {
        LOG.debug("Show Books handler method");
        List<Book> theBooks = bookService.findBookByUser("reading", userService.getId());
        List<Book> theBooks1 = bookService.findBookByUser("read", userService.getId());
        List<Integer> timeList = bookService.findTime(userService.getId());
        Map<Book, Integer> timeandbook = new HashMap<>();
        for (int i = 0; i < theBooks1.size(); i++) {
            timeandbook.put(theBooks1.get(i), timeList.get(i));
        }
        theModel.addAttribute("reading", theBooks);
        theModel.addAttribute("books", timeandbook);
        return "user-books";
    }

    //shows add book form
    @GetMapping("/book/showForm")
    public String showFormForAdd(Model theModel) {
        LOG.debug("Inside show book-form handler method");
        Book theBook = new Book();
        theModel.addAttribute("book", theBook);
        return "book-form";
    }

    // saves or creates new book from form
    @PostMapping("/book/saveBook")
    public String saveBook(@ModelAttribute("book") Book theBook) {
        LOG.debug("Save Book handler method");
        bookService.create(theBook);
        return "redirect:/book/list";
    }

    // shows detail book information for reader
    @GetMapping("books/info")
    public String showBookInfo(@RequestParam("bookID") long theId, Model theModel) {
        LOG.debug("Update Book handler method");
        Book theBook = bookService.findByID(theId);
        theModel.addAttribute("book", theBook);
        return "book-info";
    }

    //show form for update book
    @GetMapping("/book/updateForm")
    public String showFormForUpdate(@RequestParam("bookID") long theId, Model theModel) {
        LOG.debug("Update Book handler method");
        Book theBook = bookService.findByID(theId);
        theModel.addAttribute("book", theBook);
        return "book-form";
    }

    //shows book filter to reader
    @GetMapping("/books/filter")
    public String showFilters(Model theModel) {
        LOG.debug("Update Book handler method");
        String bookSearch = "";
        String authorSearch = "";
        theModel.addAttribute("name", bookSearch);
        theModel.addAttribute("author", authorSearch);
        return "filter";
    }

    // deletes all book copies by admin
    @GetMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable long id) {
        LOG.debug("Delete Book handler method");
        bookService.delete(id);
        return "redirect:/book/list";
    }

    // deletes one book by admin
    @GetMapping("/book/delete-copy/{id}")
    public String deleteOneBook(@PathVariable long id) {
        LOG.debug("Delete Book handler method");
        bookService.deleteCopy(id);
        return "redirect:/book/list";
    }

    //filters book
    @GetMapping("/books/showBooks")
    public String applyFilter(@RequestParam("bookName") String bookName,
                              @RequestParam("Author") String author,
                              @RequestParam("popular") String popular,
                              @RequestParam("start") String start,
                              @RequestParam("end") String end,
                              @RequestParam("available") String available, Model theModel) {

        LOG.debug("Show Books handler method");
        List<Book> allFilters = new ArrayList<>();
        if (popular.equals("nothing")) {
            List<Book> nameFilter = bookService.findBookByName(bookName);
            List<Book> authorFilter = bookService.findBookByAuthor(author);
            for (Book b : nameFilter) {
                for (Book book : authorFilter) {
                    if (b.equals(book)) allFilters.add(book);
                }
            }

        } else if (popular.equals("popular")) {
            LocalDate startDate = LocalDate.parse(start);
            LocalDate endDate = LocalDate.parse(end);
            allFilters = bookService.findPopular(startDate, endDate);

        } else {
            LocalDate startDate = LocalDate.parse(start);
            LocalDate endDate = LocalDate.parse(end);
            allFilters = bookService.findUnpopular(startDate, endDate);
        }

        if (available.equals("true")) {
            allFilters = allFilters.stream().filter(x -> x.getCount() > 0).collect(Collectors.toList());
        }
        theModel.addAttribute("books", allFilters);
        return "all-books";
    }

    // show books statistics for admin
    @GetMapping("/book/stat")
    public String showFormForStat(Model theModel) {
        LOG.debug("Update Book handler method");
        Map<Book, List<String>> book = new LinkedHashMap<>();
        List<Book> books = bookService.findAll();
        List<String> all = new ArrayList<>();
        List<String> authors = bookService.getAuthors();
        List<Integer> count = bookService.getCount();
        List<Double> duration = bookService.getDuration();

        for (int i = 0; i < books.size(); i++) {
            String str = authors.get(i) + ";" + count.get(i).toString() + ";" + duration.get(i).toString();
            List<String> temp = new ArrayList<>();
            temp.add(authors.get(i));
            temp.add(count.get(i).toString());
            temp.add(duration.get(i).toString());
            temp.add(String.valueOf(books.get(i).getCount() - count.get(i)));
            book.put(books.get(i), temp);
        }
        theModel.addAttribute("book", book);
        return "stat-book";
    }

    //shows detail book information to admin
    @GetMapping("book/info")
    public String showInfo(@RequestParam("bookID") long theId, Model theModel) {
        LOG.debug("Update Book handler method");
        Book theBook = bookService.findByID(theId);
        theModel.addAttribute("book", theBook);
        return "admin-book-info";
    }

}