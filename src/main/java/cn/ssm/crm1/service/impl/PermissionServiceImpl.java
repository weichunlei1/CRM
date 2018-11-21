package cn.ssm.crm1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ssm.crm1.domain.Permission;
import cn.ssm.crm1.mapper.PermissionMapper;
import cn.ssm.crm1.page.PageResult;
import cn.ssm.crm1.query.PageQueryByPermission;
import cn.ssm.crm1.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionMapper permissionMapper;
	
	@Override
	public PageResult queryPage(PageQueryByPermission pp) {
		PageResult pageResult = null;
		//数量
		Long count = permissionMapper.queryPageByCount();
		if(count == 0){
			return pageResult = PageResult.EMPTY;
		}
		//每页数据
		List rows = permissionMapper.queryPageByRows(pp);
		
		return new PageResult(count,rows);
	}

	@Override
	public Permission queryPermissionByResource(String function) {
		return permissionMapper.queryPermissionByResource(function);
	}

	@Override
	public List<Permission> queryPermissionsByEid(Long eid) {
		return permissionMapper.queryPermissionsByEid(eid);
	}

}
