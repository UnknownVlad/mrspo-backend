package com.example.mrspobacked.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "books")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookEntity extends BaseEntity{
    private static final String AUTHORS_DELIMITER = "::";
    private static final String GENRE_DELIMITER = "::";

    String bookName;
    String description;
    Integer pageCount;
    Integer circulation;
    String authors;
    String genres;
    Double rating;
    Boolean onSale;

    @Transient
    public List<String> getGenres(){
        return Arrays.stream(this.genres.split(GENRE_DELIMITER))
                .toList();
    }

    @Transient
    public List<String> getAuthors(){
        return Arrays.stream(this.authors.split(AUTHORS_DELIMITER))
                .toList();
    }

}
