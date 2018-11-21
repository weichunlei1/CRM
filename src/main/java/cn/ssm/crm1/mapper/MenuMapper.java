package cn.ssm.crm1.mapper;

import cn.ssm.crm1.domain.Menu;
import java.util.List;

public interface MenuMapper {
    
	public List<Menu> queryRootMenu();
	
	public List<Menu> queryMenuByPid(Long id);
	
}