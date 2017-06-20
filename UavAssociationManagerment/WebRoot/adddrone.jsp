<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!--  录入无人机-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'adddrone.jsp' starting page</title>
    
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
  			<h1>录入无人机信息</h1>
  			
	  			<form action="drone!addDroneInfo.action" method="post" >
		  			<table border="0"  class="table">
		  				<tr>
		  					<td>设备串号：</td>
		  					<td><s:textfield  name="droneInfo.droneId"  theme="simple" /></td>
		  				</tr>
		  					<tr>
		  					<td>
		  						生产厂商:
		  					</td>
		  					<td>
		  						 <s:textfield  name="droneInfo.droneBrand" theme="simple" />
		  					</td>
		  				</tr>
		  				<tr>
		  					<td>
		  						设备型号：
		  					</td>
		  					<td>
		  						<s:textfield  name="droneInfo.droneModel" theme="simple" />
		  					</td>
		  				</tr>
		  				<tr>
		  					<td>
		  						设备荷载：
		  					</td>
		  					<td>
		  						<s:textfield name="droneInfo.droneLoad" theme="simple" />
		  					</td>
		  				</tr>
		  				<tr>
		  					<td>
		  						生产日期：
		  					</td>
		  					<td>
		  						<s:textfield  name="droneInfo.dateManufacture" theme="simple" />
		  					</td>
		  				</tr>
		  				<tr>
		  					<td>
		  						生产批次：
		  					</td>
		  					<td>
		  						 <s:textfield  name="droneInfo.productionLot" theme="simple" />
		  					</td>
		  				</tr>
		  				<tr>
		  					<td>
		  						使用单位名称:
		  					</td>
		  					<td>
		  							<s:textfield  name="droneInfo.useCompanyName" theme="simple" />
		  					</td>
		  				</tr>
		  				<tr>
		  					<td>
		  						行业用途：
		  					</td>
		  					<td>
		  						<s:textfield  name="droneInfo.industry" theme="simple" />
		  					</td>
		  				</tr>
		  				<tr>
		  					<td >
		  						该无人机的驾驶员：
		  					</td>
		  					<td align="center">
		  						<s:select name="droneInfo.dronedriverinfoId" list="droneDriverInfos" listKey="droneDriverInfoId" listValue="driverName" theme="simple" />
		  					</td>
		  				</tr>
		  				<tr>
		  					<td colspan="2" align="center">
		  						<input type="submit" title="提交">
		  					</td>
		  				</tr>
		  			</table>
	  			</form>
  			
  				<p><%String str ="";
	  				if(session.getAttribute("add")!=null){
	  					str = (String)session.getAttribute("add");
	  				}
  				     %>
  					<%=str %>
  					<% session.setAttribute("add",null); %>
  				</p>
  		</center>
  	</body>
</html>
