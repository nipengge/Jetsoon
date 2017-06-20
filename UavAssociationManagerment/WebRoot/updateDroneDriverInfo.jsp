<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updateDroneDriverInfo' starting page</title>
    
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
  			<h1>修改无人机驾驶员信息</h1>
  			<form action="drone!updateDroneDriverInfo.action" method="post">
  				<s:hidden name="droneDriverInfo.droneDriverInfoId"></s:hidden>
  				<table class="table">
  					<tr>
  						<td>
  							驾驶员姓名：
  						</td>
  						<td>
  							<s:textfield name="droneDriverInfo.driverName" theme="simple"></s:textfield>
  						</td>
  					</tr>
  					<tr>
  						<td>
  							身份证号：
  						</td>
  						<td>
  							<s:textfield name="droneDriverInfo.idCard" theme="simple"></s:textfield>
  						</td>
  					</tr>
  					<tr>
  						<td>
  							驾驶证号：
  						</td>
  						<td>
  							<s:textfield name="droneDriverInfo.droneIdCard" theme="simple"></s:textfield>
  						</td>
  					</tr>
  					<tr>
  						<td>
  							发证单位：
  						</td>
  						<td>
  							<s:textfield name="droneDriverInfo.issuingUnit" theme="simple"></s:textfield>
  						</td>
  					</tr>
  					<tr>
  						<td colspan="2" align="center">
  							<input type="submit" title="提交">
  						</td>
  					</tr>
  				</table>
  				
  			</form>
  		</center>
  </body>
</html>
