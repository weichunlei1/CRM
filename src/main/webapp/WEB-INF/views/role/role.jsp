<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
<jsp:include page="/WEB-INF/views/common/common.jsp"></jsp:include>
<script type="text/javascript" src="/js/views/role.js"></script>
</head>
<body>
	<!-- 数据表格 -->
	<table id="role_datagrid"></table>
	
	<!-- 按钮 -->
	<div id="role_datagrid_btn">
		<div>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add();">增加</a>
			<a id="edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="edit();">修改</a>
			<a id="del" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="del();">删除</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" onclick="reload();">刷新</a>
		</div>
	</div>
	
	<!-- 窗口 -->
	<div id="role_dialog_form">
		<form action="" method="post">
					<input id="id" type="hidden" name="id" value=""/>
			<table>
				<tr align="center">
					<td >角色编号<input type="text" name="sn"/></td>
					<td >角色名称<input type="text" name="name"/></td>
				</tr>
				<tr align="center">
					<td><table id="role_allPermissions"></table></td>
					<td><table id="role_selfPermissions"></table></td>
				</tr>
			</table>
		</form>
	</div>
	
	
	<div id="role_dialog_buttons">
		<a class="easyui-linkbutton" onclick="saveForm();">保存</a>
		<a class="easyui-linkbutton" onclick="closeForm();">关闭</a>
	</div>
	
	
</body>
</html>