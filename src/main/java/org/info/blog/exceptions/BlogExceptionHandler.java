package org.info.blog.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import org.info.blog.dto.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class BlogExceptionHandler {

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ExceptionResponseDto> expiredJwtToken(ExpiredJwtException expiredJwtException){
        return new ResponseEntity<>(new ExceptionResponseDto("Token Expired"), HttpStatus.UNAUTHORIZED);
    }
}
