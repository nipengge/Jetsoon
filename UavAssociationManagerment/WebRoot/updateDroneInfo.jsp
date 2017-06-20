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
    
    <title>My JSP 'updateDroneInfo.jsp' starting page</title>
    
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
  			<h1>修改无人机信息</h1>
  			<form action="drone!updateDroneInfo" method="post">
  				<s:hidden name="droneInfo.diId"></s:hidden>
  				<table class="table"">
  					<tr>
  						<td>
  							无人机串号：
  						</td>
  						<td>
  							<s:textfield name="droneInfo.droneId" theme="simple" ></s:textfield>
  						</td>
  					</tr>
  					<tr>
  						<td>
  							品牌：
  						</td>
  						<td>
  							<s:textfield name="droneInfo.droneBrand" theme="simple" ></s:textfield>
  						</td>
  					</tr>
  					<tr>
  						<td>
  							型号：
  						</td>
  						<td>
  								<s:textfield name="droneInfo.droneModel" theme="simple" ></s:textfield>
  						</td>
  					</tr>
  					<tr>
  						<td>
  							荷载：
  						</td>
  						<td>
  							<s:textfield name="droneInfo.droneLoad" theme="simple" ></s:textfield>
  						</td>
  					</tr>
  					<tr>
  						<td>
  							生产日期：
  						</td>
  						<td>
  							<s:textfield name="droneInfo.dateManufacture" theme="simple" ></s:textfield>
  						</td>
  					</tr>
  					<tr>
  						<td>
  							生产批次：
  						</td>
  						<td>
  							<s:textfield name="droneInfo.productionLot" theme="simple" ></s:textfield>
  						</td>
  					</tr>
  					<tr>
  						<td>
  							使用单位名称：
  						</td>
  						<td>
							<s:textfield name="droneInfo.useCompanyName" theme="simple" ></s:textfield>
  						</td>
  					</tr>
  					<tr>
  						<td>
  							行业用途：
  						</td>
  						<td>
  							<s:textfield name="droneInfo.industry" theme="simple" ></s:textfield><br/>
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
