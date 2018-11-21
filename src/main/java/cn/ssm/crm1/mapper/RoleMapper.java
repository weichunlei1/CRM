package cn.ssm.crm1.mapper;

import cn.ssm.crm1.domain.Permission;
import cn.ssm.crm1.domain.Role;
import cn.ssm.crm1.query.PageQueryByRole;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

	Long queryPageByCount();

	List queryPageByRows(PageQueryByRole pr);

	void insertRoleAndPermissionRelation(@Param("rid")Long rid,@Param("pid") Long pid);

	List<Permission> queryPermissionByRid(Long rid);

	void deleteRoleAndPermissionRelation(Long rid);

	List<Long> queryRoleIdsForEmpId(Long eid);

	
}