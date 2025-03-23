package com.songify.infrastructure.apivalidation;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
class ApiValidationErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiValidationErrorResponseDto> handleValidationException(MethodArgumentNotValidException exception) {
        List<String> messages = getErrorsFromException(exception);
        ApiValidationErrorResponseDto response = new ApiValidationErrorResponseDto(messages, BAD_REQUEST);
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    private List<String> getErrorsFromException(MethodArgumentNotValidException exception) {
        return exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    }
}
