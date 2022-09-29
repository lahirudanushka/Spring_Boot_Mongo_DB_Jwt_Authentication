package com.demo.jwtdemo.util;


import com.demo.jwtdemo.dto.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.security.core.AuthenticationException;


@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResult> accessDeniedExceptionHandler(AccessDeniedException ex) {
        ErrorResult result = new ErrorResult();
        result.setCode("401");
        result.setReason("Access Denied");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

    @ExceptionHandler({AuthenticationException.class})
    @ResponseBody
    public ResponseEntity<ErrorResult> authenticationException(AuthenticationException ex) {
        ErrorResult result = new ErrorResult();
        result.setCode("401");
        result.setReason("Please use a valid authorization token");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResult> customExceptionHandler(Exception ex) {
        ErrorResult result = new ErrorResult();
        result.setCode("500");
        result.setReason("Internal Server Error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }

    @ExceptionHandler(BaseException.class)
    public  ResponseEntity<ErrorResult> customBaseExceptionHandler(BaseException ex) {
        ErrorResult result = new ErrorResult();
        result.setCode(ex.getResultCode());
        result.setReason(ex.getReason());
        return ResponseEntity.status(ex.getHttpStatus()).body(result);
    }


}
