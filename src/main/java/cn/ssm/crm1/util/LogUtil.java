package cn.ssm.crm1.util;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.ssm.crm1.constant.UserContext;
import cn.ssm.crm1.domain.Employee;
import cn.ssm.crm1.domain.Systemlog;
import cn.ssm.crm1.service.SystemlogService;


public class LogUtil {

	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private SystemlogService systemlogService;
	
	public void writeLog(JoinPoint joinPoint){
		if(joinPoint.getTarget() instanceof SystemlogService){
			return;
		}
		Systemlog log = new Systemlog();
		//设置用户
		Employee employee = (Employee) request.getSession().getAttribute(UserContext.USER_IN_SESSION);
		log.setOpuser(employee);
	
		//设置时间
		log.setOptime(new Date());
		//设置ip
		String opip = request.getRemoteAddr();
		log.setOpip(opip);
		//设置那个类的哪个方法
		//function:访问的那个类的哪个方法
		String a = joinPoint.getTarget().getClass().getName();
		String b = joinPoint.getSignature().getName();
		String function = a+":"+b;
		log.setFunction(function);
		//设置携带的参数
		Object[] args = joinPoint.getArgs();
		String params = Arrays.toString(args);
		log.setParams(params);
		//添加日志
		systemlogService.save(log);
	}
}
