$(function(){
	$("#employee_datagrid").datagrid({
		url:"/employee_list",
		fit:true,
		fitColumns:true,
		pagination:true,
		singleSelect:true,
		pageList:[1,5,10,20],
		toolbar:"#employee_datagrid_btn",
		onClickRow:function(rowIndex, rowData){
			//console.log(rowIndex);
			//console.log(rowData);
			if(rowData.state){
				//编辑，离职按钮可以使用
				$("#edit").linkbutton('enable');
				$("#del").linkbutton('enable');
			}else{
				//编辑，离职按钮不可以使用
				$("#edit").linkbutton('disable');
				$("#del").linkbutton('disable');
			}
		},
		columns:[[
		          {field:'id',title:'编号',width:1,align:'center'},
		          {field:'username',title:'名称',width:1,align:'center'},
		          {field:'realname',title:'真实名字',width:1,align:'center'},
		          {field:'tel',title:'电话',width:1,align:'center'},
		          {field:'email',title:'邮箱',width:1,align:'center'},
		          {field:'inputtime',title:'入职时间',width:1,align:'center',formatter:timeFormat},
		          {field:'state',title:'状态',width:1,align:'center',formatter:stateFormat},
		          {field:'admin',title:'是否管理员',width:1,align:'center',formatter:adminFormat},
		          {field:'dept',title:'部门',width:1,align:'center',formatter:deptFormat}
		          ]]
	});
	//日期格式化
	function timeFormat(value,row,index){
		 var unixTimestamp = new Date(value);  
         return unixTimestamp.toLocaleDateString();  
	}
	//状态格式化
	function stateFormat(value,row,index){
		if(value){
			return "<font color='green'>正常</font>";
		}else{
			return "<font color='red'>离职</font>";
		}
	}
	//管理员格式化
	function adminFormat(value,row,index){
		if(value){
			return "是";
		}else{
			return "否";
		}	
	}
	//部门格式化
	function deptFormat(value,row,index){
		if (row.dept){
			return row.dept.name;
		} else {
			return value;
		}

	}
	
	$("#employee_dialog_form").dialog({
		width:300,
		height:370,
		closed: true,
		modal:true,
		buttons:"#employee_dialog_buttons"
	});
});
function  add(){
	$("#employee_dialog_form").dialog("open");
	$("#employee_dialog_form").dialog("setTitle","增加");
	$("#employee_dialog_form").form("clear");
} 
function  edit(){
	//选中数据 
	var row = $("#employee_datagrid").datagrid("getSelected");
	if(row){
		//打开窗口
		$("#employee_dialog_form").dialog("open");
		$("#employee_dialog_form").dialog("setTitle","修改");
		$("#employee_dialog_form").form("clear");
		//显示日期
		if(row.inputtime){
			//格式化
			var date = new Date(row.inputtime);  
	        var formatDate = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
	        //console.log(formatDate);
	        row["inputtime"] = formatDate;
		}
		//显示部门
		if(row.dept){
			row["dept.id"] = row.dept.id;
		}
		//根据用户id显示该用户下的角色  返回什么数据
		//$("#role_form_combobox").combobox('setValues',['1','2']);
		/*$.ajax({//异步请求  同步请求   请求顺序
			url:'/queryRoleIdsForEmpId',
			data:{'eid':row.id},
			dataType: "json",
			async: false,
			success: function(msg){
				$("#role_form_combobox").combobox('setValues',msg);
			}
		})*/
		var html = $.ajax({
		  url: "/queryRoleIdsForEmpId",
		  data:{'eid':row.id},
		  async: false
		 }).responseText;
		//
		html = $.parseJSON(html);
		$("#role_form_combobox").combobox('setValues',html);
		//员工数据回显
		$("#employee_dialog_form").form('load',row);
	}else{
		$.messager.alert("温馨提示","请选择要编辑的数据","warning");
	}
} 
function  del(){
	//选中数据 
	var row = $("#employee_datagrid").datagrid("getSelected");
	if(row){
		$.post(
			"/employee_delete",	
			{"id":row.id},
			function(data){
				if(data.success){//删除成功
					   $.messager.alert("温馨提示",data.msg,"info");
			    	   //加载数据表格
			    	   $("#employee_datagrid").datagrid("reload");
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
	$("#employee_datagrid").datagrid("reload");
} 
function searchCondition(){
	var param = {};
	//获取表单的数据
	//console.log($("#query_form").serialize());
	//console.log($("#query_form").serializeArray());
	var formData = $("#query_form").serializeArray();
	for(var i=0;i<formData.length;i++){
		param[formData[i].name] = formData[i].value;
	}
	//使用datagrid重新加载一遍  带参数
	$('#employee_datagrid').datagrid('load',param);	
}

function  saveForm(){
	//获取隐藏域id的值
	var id = $("#id").val();
	var url = "/employee_save";
	if(id){
		url = "/employee_update";
	}
	//提交表单
	$('#employee_form').form('submit',{    
	    url:url,    
	    onSubmit: function(param){    
	    	if(!$('form').form('validate')){
	    		return false;
	    	}
	    	//获取角色id
	    	var rows = $("#role_form_combobox").combobox('getValues');
	    	for(var i=0;i<rows.length;i++){
	    		param['roles['+i+'].id'] = rows[i];
			}
	    	return true;
	    },    
	    success:function(data){ 
	       var obj = $.parseJSON(data);
	       if(obj.success){//保存成功
	    	   $.messager.alert("温馨提示",obj.msg,"info");
	    	   // 关闭窗口 
	    	   $("#employee_dialog_form").dialog("close");
	    	   //清空form数据
	    	   $("#employee_dialog_form").form("clear");
	    	   //加载数据表格
	    	   $("#employee_datagrid").datagrid("reload");
	       }else{
	    	   $.messager.alert("温馨提示",obj.msg,"error");
	       }
	    }    
	}); 
	
} 
function  closeForm(){
	$("#employee_dialog_form").dialog("close");
} 
