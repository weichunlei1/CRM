package cn.ssm.crm1.query;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class BaseQueryPage {

	private Integer page;//当前页
	private Integer rows;//每页数量
	private Integer start;
	
	public Integer getStart(){
		return page==null?0:(page-1)*rows;
	}
}
