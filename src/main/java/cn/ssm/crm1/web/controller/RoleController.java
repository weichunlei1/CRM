package cn.ssm.crm1.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ssm.crm1.domain.Permission;
import cn.ssm.crm1.domain.Role;
import cn.ssm.crm1.page.PageResult;
import cn.ssm.crm1.query.PageQueryByRole;
import cn.ssm.crm1.service.RoleService;
import cn.ssm.crm1.util.JsonResult;

@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/role")
	public String index(){
		return "role/role";
	}
	@RequestMapping("/role_list")
	@ResponseBody
	public PageResult list(PageQueryByRole pr){
		PageResult result = null;
		result = roleService.queryPageForRole(pr);
		return result;
	}
	@RequestMapping("/role_save")
	@ResponseBody
	public JsonResult save(Role role){
		JsonResult result = null;
		try {
			int affectCount = roleService.save(role);
			if(affectCount>0){
				result = new JsonResult(true,"角色添加成功");
			}else{
				result = new JsonResult(false,"角色添加失败");
			}
		} catch (Exception e) {
			result = new JsonResult(false,"角色添加异常");
			e.printStackTrace();
		}
		return result;
	}
	@RequestMapping("/role_queryPermissionByRid")
	@ResponseBody
	public PageResult queryPermissionByRid(Long rid){
		PageResult result = null;
		try {
			result = roleService.queryPermissionByRid(rid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@RequestMapping("/role_update")
	@ResponseBody
	public JsonResult update(Role role){
		JsonResult result = null;
		try {
			int affectCount = roleService.update(role);
			if(affectCount>0){
				result = new JsonResult(true,"角色修改成功");
			}else{
				result = new JsonResult(false,"角色修改失败");
			}
		} catch (Exception e) {
			result = new JsonResult(false,"角色修改异常");
			e.printStackTrace();
		}
		return result;
	}
	@RequestMapping("/role_delete")
	@ResponseBody
	public JsonResult delete(Long rid){
		JsonResult result = null;
		try {
			int affectCount = roleService.delete(rid);
			if(affectCount>0){
				result = new JsonResult(true,"角色删除成功");
			}else{
				result = new JsonResult(false,"角色删除失败");
			}
		} catch (Exception e) {
			result = new JsonResult(false,"角色删除异常");
			e.printStackTrace();
		}
		return result;
	}
}
