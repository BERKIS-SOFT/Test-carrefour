package com.carrefour.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.carrefour.exceptions.NoContent;
import com.carrefour.exceptions.NotFound;



@RestControllerAdvice
public class Advice {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}

    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(NoContent.class)
    protected Exception noContent() {
        Exception exception = new Exception();
        exception.setStatus(204);
        exception.setError("No CONTENT DATA");
        exception.setMessage("Empty data");
        return exception;
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    protected Exception notFound01() {
        return shareNotFound();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFound.class)
    protected Exception notFound02() {
        return shareNotFound();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(java.lang.Exception.class)
    protected Exception serverError()   {
        Exception exception = new Exception();
        exception.setStatus(500);
        exception.setError("Internal server error");
        exception.setMessage("Internal server error");
        return exception;
    }
    
   

    protected Exception shareNotFound() {
        Exception exception = new Exception();
        exception.setStatus(404);
        exception.setError("Not found");
        exception.setMessage("Not found path");
        return exception;
    }
    
}