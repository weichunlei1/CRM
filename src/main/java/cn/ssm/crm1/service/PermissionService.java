package cn.ssm.crm1.service;

import java.util.List;

import cn.ssm.crm1.domain.Permission;
import cn.ssm.crm1.page.PageResult;
import cn.ssm.crm1.query.PageQueryByPermission;

public interface PermissionService {

	PageResult queryPage(PageQueryByPermission pp);

	Permission queryPermissionByResource(String function);

	List<Permission> queryPermissionsByEid(Long id);

}
