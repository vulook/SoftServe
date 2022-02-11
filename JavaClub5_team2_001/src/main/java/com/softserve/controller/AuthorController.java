package com.softserve.controller;

import com.softserve.entity.Author;
import com.softserve.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    private AuthorService authorService;

    @GetMapping("/list")
    public String listAuthors(Model theModel) {
        LOG.debug("Show Author handler method");
        List<Author> theAuthors = authorService.findAll();
        theModel.addAttribute("authors", theAuthors);
        return "list-authors";
    }
//    @GetMapping("/showForm")
//    public String showFormForAdd(Model theModel) {
//        LOG.debug("Inside show book-form handler method");
//        Author theAuthor = new Author();
//        theModel.addAttribute("author", theAuthor);
//        return "author-form";
//    }

    @PostMapping("/saveAuthor")
    public String saveAuthor(@ModelAttribute("author") Author theAuthor) {
        LOG.debug("Save Author handler method");
        authorService.update(theAuthor);
        return "redirect:/author/list";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("authorID") long theId,
                                    Model theModel) {
        LOG.debug("Update Author handler method");
        Author theAuthor = authorService.read(theId);
        theModel.addAttribute("author", theAuthor);
        return "author-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable long id) {
        LOG.debug("Delete Author handler method");
        authorService.delete(id);
        return "redirect:/author/list";
    }

}