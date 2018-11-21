package cn.ssm.crm1.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ssm.crm1.domain.Department;
import cn.ssm.crm1.service.DepartmentService;

@Controller
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping("/department")
	public String department(){
		return "dept/department";
	}
	@RequestMapping("/queryDepartmentForEmployee")
	@ResponseBody
	public List<Department> list(){
		List<Department> depts = departmentService.findDepts();
		return depts;
	}
}
