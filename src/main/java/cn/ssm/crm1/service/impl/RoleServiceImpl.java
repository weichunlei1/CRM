package cn.ssm.crm1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ssm.crm1.domain.Permission;
import cn.ssm.crm1.domain.Role;
import cn.ssm.crm1.mapper.RoleMapper;
import cn.ssm.crm1.page.PageResult;
import cn.ssm.crm1.query.PageQueryByRole;
import cn.ssm.crm1.service.RoleService;
import cn.ssm.crm1.util.JsonResult;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public PageResult queryPageForRole(PageQueryByRole pr) {
		PageResult pageResult = null;
		//数量
		Long count = roleMapper.queryPageByCount();
		if(count == 0){
			return pageResult = PageResult.EMPTY;
		}
		//每页数据
		List rows = roleMapper.queryPageByRows(pr);
		
		return new PageResult(count,rows);
	}

	@Override
	public int save(Role role) {
		//添加角色
		int count = roleMapper.insert(role);
		//添加角色权限关系
		List<Permission> permissions = role.getPermissions();
		for (Permission permission : permissions) {
			roleMapper.insertRoleAndPermissionRelation(role.getId(),permission.getId());
		}
		return count;
	}

	@Override
	public PageResult queryPermissionByRid(Long rid) {
		List<Permission> rows = roleMapper.queryPermissionByRid(rid);
		long total = rows.size();
		return new PageResult(total,rows);
	}

	@Override
	public int update(Role role) {
		//删除以前的关系
		roleMapper.deleteRoleAndPermissionRelation(role.getId());
		//修改角色
		int count = roleMapper.updateByPrimaryKey(role);
		//重新添加新的关系
		List<Permission> permissions = role.getPermissions();
		for (Permission permission : permissions) {
			roleMapper.insertRoleAndPermissionRelation(role.getId(),permission.getId());
		}
		return count;
	}

	@Override
	public int delete(Long rid) {
		//删除关系表
		roleMapper.deleteRoleAndPermissionRelation(rid);
		//删除角色表
		return roleMapper.deleteByPrimaryKey(rid);
	}
}
