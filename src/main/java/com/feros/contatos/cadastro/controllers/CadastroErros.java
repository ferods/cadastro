package com.feros.contatos.cadastro.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.feros.contatos.cadastro.dtos.ErroDTO;

@RestControllerAdvice
public class CadastroErros {
	
	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDTO> handle(MethodArgumentNotValidException ex){		
		List<ErroDTO> dto = new ArrayList<ErroDTO>();
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		fieldErrors.stream().forEach(fe -> {		
		String mensagem = messageSource.getMessage(fe, LocaleContextHolder.getLocale());			
			ErroDTO erro =	new ErroDTO(fe.getField(), mensagem);		
		dto.add(erro);				
		});		
		return dto;		
	}

}
