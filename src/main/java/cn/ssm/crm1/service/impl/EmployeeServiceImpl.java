package cn.ssm.crm1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ssm.crm1.domain.Employee;
import cn.ssm.crm1.domain.Role;
import cn.ssm.crm1.mapper.EmployeeMapper;
import cn.ssm.crm1.mapper.RoleMapper;
import cn.ssm.crm1.page.PageResult;
import cn.ssm.crm1.query.PageQueryByEmployee;
import cn.ssm.crm1.service.EmployeeService;

/**
 * 员工业务
 * @author Administrator
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public int save(Employee e) {
		e.setPassword("111111");
		e.setState(true);
		e.setAdmin(false);
		
		//保存员工
		int count = employeeMapper.insert(e);
		//保存员工和角色之间的关系
		List<Role> roles = e.getRoles();
		for (Role role : roles) {
			employeeMapper.insertEmployeeAndRoleRelative(e.getId(),role.getId());
		}
		return count;
	}

	@Override
	public Employee login(String username, String password) {
		return employeeMapper.loginByNameAndPwd(username,password);
	}

	@Override
	public PageResult queryPage(PageQueryByEmployee po) {
		PageResult pageResult = null;
		//数量
		Long count = employeeMapper.queryPageByCount();
		if(count == 0){
			return pageResult = PageResult.EMPTY;
		}
		//每页数据
		List rows = employeeMapper.queryPageByRows(po);
		
		return new PageResult(count,rows);
	}

	@Override
	public int update(Employee e) {
		//删除关系
		employeeMapper.deleteEmployeeAndRoleRelative(e.getId());
		//修改用户
		Employee oldE = employeeMapper.selectByPrimaryKey(e.getId());
		oldE.setUsername(e.getUsername());
		oldE.setRealname(e.getRealname());
		oldE.setTel(e.getTel());
		oldE.setEmail(e.getEmail());
		oldE.setInputtime(e.getInputtime());
		oldE.setDept(e.getDept());
		int count = employeeMapper.updateByPrimaryKey(oldE);
		//添加关系
		List<Role> roles = e.getRoles();
		for (Role role : roles) {
			employeeMapper.insertEmployeeAndRoleRelative(e.getId(),role.getId());
		}
		return count;
	}

	@Override
	public int updateState(Long eid) {
		//删除关系
		employeeMapper.deleteEmployeeAndRoleRelative(eid);
		return employeeMapper.updateState(eid);
	}

	@Override
	public List<Role> queryRoleForEmployee() {
		return roleMapper.selectAll();
	}

	@Override
	public List<Long> queryRoleIdsForEmpId(Long eid) {
		
		return roleMapper.queryRoleIdsForEmpId(eid);
	}

}
