package cn.ssm.crm1.mapper;

import cn.ssm.crm1.domain.Employee;
import cn.ssm.crm1.domain.Role;
import cn.ssm.crm1.query.PageQueryByEmployee;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

	Employee loginByNameAndPwd(@Param("username") String username,@Param("password") String password);

	Long queryPageByCount();

	List queryPageByRows(PageQueryByEmployee po);

	int updateState(Long eid);

	void insertEmployeeAndRoleRelative(@Param("eid")Long eid,@Param("rid") Long rid);

	void deleteEmployeeAndRoleRelative(Long eid);

	
}