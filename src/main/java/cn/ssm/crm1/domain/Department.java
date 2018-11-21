package cn.ssm.crm1.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 部门实体
 * @author Administrator
 *
 */
@Setter@Getter
public class Department {
    private Long id;//主键
    private String sn;//部门编号
    private String name;//部门名称
    private Long manager;//部门经理
    private Long parent;//上一级部门
    private Boolean state;//状态 1 正常 ，0 停用
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getManager() {
		return manager;
	}
	public void setManager(Long manager) {
		this.manager = manager;
	}
	public Long getParent() {
		return parent;
	}
	public void setParent(Long parent) {
		this.parent = parent;
	}
	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
    

    
}