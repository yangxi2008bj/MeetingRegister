package com.lachesis.windranger.interceptor;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lachesis.windranger.common.util.DateUtil;

public class CommonInteceptor extends HandlerInterceptorAdapter {
	
	private static Logger LOG=Logger.getLogger(CommonInteceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			StringBuilder sb = new StringBuilder(1000);
			HandlerMethod h = (HandlerMethod) handler;
			sb.append("-----------------------").append(DateUtil.format(new Date(), DateUtil.YMDHFM))
					.append("-------------------------------------\n");
			sb.append("Controller-Method: ").append(h.getBean().getClass().getName());
			sb.append(".").append(h.getMethod().getName()).append("\n");
			sb.append("Params    : ").append(getParamString(request.getParameterMap())).append("\n");
			sb.append("URI       : ").append(request.getRequestURI()).append("\n");
			System.out.println(sb.toString());
			LOG.info("preHandle" + sb);
		}
		// 记录接口开始时间
		Long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//获取接口开始时间
		Long startTime = (Long) request.getAttribute("startTime");
		//获取接口结束时间
		Long endTime = System.currentTimeMillis();
		//接口执行消耗时间
		Long exeTime = endTime - startTime;
		LOG.info("["+request.getRequestURI()+"]execTime:"+exeTime + "ms");
	
	}

	private String getParamString(Map<String, String[]> map) {
		StringBuilder sb = new StringBuilder();
		for (Entry<String, String[]> e : map.entrySet()) {
			sb.append(e.getKey()).append("=");
			String[] value = e.getValue();
			if (value != null && value.length == 1) {
				sb.append(value[0]).append("\t");
			} else {
				sb.append(Arrays.toString(value)).append("\t");
			}
		}
		return sb.toString();
	}

	@Around("execution(public * com.lachesis.windranger.*.*(..))")
	public Object invoke(ProceedingJoinPoint  joinPoint) throws Throwable {
		Long startTime = System.currentTimeMillis();
		LOG.info("ACTION START: THREAD-"+startTime+"  " + joinPoint.getTarget().getClass()
				+ "." + joinPoint.getSignature().getName() + "("+ Arrays.toString(joinPoint.getArgs())+")  ");
		Object o = joinPoint.proceed();
		Long costTime = System.currentTimeMillis() - startTime;
		LOG.info("ACTION   END : THREAD-"+startTime+", CONSUME TIME: " + costTime +"ms");
		return o;
	}
	
	
}
