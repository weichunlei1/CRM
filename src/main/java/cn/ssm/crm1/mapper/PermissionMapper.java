package cn.ssm.crm1.mapper;

import cn.ssm.crm1.domain.Permission;
import cn.ssm.crm1.query.PageQueryByPermission;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

	Long queryPageByCount();

	List queryPageByRows(PageQueryByPermission pp);

	Permission queryPermissionByResource(String function);

	List<Permission> queryPermissionsByEid(Long eid);
}