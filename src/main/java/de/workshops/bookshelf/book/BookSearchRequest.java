package de.workshops.bookshelf.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BookSearchRequest(@NotBlank String author, @Size(min = 10, max = 14) String isbn) {}
