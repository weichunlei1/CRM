package cn.ssm.crm1.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.ssm.crm1.constant.UserContext;
import cn.ssm.crm1.domain.Employee;
import cn.ssm.crm1.domain.Menu;
import cn.ssm.crm1.domain.Permission;
import cn.ssm.crm1.service.PermissionService;

@Component
public class PermissionUtil {
	
	//有static 不能注入
	private static PermissionService permissionService;
	
	private static HttpServletRequest request;
	
	/*
	 * 推荐使用在controller
	 * public String hello(HttpServletRequest request,HttpServletResponse response) 
	 */
	@Autowired
	public  void setRequest(HttpServletRequest request) {
		PermissionUtil.request = request;
	}

	@Autowired
	public  void setPermissionService(PermissionService permissionService) {
		PermissionUtil.permissionService = permissionService;
		//代码的扩展性
		//checkPermission(request,"");
	}

	public static boolean checkPermission(String function) {
		
		/**
		 * 只要不是在controller就不是在web环境下
		 * 
		 */
		Employee employee = (Employee) request.getSession().getAttribute(UserContext.USER_IN_SESSION);
		
		//超级管理员放行
		if(employee.getAdmin()!=null && employee.getAdmin()){
			return true;
		}
		//根据权限表达式去数据库权限表查询是否有该权限
		Permission permission = permissionService.queryPermissionByResource(function);
		if(permission!=null){
			//有
			//获取该用户所有的权限表达式
			//List<Permission> permissions = permissionService.queryPermissionsByEid(employee.getId());
			List<Permission> permissions = (List<Permission>) request.getSession().getAttribute(UserContext.PERMISSION_IN_SESSION);
			//通过请求的权限表达式与该用户所有的权限表达式匹配
				if(permissions!=null && permissions.size()>0){
					//获取All匹配
					String allPermission = function.split(":")[0]+":All";
					for (Permission p : permissions) {
						//完全匹配||All匹配
						if(p.getResource().equals(function) || p.getResource().equals(allPermission)){
							//中  
							//放行
							return true;
						}
					}
					//没中
					//拦截
					return false;
				}else{
					return false;
				}
		}
		//没有
		//放行
		return true;
	}
	public static void checkMenu(List<Menu> menus) {
		//遍历 
		for(int i=menus.size()-1;i>=0;i--){
			//根据Menu的resource判断是否有该权限
			if(checkPermission(menus.get(i).getResource())){
				//有
					//判断该菜单是否还有子菜单
				List<Menu> children = menus.get(i).getChildren();
				if(children!=null &&children.size()>0){
					checkMenu(children);
				}
			}else{
				//没有 移除
				menus.remove(menus.get(i));
			}
		}		
	}
}
