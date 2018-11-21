/*
 * 登陆方法
 */
function loginForm(){
	//获取用户名
	var username = $("input[name='username']").val();
	//去空
	username = $.trim(username);
	//获取密码
	var password = $("input[name='password']").val();
	//去空
	password = $.trim(password);
	//发送请求
	$.post(
			"/login",
			{"username":username,"password":password},
			function(data){
				if(data.success){//成功
					//跳转首页
					location.href = "/index";
				}else{//失败
					$.messager.alert("温馨提示",data.msg,"warning");
				}
			},"json" //预定义返回json格式
	);
}
/*
 * 重置方法
 */
function resetForm(){
	//清空输入框
	$("input[name='username']").val("");
	$("input[name='password']").val("");
}