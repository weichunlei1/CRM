package cn.ssm.crm1.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
/**
 * 员工实体
 * @author Administrator
 *
 */
@Setter@Getter
public class Employee {
    private Long id;//主键
    private String username;//账号
    private String realname;//真实名称
    private String password;//密码
    private String tel;//电话
    private String email;//邮箱
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date inputtime;//入职时间
    private Boolean state;//状态：1 正常  0 离职
    private Boolean admin;//是否是管理员： 1 是  0 否
    private Department dept;//数据库是外键，使用实体

    
    private List<Role> roles = new ArrayList<Role>();


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getRealname() {
		return realname;
	}


	public void setRealname(String realname) {
		this.realname = realname;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getInputtime() {
		return inputtime;
	}


	public void setInputtime(Date inputtime) {
		this.inputtime = inputtime;
	}


	public Boolean getState() {
		return state;
	}


	public void setState(Boolean state) {
		this.state = state;
	}


	public Boolean getAdmin() {
		return admin;
	}


	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}


	public Department getDept() {
		return dept;
	}


	public void setDept(Department dept) {
		this.dept = dept;
	}


	public List<Role> getRoles() {
		return roles;
	}


	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
    
   
}