package cn.ssm.crm1.util;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class JsonResult {

	private boolean success;
	private String msg;
	public JsonResult(boolean success) {
		super();
		this.success = success;
	}
	public JsonResult(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}
	
}
