package com.project.kupuvalnik.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OfferNotFoundException.class)
    public ModelAndView handleOfferExceptions(OfferNotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("404");

        modelAndView.addObject("offerId", exception.getId());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}
