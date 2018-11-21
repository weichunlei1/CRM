$(function(){
	$("#menuTree").tree({
		url:"/menu_list",
		method:"get",
		onClick: function(node){
			console.log(node);
			//获取文本
			var text = node.text;
			if(node.attributes){
				node.attributes = $.parseJSON(node.attributes);
			}
			//判断选项卡是否存在
			var flag = $("#myTabs").tabs("exists",text);
			if(flag){
				$("#myTabs").tabs("select",text);
			}else{
				//添加选项卡
				$('#myTabs').tabs('add',{
					title: text,
					selected: true,
					closable:true,
					iconCls:node.iconCls,
					content:"<iframe src='"+node.attributes.url+"' style='width:100%;height:100%' frameborder=0></iframe> "
				});

			}
			
		}   
	});
});