package org.info.blog.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.log4j.Log4j2;
import org.info.blog.dto.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
@Log4j2
public class BlogExceptionHandler {

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ExceptionResponseDto> expiredJwtToken(ExpiredJwtException expiredJwtException) {
        Date expiryDate = expiredJwtException.getClaims().getExpiration();

        log.error("Handling ExpiredJwtException");
        return new ResponseEntity<>(new ExceptionResponseDto("Token Expired" + expiryDate), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<ExceptionResponseDto> userExists(UserExistsException userExistsException) {
        log.error("User Exists");
        return new ResponseEntity<>(new ExceptionResponseDto("User Already Exists"), HttpStatus.CONFLICT);
    }


    //custom
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ExceptionResponseDto> handleAuthenticationException(AuthenticationException ex) {
        log.error("Forbidden");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionResponseDto("Forbidden From Request"));

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto> generalException() {
        return new ResponseEntity<>(new ExceptionResponseDto("General exception"), HttpStatus.EXPECTATION_FAILED);
    }


}
