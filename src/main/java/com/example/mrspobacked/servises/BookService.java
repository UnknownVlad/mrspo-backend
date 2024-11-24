package com.example.mrspobacked.servises;

import com.example.mrspobacked.controllers.dtos.requests.RequestBookDto;
import com.example.mrspobacked.entities.BookEntity;
import com.example.mrspobacked.exceptions.BookNotFoundException;
import com.example.mrspobacked.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public long count() {
        return bookRepository.count();
    }

    public BookEntity getById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Книга с id: %d не найден".formatted(id)));
    }

    public List<BookEntity> getByBookNameLike(String bookName){
        return bookRepository.findAllByBookNameLike(bookName);
    }

    public List<BookEntity> getAll() {
        return bookRepository.findAll();
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public BookEntity update(Long id, RequestBookDto bookDto) {
        BookEntity bookEntity = getById(id);

        bookEntity.setBookName(bookDto.getBookName());
        bookEntity.setDescription(bookDto.getDescription());
        bookEntity.setPageCount(bookDto.getPageCount());
        bookEntity.setCirculation(bookDto.getCirculation());
        bookEntity.setAuthors(String.join("::", bookDto.getAuthors()));
        bookEntity.setGenres(String.join("::", bookDto.getGenres()));
        bookEntity.setRating(bookDto.getRating());
        bookEntity.setOnSale(bookDto.getOnSale());

        return save(bookEntity);
    }

    public BookEntity save(BookEntity ingredientEntity) {
        return bookRepository.save(ingredientEntity);
    }
}
