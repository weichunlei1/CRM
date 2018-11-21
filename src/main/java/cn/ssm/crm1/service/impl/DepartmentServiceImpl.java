package cn.ssm.crm1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ssm.crm1.domain.Department;
import cn.ssm.crm1.mapper.DepartmentMapper;
import cn.ssm.crm1.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Override
	public List<Department> findDepts() {
		return departmentMapper.selectAll();
	}

}
