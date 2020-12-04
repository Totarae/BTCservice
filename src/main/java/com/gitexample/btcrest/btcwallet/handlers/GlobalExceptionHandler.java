package com.gitexample.btcrest.btcwallet.handlers;

import com.gitexample.btcrest.btcwallet.exceptions.InvalidDateFrameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ValidationException.class)
    public Map<String, Object> ValidationException(ValidationException ex){
        Map<String, Object> map = new HashMap<>();
        map.put("error", ex.getMessage());
        map.put("status", HttpStatus.BAD_REQUEST.value());
        return map;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public Map<String, Object> MessageException(){
        Map<String, Object> map = new HashMap<>();
        map.put("error", "Invalid request. Please, check Date and number format");
        map.put("status", HttpStatus.BAD_REQUEST.value());
        return map;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = InvalidDateFrameException.class)
    public Map<String, Object> InvalidDateFrameException(InvalidDateFrameException ex){
        Map<String, Object> map = new HashMap<>();
        map.put("error", ex.getMsg());
        map.put("status", HttpStatus.BAD_REQUEST.value());
        return map;
    }

}
