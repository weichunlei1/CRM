package cn.ssm.crm1.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页控制
 * @author Administrator
 *
 */
@Controller
public class IndexController {

	
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
}
