package cn.ssm.crm1.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ssm.crm1.constant.UserContext;
import cn.ssm.crm1.domain.Menu;
import cn.ssm.crm1.service.MenuService;

@Controller
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@RequestMapping("/menu_list")
	@ResponseBody
	public List<Menu> list(HttpSession session){
		List<Menu> result = null;
		//result = menuService.queryRootMenu();
		result = (List<Menu>) session.getAttribute(UserContext.MENU_IN_SESSION);
		return result;
	}
	
}
