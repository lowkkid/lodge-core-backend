package com.github.lowkkid.lodgecore.common.exception;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    private static final String ARGUMENT_VALIDATION_FAILED = "Argument validation failed.";


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<String> handleAlreadyExistsException(AlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleInvalidInputException(final IllegalArgumentException e) {
        logException("Invalid input error", e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<String> handle(MethodArgumentNotValidException ex) {
        logException("Method arguments are not valid", ex);
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ARGUMENT_VALIDATION_FAILED + " " + allErrors.get(0).getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        log.error(INTERNAL_SERVER_ERROR, ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(INTERNAL_SERVER_ERROR);
    }

    private void logException(String logMessage, Exception ex) {
        StackTraceElement[] stackTrace = ex.getStackTrace();
        String location = "unknown";

        if (stackTrace.length > 0) {
            StackTraceElement firstElement = stackTrace[0];
            location = String.format("%s.%s:%d",
                    firstElement.getClassName(),
                    firstElement.getMethodName(),
                    firstElement.getLineNumber());
        }

        if (logMessage != null) {
            log.warn("{}: {} at {}", logMessage, ex.getMessage(), location);
        } else {
            log.warn("{} at {}", ex.getMessage(), location);
        }
    }
}

