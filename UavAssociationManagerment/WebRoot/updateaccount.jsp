<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updateaccount.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/addandupdatetable.css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body> 
  		<center>
  				<p>修改企业信息</p>
  				<form action="enterprise!updateAccount.action" method="post">
  					<s:hidden name="enterpriseUser.companyInformation.eiId" ></s:hidden>	
  					<table class="table">
  						<tr>
  							<td>
  								 账户名称：
  							</td>
  							<td>
  								 <s:textfield name="enterpriseUser.accountName" disabled="true" theme="simple"></s:textfield>
  							</td>
  						</tr>
  						<tr>
  							<td>
  								企业名称：
  							</td>
  							<td>
  								 <s:textfield name="enterpriseUser.companyInformation.companyName" theme="simple"></s:textfield>
  							</td>
  						</tr>
  						<tr>
  							<td>
  								 企业地址：
  							</td>
  							<td>
  								 <s:textfield name="enterpriseUser.companyInformation.companyAddress" theme="simple"></s:textfield>
  							</td>
  						</tr>
  						<tr>
  							<td>
  								企业电话：
  							</td>
  							<td>
  								<s:textfield name="enterpriseUser.companyInformation.companytel" theme="simple"></s:textfield>
  							</td>
  						</tr>
  						<tr>
  							<td>
  								所属行业：
  							</td>
  							<td>
  								<s:textfield name="enterpriseUser.companyInformation.subordinateIndustry" theme="simple"></s:textfield>
  							</td>
  						</tr>
  						<tr>
  							<td  align="center" colspan="2">
  								<input type="submit" title="提交">
  							</td>
  						</tr>
  					</table>
  				</form>
  		</center>
  </body>
</html>
