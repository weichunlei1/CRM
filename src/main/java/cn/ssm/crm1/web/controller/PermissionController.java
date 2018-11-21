package cn.ssm.crm1.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ssm.crm1.page.PageResult;
import cn.ssm.crm1.query.PageQueryByPermission;
import cn.ssm.crm1.service.PermissionService;

@Controller
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	
	@RequestMapping("/permission_list")
	@ResponseBody
	public PageResult list(PageQueryByPermission pp){
		PageResult result= null;
		result = permissionService.queryPage(pp);
		return result;
	}
}
