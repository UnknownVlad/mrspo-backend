package com.example.mrspobacked;


import com.example.mrspobacked.controllers.dtos.common.CommonErrorResponseDto;
import com.example.mrspobacked.controllers.dtos.requests.AuthUserRequestDto;
import com.example.mrspobacked.controllers.dtos.requests.RegistrationUserRequestDto;
import com.example.mrspobacked.controllers.dtos.requests.RequestBookDto;
import com.example.mrspobacked.controllers.dtos.responses.*;
import com.example.mrspobacked.entities.BookEntity;
import com.example.mrspobacked.entities.UserActionEntity;
import com.example.mrspobacked.servises.BookService;
import com.example.mrspobacked.servises.UserActionService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static com.example.mrspobacked.ComplexTestSuccessConstants.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class ComplexRestTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserActionService userActionService;

    @Test
    void complexSuccessTest() {
        long startBookCount = bookService.count();

        HttpHeaders emptyHeader = new HttpHeaders();
        emptyHeader.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RegistrationUserRequestDto> registrationEntity = new HttpEntity<>(VALID_USER, emptyHeader);
        ResponseEntity<RegistrationUserResponseDto> registrationUserResponseDtoResponseEntity = restTemplate.exchange(
                "/api/user/registration",
                HttpMethod.POST,
                registrationEntity,
                RegistrationUserResponseDto.class
        );
        assertEquals(HttpStatus.OK, registrationUserResponseDtoResponseEntity.getStatusCode());


        HttpEntity<AuthUserRequestDto> authEntity = new HttpEntity<>(AUTH_USER, emptyHeader);
        ResponseEntity<AuthUserResponseDto> authUserResponseDtoResponseEntity = restTemplate.exchange(
                "/api/user/auth",
                HttpMethod.POST,
                authEntity,
                AuthUserResponseDto.class
        );
        assertEquals(HttpStatus.OK, registrationUserResponseDtoResponseEntity.getStatusCode());
        assertNotNull(authUserResponseDtoResponseEntity.getBody());
        String token = authUserResponseDtoResponseEntity.getBody().getToken();
        assertNotNull(token);

        HttpHeaders tokenHeader = new HttpHeaders();
        tokenHeader.setContentType(MediaType.APPLICATION_JSON);
        tokenHeader.set("Authorization", "Bearer " + token);

        HttpEntity<?> pageUserEntity = new HttpEntity<>(tokenHeader);
        ResponseEntity<UserPageResponseDto> userPage = restTemplate.exchange(
                "/api/user/page",
                HttpMethod.GET,
                pageUserEntity,
                UserPageResponseDto.class
        );
        assertEquals(HttpStatus.OK, userPage.getStatusCode());
        assertNotNull(userPage.getBody());
        assertNotNull(userPage.getBody().getId());

        HttpEntity<RequestBookDto> addBookEntity = new HttpEntity<>(ADD_BOOK_REQUEST, tokenHeader);
        ResponseEntity<ResponseBookDto> addBook = restTemplate.exchange(
                "/api/book/add",
                HttpMethod.POST,
                addBookEntity,
                ResponseBookDto.class
        );
        assertEquals(HttpStatus.OK, addBook.getStatusCode());
        assertNotNull(addBook.getBody());
        assertNotNull(addBook.getBody().getBook().getId());
        BookEntity bookEntity = bookService.getById(addBook.getBody().getBook().getId());
        assertNotNull(bookEntity);


        HttpEntity<?> getBookByIdEntity = new HttpEntity<>(tokenHeader);
        ResponseEntity<ResponseBookDto> getBookById = restTemplate.exchange(
                "/api/book/get/%d".formatted(bookEntity.getId()),
                HttpMethod.GET,
                getBookByIdEntity,
                ResponseBookDto.class
        );
        assertEquals(HttpStatus.OK, getBookById.getStatusCode());
        assertNotNull(getBookById.getBody());
        assertNotNull(getBookById.getBody().getBook().getId());
        assertEquals(addBook.getBody().getBook().getId(), getBookById.getBody().getBook().getId());

        HttpEntity<RequestBookDto> updateBookEntity = new HttpEntity<>(UPDATE_BOOK_REQUEST, tokenHeader);
        ResponseEntity<ResponseBookDto> updateBook = restTemplate.exchange(
                "/api/book/update/%d".formatted(bookEntity.getId()),
                HttpMethod.PUT,
                updateBookEntity,
                ResponseBookDto.class
        );
        assertEquals(HttpStatus.OK, updateBook.getStatusCode());
        assertNotNull(updateBook.getBody());
        assertNotNull(updateBook.getBody().getBook().getId());

        BookEntity updatedBook = bookService.getById(addBook.getBody().getBook().getId());
        assertNotNull(updatedBook);

        assertEquals(UPDATE_BOOK_REQUEST.getBookName(), updatedBook.getBookName());
        assertEquals(UPDATE_BOOK_REQUEST.getDescription(), updatedBook.getDescription());
        assertEquals(UPDATE_BOOK_REQUEST.getRating(), updatedBook.getRating());
        assertEquals(UPDATE_BOOK_REQUEST.getOnSale(), updatedBook.getOnSale());

        HttpEntity<RequestBookDto> deleteBookEntity = new HttpEntity<>(tokenHeader);
        ResponseEntity<DeleteBookResponseDto> deleteBook = restTemplate.exchange(
                "/api/book/delete/%d".formatted(bookEntity.getId()),
                HttpMethod.DELETE,
                deleteBookEntity,
                DeleteBookResponseDto.class
        );

        assertEquals(HttpStatus.OK, deleteBook.getStatusCode());
        assertNotNull(deleteBook.getBody());
        assertTrue(deleteBook.getBody().getSuccess());

        HttpEntity<RequestBookDto> allBookEntity = new HttpEntity<>(tokenHeader);
        ResponseEntity<AllBookResponseDto> allBook = restTemplate.exchange(
                "/api/book/get/all",
                HttpMethod.GET,
                allBookEntity,
                AllBookResponseDto.class
        );

        assertEquals(HttpStatus.OK, allBook.getStatusCode());
        assertNotNull(allBook.getBody());
        assertNotNull(allBook.getBody().getBooks());
        assertEquals(startBookCount, allBook.getBody().getBooks().size());


        List<UserActionEntity> userActionEntities = userActionService.getUserActions(userPage.getBody().getId());

        assertEquals(5, userActionEntities.size());

        Optional<UserActionEntity> addBookOptionalEntity = userActionEntities.stream()
                .filter(userActionEntity -> userActionEntity.getRequestType().equals("POST"))
                .findFirst();
        assertTrue(addBookOptionalEntity.isPresent());

        Optional<UserActionEntity> getBookOptionalEntity = userActionEntities.stream()
                .filter(userActionEntity -> userActionEntity.getRequestType().equals("GET") && userActionEntity.getMethodName().equals("BookController#getBookById"))
                .findFirst();
        assertTrue(getBookOptionalEntity.isPresent());

        Optional<UserActionEntity> getAllBookOptionalEntity = userActionEntities.stream()
                .filter(userActionEntity -> userActionEntity.getRequestType().equals("GET") && userActionEntity.getMethodName().equals("BookController#getAllBooks"))
                .findFirst();
        assertTrue(getAllBookOptionalEntity.isPresent());

        Optional<UserActionEntity> updateBookOptionalEntity = userActionEntities.stream()
                .filter(userActionEntity -> userActionEntity.getRequestType().equals("PUT"))
                .findFirst();
        assertTrue(updateBookOptionalEntity.isPresent());

        Optional<UserActionEntity> deleteBookOptionalEntity = userActionEntities.stream()
                .filter(userActionEntity -> userActionEntity.getRequestType().equals("DELETE"))
                .findFirst();
        assertTrue(deleteBookOptionalEntity.isPresent());

        HttpEntity<?> actionsUserEntity = new HttpEntity<>(tokenHeader);
        ResponseEntity<UserActionsResponseDto> actionsUser = restTemplate.exchange(
                "/api/user/actions",
                HttpMethod.GET,
                actionsUserEntity,
                UserActionsResponseDto.class
        );
        assertEquals(HttpStatus.OK, actionsUser.getStatusCode());
        assertNotNull(actionsUser.getBody());
        assertNotNull(actionsUser.getBody().getActions());
        assertEquals(5, actionsUser.getBody().getActions().size());
    }

    @Disabled
    @Test
    void validationErrorTest() {


        HttpHeaders emptyHeader = new HttpHeaders();
        emptyHeader.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RegistrationUserRequestDto> registrationEntity = new HttpEntity<>(NON_VALID_USER, emptyHeader);
        ResponseEntity<CommonErrorResponseDto> registrationUserResponseDtoResponseEntity = restTemplate.exchange(
                "/api/user/registration",
                HttpMethod.POST,
                registrationEntity,
                CommonErrorResponseDto.class
        );

        System.out.println(registrationUserResponseDtoResponseEntity);
    }

}