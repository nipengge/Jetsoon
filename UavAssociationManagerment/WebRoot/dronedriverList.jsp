<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.jetsoon.bean.PagerBean"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'dronedriverList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/table.css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    	<center>
    		<h1>驾驶员列表</h1>
  			 </center><!--
  				<form name="from1" id="from1" action="enterprise!queryNotChecked.action" method="post">
  					<h2>筛选:
  						<s:select onchange="submitForm();" name="enterpriseUser.statu" list="#{1:'未审核',2:'已审核',3:'已禁用'}"  headerKey="0" headerValue="显示所有" listKey="key" listValue="value">
  							账号状态 
  						</s:select>
  						
  						公司名称:<s:textfield name="enterpriseUser.companyInformation.companyName"/>
  						<input type="submit" title="提交">
  						</h2>
  					
  				</form>
  			 --><table width="100%" class="table" >
	    	<tr>
	    		<td>驾驶员编号</td>
	    		<td>驾驶员姓名</td>
	    		<td>驾驶员身份证号</td>
	    		<td>驾驶证号</td>
	    		<td>发证单位</td>
	    		<td>操作</td>
	    	</tr>
	    	<s:iterator value="droneDriverInfos" var="droneDriverInfo">
	    		<tr class="alter">
	    			<td>
	    				<s:property value="#droneDriverInfo.droneDriverInfoId"/>
	    			</td>
	    			<td>
	    				<s:property value="#droneDriverInfo.driverName"/>
	    			</td>
	    			<td>
	    				<s:property value="#droneDriverInfo.idCard"/>
	    			</td>
	    			<td>
	    				<s:property value="#droneDriverInfo.droneIdCard"/>
	    			</td>
	    			<td>
	    				<s:property value="#droneDriverInfo.issuingUnit"/>
	    			</td>
	    			<td><!--
	    				<a href="#">删除</a>
	    				<br/>
	    				--><a href="drone!toUpdateDroneDriverInfo.action?droneDriverInfo.droneDriverInfoId=<s:property value="#droneDriverInfo.droneDriverInfoId"/>">修改</a>
	    			</td>
	    			<!--<td>
	    				<s:if test="#enterpriseUser.statu==1">未审核</s:if>
	    				<s:if test="#enterpriseUser.statu==2">已开通</s:if>
	    				<s:if test="#enterpriseUser.statu==3">已禁用</s:if>
	    			</td>
	    			<td>
	    				<s:if test="#enterpriseUser.statu==1">
	    					<a href="enterprise!throughAudit.action?enterpriseUser.euId=<s:property value="#enterpriseUser.euId"/>&enterpriseUser.accountName=<s:property value="#enterpriseUser.accountName"/>">通过审核</a><br/>
	    				</s:if >
	    				
	    				<s:if test="#enterpriseUser.statu==2">
	    					<a href="enterprise!disableAccount.action?enterpriseUser.euId=<s:property value="#enterpriseUser.euId"/>&enterpriseUser.accountName=<s:property value="#enterpriseUser.accountName"/>">禁用此账号</a>
	    				</s:if>
	    				
	    				<s:if test="#enterpriseUser.statu==3">
	    					<a href="enterprise!throughAudit.action?enterpriseUser.euId=<s:property value="#enterpriseUser.euId"/>&enterpriseUser.accountName=<s:property value="#enterpriseUser.accountName"/>">启用此账号</a>
	    				</s:if>
	    				<a href="#">修改</a>
	    			</td>
	    		--></tr>
	    	</s:iterator>
	    </table>
	    <div style="height:30px;"></div>
		    <div style="whidth:800px;float:right;">
		  <form action="drone!qureyCompanyDroneDriver.action" method="post" name="form1"> 
		    当前第  <select name="droneDriverInfo.pagerBean.newPage" onchange="form1.submit();">
		    	<%
		    		PagerBean p = (PagerBean)session.getAttribute("page");
		    		for(int i=1;i<=p.getAllPage();i++){
		      	%>
		      	<option value="<%=i %>" <%=i==p.getNewPage()?"selected":"" %>><%=i %></option>
		    	 <% }%>
		    </select>
		    页,共${page.allPage }页,每页显示
		    <!--<select name="num" onchange="form1.submit();">
		    	<%
		    	for(int i=5;i<=20;i++){
		    	 %>
		    	 <option value="<%=i %>" <%=i==p.getNum()?"selected":"" %>><%=i %></option>
		    	 <%} %>
		    </select>-->${page.num }
		    条记录,共${page.count }条记录
		    &nbsp;&nbsp;&nbsp;
		    <a href="drone!qureyCompanyDroneDriver.action?droneDriverInfo.pagerBean.newPage=1">首页</a>&nbsp;&nbsp;&nbsp;
		    <a href="drone!qureyCompanyDroneDriver.action?droneDriverInfo.pagerBean.newPage=${page.newPage-1 }">上一页</a>&nbsp;&nbsp;&nbsp;
		    <a href="drone!qureyCompanyDroneDriver.action?droneDriverInfo.pagerBean.newPage=${page.newPage+1 }">下一页</a>&nbsp;&nbsp;&nbsp;
		    <a href="drone!qureyCompanyDroneDriver.action?droneDriverInfo.pagerBean.newPage=${page.allPage }">尾页</a>
		    </form>
		    </div>
	    <center>
	    	<p>
	    		<%  String log ="";
	    			String code =(String) session.getAttribute("code");
	    			String accountName = (String) session.getAttribute("accountName");
	    			if(code != null && code.equals("ThroughAudit")){
	    				log = accountName+"账号通过审核成功";
	    			}else if(code != null && code.equals("Not ThroughAudit")){
	    			    log = accountName+"通过审核失败";
	    			}else if(code != null && code.equals("DisableAccount")){
	    				 log = accountName+"账号禁用成功";
	    			}else if(code != null && code.equals("Not DisableAccount")){
	    				log = accountName+"账号禁用失败";
	    			}
	    			session.setAttribute("code",null);
	    		 %>
	    		 <%=log %>
	    	</p>
	  </center>
  </body>
</html>
