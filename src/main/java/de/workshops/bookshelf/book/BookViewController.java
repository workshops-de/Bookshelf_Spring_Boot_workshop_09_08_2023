package de.workshops.bookshelf.book;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("view")
public class BookViewController {

    private final BookService service;

    BookViewController(BookService service) {
        this.service = service;
    }

    @GetMapping
    String getAllBooks(Model model) {
        final var allBooks = service.getAllBooks();
        model.addAttribute("books", allBooks);

        return "books";
    }
}
