package com.globallogic.dogo.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.globallogic.dogo.representation.ResponseRepresentation;
import com.globallogic.dogo.services.exception.ResourceNotFoundException;

@ControllerAdvice
public class AdviceHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody
	public ResponseRepresentation handle(ResourceNotFoundException ex){
		return new ResponseRepresentation(ex.getMessage());
	} 

}
