package com.gdky.restfull.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gdky.restfull.configuration.ApiErrors;
import com.gdky.restfull.entity.ResponseMessage;
import com.gdky.restfull.exception.InvalidRequestException;
import com.gdky.restfull.exception.ResourceNotFoundException;

/**
 * Called when an exception occurs during request processing. Transforms exception message into JSON format.
 */
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    @Autowired
    private MessageSource messageSource;



    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseBody
    public ResponseEntity<ResponseMessage> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        if (log.isDebugEnabled()) {
            log.debug("handling ResourceNotFoundException...");
        }
        ResponseMessage alert = new ResponseMessage(
                ResponseMessage.Type.danger,
                "404",
                ex.getId()+" Not Found ");
        return new ResponseEntity<>(alert,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = {InvalidRequestException.class})
    public ResponseEntity<ResponseMessage> handleInvalidRequestException(InvalidRequestException ex, WebRequest req) {
        if (log.isDebugEnabled()) {
            log.debug("handling InvalidRequestException...");
        }

        ResponseMessage alert = new ResponseMessage(
            ResponseMessage.Type.danger,
            ApiErrors.INVALID_REQUEST,
            messageSource.getMessage(ApiErrors.INVALID_REQUEST, new String[]{}, null));

        BindingResult result = ex.getErrors();

        List<FieldError> fieldErrors = result.getFieldErrors();

        if (!fieldErrors.isEmpty()) {
        	for (FieldError e : fieldErrors){
        		alert.addError(e.getField(), e.getCode(), e.getDefaultMessage());
        	}
        }
        return new ResponseEntity<>(alert, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    /**
     * 处理jdbctemplate抛出的错误
     * @date 2016年4月6日
     * @para DataAccessException 
     * @param ex
     * @return JSON<ResponseMessage>
     *
     */
    @ExceptionHandler(value={DataAccessException.class})
    public ResponseEntity<ResponseMessage> handleSqlException(DataAccessException ex) {
    	ResponseMessage alert = new ResponseMessage(
    			ResponseMessage.Type.danger,
    			ApiErrors.DATA_ACCESS_ERROR,
    			ex.getMessage());
    	log.error(ex.getMessage());
    	 return new ResponseEntity<>(alert, HttpStatus.INTERNAL_SERVER_ERROR);
	  
    }
    
    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    @ResponseBody
    public ResponseEntity<ResponseMessage> handleGenericException(Exception ex, WebRequest request) {
//        if (log.isDebugEnabled()) {
//            log.warn(ex.getMessage());
//        }
    	log.error(ex.getMessage());
        return new ResponseEntity<>(new ResponseMessage(ResponseMessage.Type.danger, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
    
  
}
