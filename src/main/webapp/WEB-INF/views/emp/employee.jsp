<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    <%@ taglib uri="http://cn.ssm.crm/jsp/crm" prefix="myFn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工管理</title>
<jsp:include page="/WEB-INF/views/common/common.jsp"></jsp:include>
<script type="text/javascript" src="/js/views/employee.js"></script>
</head>
<body>
	<!-- 数据表格 -->
	<table id="employee_datagrid"></table>
	
	<!-- 按钮 -->
	<div id="employee_datagrid_btn">
		<div>
			<!-- 
				判断按钮权限
				if(has(cn.ssm.crm1.web.controller.EmployeeController:save)){
					增加
				}
				自定义标签
			 -->
			<c:if test="${myFn:checkPermission('cn.ssm.crm1.web.controller.EmployeeController:save')}">
				<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add();">增加</a>
			</c:if>
			<c:if test="${myFn:checkPermission('cn.ssm.crm1.web.controller.EmployeeController:update')}">
				<a id="edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="edit();">修改</a>
			</c:if>
			<a id="del" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="del();">离职</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" onclick="reload();">刷新</a>
		</div>
		<div>
			<form id="query_form">
				名称：<input type="text" name="keyword"/>
				开始时间<input type="text" class="easyui-datebox" name="beginTime"/>-
				<input type="text" class="easyui-datebox" name="endTime"/>
				状态：<select name='state'>
					<option value="">请选择</option>
					<option value="true">正常</option>
					<option value="false">离职</option>
				</select>
				<a href="#" class="easyui-linkbutton" onclick="searchCondition();">查询</a>
			</form>
		</div>
	</div>
	
	<!-- 窗口 -->
	<div id="employee_dialog_form">
		<form id='employee_form' action="" method="post">
			<script type="text/javascript">
				$(function(){
					$.extend($.fn.validatebox.defaults.rules, {    
					    tel: {   
					        validator: function(value,param){  
					        	// value 输入的手机号
					        	// 正则匹配
					        	var regex = /^[1][35678][0-9]{9}$/;
					        	if(regex.test(value)){
					        		return true;
					        	}else{
					        		 return false;  
					        	}
					        },    
					        message: '请输入正确的手机号码'   
					    }    
					});  
				});
			</script>
			
			<table>
				<tr>
					<input id="id" type="hidden" name="id" value=""/>
					<td>名&nbsp;&nbsp;&nbsp;&nbsp;称</td>
					<td><input type="text" name="username" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<td>真实名称</td>
					<td><input type="text" name="realname" class="easyui-validatebox" data-options="required:true"/></td>
				</tr>
				<tr>
					<td>电&nbsp;&nbsp;&nbsp;&nbsp;话</td>
					<td><input type="text" name="tel" class="easyui-validatebox" required="required" data-options="validType:'tel'" /></td>
				</tr>
				<tr>
					<td>邮&nbsp;&nbsp;&nbsp;&nbsp;箱</td>
					<td><input type="text" name="email" class="easyui-validatebox" data-options="validType:'email'"/></td>
				</tr>
				<tr>
					<td>入职时间</td>
					<td><input type="text" id="inputtime" name="inputtime" class="easyui-datebox" required="required" /></td>
				</tr>
				<tr>
					<td>部&nbsp;&nbsp;&nbsp;&nbsp;门</td>
					<td><input type="text" id=dept name="dept.id" class="easyui-combobox" data-options="valueField:'id',textField:'name',url:'/queryDepartmentForEmployee'"/></td>
				</tr>
				<tr>
					<td>角&nbsp;&nbsp;&nbsp;&nbsp;色</td>
					<td><input type="text" id="role_form_combobox" name="role.id" class="easyui-combobox" data-options="valueField:'id',textField:'name',multiple:true,url:'/queryRoleForEmployee'"/></td>
				</tr>
			</table>
		</form>
	</div>
	
	
	<div id="employee_dialog_buttons">
		<a class="easyui-linkbutton" onclick="saveForm();">保存</a>
		<a class="easyui-linkbutton" onclick="closeForm();">关闭</a>
	</div>
	
	
</body>
</html>