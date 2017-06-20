<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>深圳无人机行业协会无人机管控调度系统-登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/style.css" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/body.css"/> 
	<SCRIPT type="text/javascript">
		if(self.location!=top.location) 
		{ 
			top.location.href=self.location.href;
		}
	</SCRIPT>
  </head>
  
   <body >
   
   <% 	/**
   		 ** 显示账号不正确提示
   		 **/
   		String error="";
   		String code =(String) request.getAttribute("code");
   		if(code!=null && code.equals("Not LoginCheck")){
			error="账号或密码不正确";
		}else if(code !=null && code.equals("login user")){
			error="账号过期请重新登录";
		}else if(code !=null && code.equals("Account not audited")){
			error="账户未审核，请联系管理员开通";
		}else if(code !=null && code.equals("Account is disabled")){
			error="账户被禁用,有疑问请联系管理员";
		}
							
	%>
	 <s:if test="#session.backUser!=null">
		<%
			session.setAttribute("backUser",null);
		 %>
	</s:if>
	<%
		if(session.getAttribute("user")!=null){
			response.sendRedirect(request.getContextPath()+"/main.jsp");
		}
	 %>
		<!--<center><h1>Jetsoon无人机管理调度系统</h1></center>-->
		<div style="background-color:#fff" align="center">
			<img alt="深圳无人机协会" src="<%=path %>/images/logo.jpg" style="margin-top: 30px;margin-bottom: 5px;">
		</div>
		<div>
		
			<div  style="float: left;margin-left: 150px"><img src="<%=path %>/images/uav.png"/></div>
			<div style="float: right;margin-right: 150px"><img src="<%=path %>/images/uav4.png"/></div>
		   	<div style="float:none; " class="container">
				<section id="content">
				<form action="login!loginCheck.action" method="post" >
					<h1>账号登录</h1>
					<div style="font-family: 楷体;">
						账号：<s:textfield  name="enterpriseUser.accountName" theme="simple" />
					</div>
					<div style="font-family: 楷体;" >
						密码：<s:password  name="enterpriseUser.euPassword"  theme="simple" />
					</div>
					 <div class="">
					 
						<span class="help-block u-errormessage" id="js-server-helpinfo">&nbsp;</span>
					</div> 
					<div>
						<p style="color: red">
							<%=error%>
							<% error=""; %>
						</p>
					</div>
					<div>
						<!-- <input type="submit" value="Log in" /> -->
						<input type="submit" value="登录" class="btn btn-primary" id="js-btn-login"/>
						<a href="/UavAssociationManagerment/addenterpriseuser.jsp">注册账号</a>
						<!-- <a href="#">Register</a> -->
					</div>
				</form><!-- form -->
			</section><!-- content -->
			</div> 
		</div>
		<div class="copy-rights">
					<p>深圳无人机行业协会无人机管控调度系统</p>
					<p>
						Copyright &copy; 2017.Company  
						<a href="http://www.jetsoon.cn/" target="_blank" title="激速智能">Jetsoon</a>
						All rights reserved.
					</p>
					<p>
						版权所有  &copy; 2017. <a href="http://www.jetsoon.cn/" title="Jetsoon" target="_blank">Jetsoon</a>
						保留所有权利.
					</p>
		</div>
  
  <script type="text/javascript">
  $(function(){
  		$('#zc').dialog({
  			title:'注册用户',
  			width:400,
  			height:'auto',
  			top:100,
  			modal :true,
  			closed:true,
  			buttons:[
  					 {text:'保存',iconCls:'icon-save',handler:function(){
  						$('#adduser').form('submit',{
  							url:'/HouTai/useradd.json',
  								success:function(data){
  								$('#adduser').form('clear');
  								$('#zc').dialog('close');
  									}
  									});
  						       }},
  						       {text:'取消',iconCls:'icon-cancel',handler:function(){
  									$('#zc').dialog('close');
  							       }}
  						]
  			});
  	});

  function show(){
		$('#zc').dialog('open');
	}
  </script>
</html>
