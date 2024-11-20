package com.example.mrspobacked.mappers;

import com.example.mrspobacked.controllers.dtos.requests.RequestBookDto;

import com.example.mrspobacked.controllers.dtos.responses.ResponseBookDto;
import com.example.mrspobacked.entities.BookEntity;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {


    public BookEntity toEntity(RequestBookDto requestBookDto) {
        return BookEntity.builder()
                .bookName(requestBookDto.getBookName())
                .description(requestBookDto.getDescription())
                .pageCount(requestBookDto.getPageCount())
                .circulation(requestBookDto.getCirculation())
                .authors(String.join("::", requestBookDto.getAuthors()))
                .genres(String.join("::", requestBookDto.getGenres()))
                .rating(requestBookDto.getRating())
                .onSale(requestBookDto.getOnSale())
                .build();
    }
    public ResponseBookDto toDto(BookEntity bookDto) {
        return ResponseBookDto.builder()
                .id(bookDto.getId())
                .bookName(bookDto.getBookName())
                .description(bookDto.getDescription())
                .pageCount(bookDto.getPageCount())
                .circulation(bookDto.getCirculation())
                .authors(bookDto.getAuthors())
                .genres(bookDto.getGenres())
                .rating(bookDto.getRating())
                .onSale(bookDto.getOnSale())
                .build();
    }

}
