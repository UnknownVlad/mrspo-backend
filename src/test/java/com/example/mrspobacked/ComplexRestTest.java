package com.example.mrspobacked;


import com.example.mrspobacked.controllers.dtos.requests.AuthUserRequestDto;
import com.example.mrspobacked.controllers.dtos.requests.RegistrationUserRequestDto;
import com.example.mrspobacked.controllers.dtos.requests.RequestBookDto;
import com.example.mrspobacked.controllers.dtos.responses.AuthUserResponseDto;
import com.example.mrspobacked.controllers.dtos.responses.RegistrationUserResponseDto;
import com.example.mrspobacked.controllers.dtos.responses.ResponseBookDto;
import com.example.mrspobacked.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.example.mrspobacked.ComplexTestSuccessConstants.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class ComplexRestTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;

    @Test
    void complexSuccessTest() {
        HttpHeaders emptyHeader = new HttpHeaders();
        emptyHeader.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RegistrationUserRequestDto> registrationEntity = new HttpEntity<>(VALID_USER, emptyHeader);
        ResponseEntity<RegistrationUserResponseDto> registrationUserResponseDtoResponseEntity = restTemplate.exchange(
                "/api/user/registration",
                HttpMethod.POST,
                registrationEntity,
                RegistrationUserResponseDto.class
        );
        Assertions.assertEquals(HttpStatus.OK, registrationUserResponseDtoResponseEntity.getStatusCode());


        HttpEntity<AuthUserRequestDto> authEntity = new HttpEntity<>(AUTH_USER, emptyHeader);
        ResponseEntity<AuthUserResponseDto> authUserResponseDtoResponseEntity = restTemplate.exchange(
                "/api/user/auth",
                HttpMethod.POST,
                authEntity,
                AuthUserResponseDto.class
        );
        Assertions.assertEquals(HttpStatus.OK, registrationUserResponseDtoResponseEntity.getStatusCode());
        String token = authUserResponseDtoResponseEntity.getBody().getToken();
        System.out.println(token);


        HttpHeaders tokenHeader = new HttpHeaders();
        tokenHeader.setContentType(MediaType.APPLICATION_JSON);
        tokenHeader.set("Authorization", "Bearer " + token);

        HttpEntity<RequestBookDto> addBookEntity = new HttpEntity<>(BOOK_REQUEST, tokenHeader);
        ResponseEntity<ResponseBookDto> addBook = restTemplate.exchange(
                "/api/book/add",
                HttpMethod.POST,
                addBookEntity,
                ResponseBookDto.class
        );

        System.out.println(addBook);



    }


}