package cn.ssm.crm1.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ssm.crm1.constant.UserContext;
import cn.ssm.crm1.domain.Department;
import cn.ssm.crm1.domain.Employee;
import cn.ssm.crm1.domain.Menu;
import cn.ssm.crm1.domain.Permission;
import cn.ssm.crm1.domain.Role;
import cn.ssm.crm1.page.PageResult;
import cn.ssm.crm1.query.PageQueryByEmployee;
import cn.ssm.crm1.service.EmployeeService;
import cn.ssm.crm1.service.MenuService;
import cn.ssm.crm1.service.PermissionService;
import cn.ssm.crm1.util.JsonResult;
import cn.ssm.crm1.util.PermissionUtil;

/**
 * 员工控制
 * @author Administrator
 */
@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private PermissionService permissionService;
	@RequestMapping("/employee")
	public String index(){
		return "emp/employee";
	}
	@RequestMapping("/loginUI")
	public String loginUI(HttpSession session){
		return "login";
	}
	/**
	 * 登陆方法
	 * @param username  用户名
	 * @param password  密码
	 * @param session   会话
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public JsonResult login(String username,String password,HttpSession session){
		//根据用户名和密码查询,返回对象
		JsonResult  json = null;
		
 		Employee employee = employeeService.login(username,password);
		if(employee!=null){
			session.setAttribute(UserContext.USER_IN_SESSION, employee);
			//权限
			List<Permission> permissions = permissionService.queryPermissionsByEid(employee.getId());
			//放在session
			session.setAttribute(UserContext.PERMISSION_IN_SESSION, permissions);
			
			
			//获取所有菜单
			List<Menu> menus = menuService.queryRootMenu();
			//放在session
			session.setAttribute(UserContext.MENU_IN_SESSION, menus);
			//判断用户所拥有的菜单
			PermissionUtil.checkMenu(menus);
			//判断
			json = new JsonResult(true);
		}else{
			json = new JsonResult(false,"用户名或密码错误");
		}
		return json;
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		//干掉session
		session.invalidate();
		//跳转登陆页面
		return "login";
	}
	/**
	 * 查询
	 * @param po
	 * @return
	 */
	@RequestMapping("/employee_list")
	@ResponseBody
	public PageResult list(PageQueryByEmployee po){
		//total  总数量  rows  数据
		PageResult pageResult = employeeService.queryPage(po);
		return pageResult;
	}
	/**
	 * 增加
	 * @param e
	 * @return
	 */
	@RequestMapping("/employee_save")
	@ResponseBody
	public JsonResult save(Employee e){
		JsonResult json = null;
		int count = employeeService.save(e);
		if(count>0){
			json = new JsonResult(true,"保存成功");
		}else{
			json = new JsonResult(false,"保存失败");
		}
		return json;
	}
	/**
	 * 修改
	 * @param e
	 * @return
	 */
	@RequestMapping("/employee_update")
	@ResponseBody
	public JsonResult update(Employee e){
		JsonResult json = null;
		int count = employeeService.update(e);
		if(count>0){
			json = new JsonResult(true,"修改成功");
		}else{
			json = new JsonResult(false,"修改失败");
		}
		return json;
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/employee_delete")
	@ResponseBody
	public JsonResult delete(Long id){
		JsonResult json = null;
		int count = employeeService.updateState(id);
		if(count>0){
			json = new JsonResult(true,"删除成功");
		}else{
			json = new JsonResult(false,"删除失败");
		}
		return json;
	}
	@RequestMapping("/queryRoleForEmployee")
	@ResponseBody
	public List<Role> list(){
		List<Role> roles = null;
		roles = employeeService.queryRoleForEmployee();
		return roles;
	}
	@RequestMapping("/queryRoleIdsForEmpId")
	@ResponseBody
	public List<Long> queryRoleIdsForEmpId(Long eid){
		List<Long> rids = null;
		rids = employeeService.queryRoleIdsForEmpId(eid);
		return rids;
	}
}
