$(function(){
	$("#role_datagrid").datagrid({
		url:"/role_list",
		fit:true,
		fitColumns:true,
		pagination:true,
		singleSelect:true,
		pageList:[1,5,10,20],
		toolbar:"#role_datagrid_btn",
		columns:[[
		          {field:'sn',title:'角色编号',width:1,align:'center'},
		          {field:'name',title:'角色名称',width:1,align:'center'}
		          ]]
	});
	
	$("#role_dialog_form").dialog({
		width:600,
		height:400,
		closed: true,
		modal:true,
		buttons:"#role_dialog_buttons"
	});
	
	
	
	//所有权限
	$("#role_allPermissions").datagrid({
		width:270,
		height:280,
		title:'所有权限',
		url:'/permission_list',
		fitColumns:true,
		pagination:true,
		singleSelect:true,
		rownumbers:true,
		columns:[[
		          {field:'name',title:'权限名称',width:1,align:'center'}
		          ]],
		//双击添加权限
		onDblClickRow:function(rowIndex, rowData){
			//问题:追加已添加的权限
			//结果:没有:添加;有:选中
			//思路:获取已有的所有权限,根据要添加的去和已有的所有权限匹配，没有:添加;有:选中

			//获取已有的所有权限
			var rows = $("#role_selfPermissions").datagrid('getRows');
			var flag = false;
			var index = -1;
			if(rows.length!=0){
				//遍历
				for(var i=0;i<rows.length;i++){
					if(rows[i].id == rowData.id){//有						
						flag = true;
						index = i;
						break;
					}
				}
			}
			if(flag){
				//选中
				$("#role_selfPermissions").datagrid('selectRow',index);
			}else{
				//添加
				$("#role_selfPermissions").datagrid('appendRow',rowData);
			}
		}	      
	});
	//简化分页
	 var pager = $("#role_allPermissions").datagrid("getPager");
	    pager.pagination({
	        showPageList: false,
	        showRefresh: false,
	        displayMsg: "",
	    });
	//已有权限
	$("#role_selfPermissions").datagrid({
		width:270,
		height:280,
		title:'已有权限',
		fitColumns:true,
		singleSelect:true,
		rownumbers:true,
		columns:[[
		          {field:'name',title:'角色名称',width:1,align:'center'}
		          ]],
		onDblClickRow:function(rowIndex, rowData){
			//删除选中的行
			$("#role_selfPermissions").datagrid('deleteRow',rowIndex);
		}
	});
});
function  add(){
	$("#role_dialog_form").dialog("open");
	$("#role_dialog_form").dialog("setTitle","角色增加");
	$("#role_dialog_form").form("clear");
} 
function  edit(){
	//选中数据 
	var row = $("#role_datagrid").datagrid("getSelected");
	if(row){
		//打开窗口
		$("#role_dialog_form").dialog("open");
		$("#role_dialog_form").dialog("setTitle","角色修改");
		$("#role_dialog_form").form("clear");
		//根据要编辑的角色id查询该角色锁拥有的权限,回显到已有权限
		
		/*var rid = row.id;
		$("#role_selfPermissions").datagrid({
			width:270,
			height:280,
			title:'已有权限',
			url:'/role_queryPermissionByRid?rid='+rid,
			fit:true,
			fitColumns:true,
			singleSelect:true,
			rownumbers:true,
			columns:[[
			          {field:'name',title:'角色名称',width:1,align:'center'}
			          ]]
		});*/
		//返回datagrid所有属性对象
		var options = $("#role_selfPermissions").datagrid('options');
		options.url = 'role_queryPermissionByRid';
		//console.log(options);
		$("#role_selfPermissions").datagrid('load',{
			rid: row.id
		})
		//数据回显
		$("#role_dialog_form").form('load',row);
	}else{
		$.messager.alert("温馨提示","请选择要编辑的数据","warning");
	}
} 
function  del(){
	//选中数据 
	var row = $("#role_datagrid").datagrid("getSelected");
	if(row){
		$.post(
			"/role_delete",	
			{"rid":row.id},
			function(data){
				if(data.success){//删除成功
					   $.messager.alert("温馨提示",data.msg,"info");
			    	   //加载数据表格
			    	   $("#role_datagrid").datagrid("reload");
			       }else{
			    	   $.messager.alert("温馨提示",data.msg,"error");
			       }
			},"json"
		);
	}else{
		$.messager.alert("温馨提示","请选择要删除的数据","warning");
	}
} 
function  reload(){
	$("#role_datagrid").datagrid("reload");
} 

function  saveForm(){
	//获取隐藏域id的值
	var id = $("#id").val();
	var url = "/role_save";
	if(id){
		url = "/role_update";
	}
	//提交表单
	$('form').form('submit',{    
	    url:url,    
	    onSubmit: function(param){    
	    	if(!$('form').form('validate')){
	    		return false;
	    	}
	    	//关联权限
	    	var rows = $("#role_selfPermissions").datagrid('getRows');
	    	for(var i=0;i<rows.length;i++){
	    		param['permissions['+i+'].id'] = rows[i].id;
			}
	    	return true;
	    	
	    },  
	    success:function(data){ 
	       var obj = $.parseJSON(data);
	       if(obj.success){//保存成功
	    	   $.messager.alert("温馨提示",obj.msg,"info");
	    	   // 关闭窗口 
	    	   $("#role_dialog_form").dialog("close");
	    	   //清空form数据
	    	   $("#role_dialog_form").form("clear");
	    	   //加载数据表格
	    	   $("#role_datagrid").datagrid("reload");
	       }else{
	    	   $.messager.alert("温馨提示",obj.msg,"error");
	       }
	    }   
	}); 
	
} 
function  closeForm(){
	$("#role_dialog_form").dialog("close");
} 
