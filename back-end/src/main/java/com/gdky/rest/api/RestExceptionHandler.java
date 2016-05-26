package com.gdky.rest.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gdky.rest.configuration.ApiErrors;
import com.gdky.rest.entity.ResponseMessage;
import com.gdky.rest.exception.InvalidRequestException;
import com.gdky.rest.exception.ResourceAlreadyExistsExcepiton;
import com.gdky.rest.exception.ResourceNotFoundException;

/**
 * Called when an exception occurs during request processing. Transforms
 * exception message into JSON format.
 */
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger log = LoggerFactory
			.getLogger(RestExceptionHandler.class);

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(value = { ResourceNotFoundException.class })
	@ResponseBody
	public ResponseEntity<ResponseMessage> handleResourceNotFoundException(
			ResourceNotFoundException ex, WebRequest request) {
		if (log.isDebugEnabled()) {
			log.debug("handling ResourceNotFoundException...");
		}
		ResponseMessage alert = new ResponseMessage(
				ResponseMessage.Type.danger, "404", ex.getId() + " Not Found ");
		return new ResponseEntity<>(alert, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = { ResourceAlreadyExistsExcepiton.class })
	public ResponseEntity<ResponseMessage> handleResourceAlreadyExistsException(
			ResourceAlreadyExistsExcepiton ex, WebRequest request) {
		if (log.isDebugEnabled()) {
			log.debug("handling ResourceAlreadyExistsException...");
		}
		ResponseMessage alert = new ResponseMessage(
				ResponseMessage.Type.danger, "identify conflict", "该人员已备案，无法重复备案");
		return new ResponseEntity<>(alert, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(value = { InvalidRequestException.class })
	public ResponseEntity<ResponseMessage> handleInvalidRequestException(
			InvalidRequestException ex, WebRequest req) {
		if (log.isDebugEnabled()) {
			log.debug("handling InvalidRequestException...");
		}

		ResponseMessage alert = new ResponseMessage(
				ResponseMessage.Type.danger, ApiErrors.INVALID_REQUEST,
				messageSource.getMessage(ApiErrors.INVALID_REQUEST,
						new String[] {}, null));

		BindingResult result = ex.getErrors();

		List<FieldError> fieldErrors = result.getFieldErrors();

		if (!fieldErrors.isEmpty()) {
			for (FieldError e : fieldErrors) {
				alert.addError(e.getField(), e.getCode(), e.getDefaultMessage());
			}
		}
		return new ResponseEntity<>(alert, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	/**
	 * 处理jdbctemplate抛出的错误
	 * 
	 * @date 2016年4月6日
	 * @para DataAccessException
	 * @param ex
	 * @return JSON<ResponseMessage>
	 * 
	 */
	@ExceptionHandler(value = { DataAccessException.class })
	public ResponseEntity<ResponseMessage> handleSqlException(
			DataAccessException ex) {
		ResponseMessage alert = new ResponseMessage(
				ResponseMessage.Type.danger, ApiErrors.DATA_ACCESS_ERROR,
				ex.getMessage());
		log.error(ex.getMessage());
		return new ResponseEntity<>(alert, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(value = { Exception.class, RuntimeException.class })
	@ResponseBody
	public ResponseEntity<ResponseMessage> handleGenericException(Exception ex,
			WebRequest request) {
		// if (log.isDebugEnabled()) {
		// log.warn(ex.getMessage());
		// }
		log.error(ex.getMessage());
		return new ResponseEntity<>(new ResponseMessage(
				ResponseMessage.Type.danger, ex.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	/*
	 * 处理账户验证异常
	 */
	@ExceptionHandler(value = { AuthenticationException.class })
	@ResponseBody
	public ResponseEntity<?> handleAuthenticationException(
			AuthenticationException ex) {

		log.error(ex.getMessage());
		String exMessage = "登录失败";
		if (ex instanceof BadCredentialsException) {
			exMessage = "无效的 用户名 或 密码";
		} else if (ex instanceof LockedException) {
			exMessage = "用户已被锁定，请联系管理中心解锁";
		} else if (ex instanceof AccountExpiredException) {
			exMessage = "用户已过期，请联系管理中心";
		} else if (ex instanceof DisabledException) {
			exMessage = "该用户已失效/注销";
		}
		return new ResponseEntity<>(new ResponseMessage(
				ResponseMessage.Type.warning, exMessage),
				HttpStatus.UNAUTHORIZED);
	}

}
