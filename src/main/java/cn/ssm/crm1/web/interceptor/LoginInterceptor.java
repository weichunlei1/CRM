package cn.ssm.crm1.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.ssm.crm1.constant.UserContext;
import cn.ssm.crm1.domain.Employee;
import cn.ssm.crm1.util.PermissionUtil;

/**
 * 登陆拦截器
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor{

	/**
	 * 执行时机：访问controller之前
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		
		//获取用户
		Employee employee = (Employee) request.getSession().getAttribute(UserContext.USER_IN_SESSION);
		if(employee!=null){
			//获取请求地址要和该用户的权限匹配
			//请求地址  cn.ssm.crm1.web.controller.EmployeeController:list
			HandlerMethod handlerMethod = (HandlerMethod)handler;
			//获取请求的路径（权限表达式）
			String function = handlerMethod.getBean().getClass().getName()+":"+handlerMethod.getMethod().getName();
			//判断权限表达式是否需要权限控制
			if(PermissionUtil.checkPermission(function)){//是
				return true;
			}else{//否
				//怎么判断是页面还是ajax
				if(handlerMethod.getMethodAnnotation(ResponseBody.class) != null){//ajax 
					response.sendRedirect("/noPermission.json");
				}else{
					response.sendRedirect("/noPermission.jsp");
				}
				return false;
			}
		}else{
			//跳转登录页面
			response.sendRedirect("/loginUI");
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
