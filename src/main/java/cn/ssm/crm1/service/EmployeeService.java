package cn.ssm.crm1.service;

import java.util.List;

import cn.ssm.crm1.domain.Employee;
import cn.ssm.crm1.domain.Role;
import cn.ssm.crm1.page.PageResult;
import cn.ssm.crm1.query.PageQueryByEmployee;

public interface EmployeeService {

	int save(Employee e);

	Employee login(String username, String password);

	PageResult queryPage(PageQueryByEmployee po);

	int update(Employee e);

	int updateState(Long id);

	List<Role> queryRoleForEmployee();

	List<Long> queryRoleIdsForEmpId(Long eid);

}
