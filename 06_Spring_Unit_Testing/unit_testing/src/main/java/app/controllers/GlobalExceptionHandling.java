package app.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling
{
    @ExceptionHandler(Exception.class)
    public String handleGlobalExceptions()
    {
        return "errors/custom-error";
    }
}
