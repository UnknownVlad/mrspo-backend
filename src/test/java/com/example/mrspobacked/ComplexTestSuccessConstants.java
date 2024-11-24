package com.example.mrspobacked;

import com.example.mrspobacked.controllers.dtos.requests.AuthUserRequestDto;
import com.example.mrspobacked.controllers.dtos.requests.RegistrationUserRequestDto;
import com.example.mrspobacked.controllers.dtos.requests.RequestBookDto;

import java.util.List;

public class ComplexTestSuccessConstants {

    public static final RegistrationUserRequestDto NON_VALID_USER = new RegistrationUserRequestDto("q", "42");

    public static final RegistrationUserRequestDto VALID_USER = new RegistrationUserRequestDto("test_user_1", "12345678");
    public static final AuthUserRequestDto AUTH_USER = new AuthUserRequestDto("test_user_1", "12345678");

    public static final RequestBookDto ADD_BOOK_REQUEST = new RequestBookDto("book 1", "description", 40, 12, List.of("Authoe A.A", "Authoe B.B"), List.of("FR1, SF2"), 2.5, true);
    public static final RequestBookDto UPDATE_BOOK_REQUEST = new RequestBookDto("updated book 1", "description 2", 40, 12, List.of("Authoe A.A", "Authoe B.B"), List.of("FR1, SF2"), 4.5, false);

    /*public static final HttpHeaders HEADERS = new HttpHeaders();
    static {
        HEADERS.setContentType(MediaType.APPLICATION_JSON);
    }*/

}
