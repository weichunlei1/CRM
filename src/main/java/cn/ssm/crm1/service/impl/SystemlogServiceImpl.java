package cn.ssm.crm1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ssm.crm1.domain.Systemlog;
import cn.ssm.crm1.mapper.SystemlogMapper;
import cn.ssm.crm1.service.SystemlogService;

@Service
public class SystemlogServiceImpl implements SystemlogService {

	@Autowired
	private SystemlogMapper  systemlogMapper;
	
	@Override
	public void save(Systemlog log) {
		systemlogMapper.insert(log);
	}

}
