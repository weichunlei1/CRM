package cn.ssm.crm1.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class PageQueryByEmployee extends BaseQueryPage{

	private String keyword;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date beginTime;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endTime;
	private Boolean state = null;
	
}
