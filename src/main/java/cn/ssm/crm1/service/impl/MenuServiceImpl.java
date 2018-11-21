package cn.ssm.crm1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ssm.crm1.domain.Menu;
import cn.ssm.crm1.mapper.MenuMapper;
import cn.ssm.crm1.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public List<Menu> queryRootMenu() {
		return menuMapper.queryRootMenu();
	}

}
