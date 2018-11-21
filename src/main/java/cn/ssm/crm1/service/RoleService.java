package cn.ssm.crm1.service;

import java.util.List;

import cn.ssm.crm1.domain.Permission;
import cn.ssm.crm1.domain.Role;
import cn.ssm.crm1.page.PageResult;
import cn.ssm.crm1.query.PageQueryByRole;
import cn.ssm.crm1.util.JsonResult;

public interface RoleService {

	PageResult queryPageForRole(PageQueryByRole pr);

	int save(Role role);

	PageResult queryPermissionByRid(Long rid);

	int update(Role role);

	int delete(Long rid);

}
