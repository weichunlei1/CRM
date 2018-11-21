package cn.ssm.crm1.page;

import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class PageResult {

	private Long total;
	private List rows;
	//定义空的集合
	public static final PageResult EMPTY = new PageResult(0L,Collections.EMPTY_LIST);
	
	public PageResult() {
		super();
	}
	public PageResult(Long total, List rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	
	
	
	
}
