package com.example.mrspobacked.controllers;

import com.example.mrspobacked.controllers.dtos.RegistrationUserRequestDto;
import com.example.mrspobacked.controllers.dtos.RegistrationUserResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class RegistrationControllerTest {

    @Autowired
    private RegistrationController registrationController;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void registration() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RegistrationUserRequestDto> entity = new HttpEntity<>(Constants.validUser, headers);

        ResponseEntity<RegistrationUserResponseDto> registrationUserResponseDtoResponseEntity = testRestTemplate.exchange(
                "/registration",
                HttpMethod.POST,
                new HttpEntity<>(Constants.validUser, headers),
                RegistrationUserResponseDto.class
        );
        System.out.println(registrationUserResponseDtoResponseEntity);
        Assertions.assertEquals(HttpStatus.OK, registrationUserResponseDtoResponseEntity.getStatusCode());


    }


    private static class Constants {
        static RegistrationUserRequestDto validUser = new RegistrationUserRequestDto("user__1", "12345678");
    }
}