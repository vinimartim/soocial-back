package com.example.demo.exceptionhandler;

import com.example.demo.exception.RegradeNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(RegradeNegocioException.class)
    public ResponseEntity<Object> handleNegocio(RegradeNegocioException ex, WebRequest request) {

        var status = HttpStatus.BAD_REQUEST;
        var corpoException = new CorpoException();
        corpoException.setStatus(status.value());
        corpoException.setMensagem(ex.getMessage());
        corpoException.setDataHora(LocalDateTime.now());

        return handleExceptionInternal(ex,corpoException,new HttpHeaders(),status,request);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        var campos = new ArrayList<CorpoException.Campo>();

        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nome = ((FieldError) error).getField();
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            campos.add(new CorpoException.Campo(nome, mensagem));
        }

        var corpoException = new CorpoException();
        corpoException.setStatus(status.value());
        corpoException.setMensagem("Um ou mais campos estão inválidos");
        corpoException.setDataHora(LocalDateTime.now());
        corpoException.setCampo(campos);

        return super.handleExceptionInternal(ex, corpoException, headers, status, request);
    }
}
