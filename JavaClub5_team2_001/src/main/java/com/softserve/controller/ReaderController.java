package com.softserve.controller;

import com.softserve.service.Implementation.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.softserve.entity.Book;
import com.softserve.entity.User;
import com.softserve.service.UserService;

@Controller
public class ReaderController {

    private static final Logger LOG = LoggerFactory.getLogger(ReaderController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/readers")
    public String listReaders(Model theModel) {
        LOG.debug("Show Books handler method");
        List<User> theReaders = userService.getReaders();
        theModel.addAttribute("readers", theReaders);
        return "list-reader";
    }

    @GetMapping("/readers/debtors")
    public String listDebtors(Model theModel) {
        LOG.debug("Show Books handler method");
        List<User> theReaders = userService.getDebtors();
        theModel.addAttribute("readers", theReaders);
        return "list-debtors";
    }

    @GetMapping("/readers/{id}")
    public String list(@PathVariable long id, Model theModel) {
        LOG.debug("Show Books handler method");
        List<Book> theBooks = userService.getStatByReader("reading", id);
        List<Book> theBooks1 = userService.getStatByReader("read", id);
        Integer time = userService.timeWithLibrary(id);
        theModel.addAttribute("time", time);
        theModel.addAttribute("reading", theBooks);
        theModel.addAttribute("books", theBooks1);
        return "reader-book";
    }

    @GetMapping("/readers/stat")
    public String stat(Model theModel) {
        LOG.debug("Show Books handler method");
        List<String> theReaders = userService.getStat();
        theModel.addAttribute("readers", theReaders);
        return "library-info";
    }

    @GetMapping("/readers/sendMail")
    public String showEmailMessage(@RequestParam("ReaderID") long theId,
                                   Model theModel) {
        LOG.debug("Update Book handler method");
        String email = userService.getReaders().stream().filter(x -> x.getId() == theId).findFirst().orElseThrow().getEmail();
        theModel.addAttribute("emails", email);

        return "email-form";
    }

    @GetMapping("/readers/sendToAll")
    public String showEmail(@RequestParam("Debtors") String debtors, Model theModel) {
        LOG.debug("SendToAll handler method");
        String[] emails;
        if (debtors.equals("true")) {
            emails = userService.getDebtors().stream().map(User::getEmail).toArray(String[]::new);
        } else {
            emails = userService.getReaders().stream().map(User::getEmail).toArray(String[]::new);
        }
        StringBuilder sb = new StringBuilder();
        for (String str : emails) {
            sb.append(str).append(" ");
        }
        sb.deleteCharAt(sb.lastIndexOf(" "));
        theModel.addAttribute("emails", sb);
        return "email-form";
    }

    @GetMapping("/readers/send")
    public String send(@RequestParam("emails") String emails, @RequestParam("subject") String subject, @RequestParam("message") String message, Model theModel) {
        LOG.debug("Update Book handler method");
        MailService mailService = new MailService();
        try {
            String[] emailArr = emails.split(" ");
            mailService.sendEmail(emailArr, subject, message);
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
        return "redirect:/book/list";
    }

}