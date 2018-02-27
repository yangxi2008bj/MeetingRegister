package com.lachesis.windranger.springmvc.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.lachesis.windranger.common.exception.AppException;
import com.lachesis.windranger.common.exception.RfForbiddenException;
import com.lachesis.windranger.common.exception.RfNotFoundException;
import com.lachesis.windranger.common.exception.RfUnauthorizedException;

public class DefaultExceptionHandler extends ExceptionHandlerExceptionResolver {  
	
	private static final Logger LOG=LoggerFactory.getLogger(DefaultExceptionHandler.class);

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,  
			Exception ex) {  

		ModelAndView mv = new ModelAndView();

		HttpStatus responseStatus = HttpStatus.OK;
		if(ex instanceof RfForbiddenException) {
			responseStatus = HttpStatus.FORBIDDEN;
		}else if(ex instanceof RfNotFoundException) {
			responseStatus = HttpStatus.NOT_FOUND;
		}else if(ex instanceof RfUnauthorizedException) {
			responseStatus = HttpStatus.UNAUTHORIZED;
		}else {
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		String responseContent = AppException.getExceptionJsonString(ex, responseStatus.name(), responseStatus.value());
		
		//使用response返回      
		response.setStatus(responseStatus.value()); //设置状态码
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE); //设置ContentType  
		response.setCharacterEncoding("UTF-8"); //避免乱码  
		response.setHeader("Cache-Control", "no-cache, must-revalidate");  			
			try {
				PrintWriter writer = response.getWriter();
				LOG.error("页面输出消息："+responseContent);
				writer.write(responseContent);
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		return mv;  
	}
	
	 
}  