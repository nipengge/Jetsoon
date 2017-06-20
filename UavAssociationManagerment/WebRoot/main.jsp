<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.jetsoon.bean.Enterprise_User"%>
<%@page import="com.jetsoon.bean.Backstage_User"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>深圳无人机协会无人机管控调度系统-主页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=path %>/css/global.css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>		
  				<!-- 顶部导航栏 -->
	  	        <div id="left-flyout-nav" class="layout-left-flyout visible-sm"></div>
			<div class="layout-right-content">
			  <header class="the-header">
			    <div class="navbar container">
			      
			      <!-- Trigger -->
			      <a class="btn-navbar btn-navbar-navtoggle btn-flyout-trigger" href="javascript:;">
			        <span class="icon-bar btn-flyout-trigger"></span>
			        <span class="icon-bar btn-flyout-trigger"></span>
			        <span class="icon-bar btn-flyout-trigger"></span>
			      </a>
			      
			      <!-- Structure -->
			      <nav class="the-nav nav-collapse clearfix">
			        <ul class="nav nav-pill pull-left">
			          <s:if test="#session.backUser!=null">
			          <li class="dropdown">
			            <a href="main.jsp">企业管理<b class="caret"></b></a>
			            <ul class="subnav">
			              <li><a href="enterprise!queryNotChecked.action?enterpriseUser.pagerBean.newPage=1" target="ifrPage">企业账户管理</a></li>
			            </ul>
			          </li>
			          </s:if>
			          <li class="dropdown">
			            <a href="main.jsp">无人机管理<b class="caret"></b></a>
			            <ul class="subnav">
			            <s:if test="#session.backUser==null">
			              <li><a href="drone!toAddDrone.action" target="ifrPage">录入无人机</a></li>
			             </s:if>
			              <li><a href="drone!qureyCompanyDroneInfo.action?droneInfo.pagerBean.newPage=1" target="ifrPage">查看所有无人机</a></li>
			            </ul>
			          </li>
			          <li class="dropdown">
			            <a href="main.jsp">驾驶员管理<b class="caret"></b></a>
			            <ul class="subnav">
			              <s:if test="#session.backUser==null">
			              <li><a href="adddronedriver.jsp"  target="ifrPage">驾驶员添加</a></li>
			               </s:if>
			              <li><a href="drone!qureyCompanyDroneDriver.action?droneDriverInfo.pagerBean.newPage=1"  target="ifrPage">驾驶员列表</a></li>
			            </ul>
			          </li>
			          <li class="dropdown">
			            <a href="main.jsp">飞机追踪  <b class="caret"></b></a>
			            <ul class="subnav">
			              <li><a href="showDrone.jsp" target="ifrPage">无人机位置</a></li>
			            </ul>
			          </li>
			           <li class="dropdown">
				            <a href="main.jsp">行业管理  <b class="caret"></b></a>
				            <ul class="subnav">
				                <li>
								    <a href="main.jsp">电力  </a>
							    </li>
							    <li>
								    <a href="main.jsp">林业 </a>
							    </li>
							    <li>
								    <a href="main.jsp">交通 </a>
							    </li>
							    <li>
								    <a href="main.jsp">公安 </a>
							    </li>
							    <li>
								    <a href="main.jsp">农业  </a>
							    </li>
							    <li>
								    <a href="main.jsp">测绘 </a>
							    </li>
							    <li>
								    <a href="main.jsp">航拍 </a>
							    </li>
				            </ul>
			          	</li>
			        	<li class="dropdown">
				            <a href="main.jsp">驾驶与培训  <b class="caret"></b></a>
				             <ul class="subnav">
			              		<li><a href="showDrone.jsp" target="ifrPage">驾驶员验证</a></li>
			              		<li><a href="showDrone.jsp" target="ifrPage">全球鹰培训</a></li>
			            	</ul>
			          	</li>
			          
			       </ul>
			        <ul class="nav nav-pill pull-right">
			          <li><a href="#">
			          		<% String str = "";
			          			if(session.getAttribute("user")!=null){
			          				Enterprise_User  user =  (Enterprise_User)session.getAttribute("user");
			          				str = user.getAccountName();
			          			}else if(session.getAttribute("backUser")!=null){
			          			   Backstage_User backUser = (Backstage_User)session.getAttribute("backUser");
			          			   str = backUser.getUserName()+"(管理员)";
			          			}
			          			str = str+",欢迎你！";
			          		 %>
			          		 <%=str %>
			          	</a></li>
			          <li><a href="login!signOut.action">退出登录</a></li><!--
			        --></ul>
			      </nav>
			    </div>
			  </header>
			</div>
	        <!-- END .layout-left-flyout -->
			<script src="<%=path %>/js/jquery.js"></script>
	        <script src="<%=path %>/js/jquery.cbFlyout.js"></script>
	        <script>
				$(document).ready(function(){
					$('.the-nav').cbFlyout();
				});
	        </script>
	        
			<div style="text-align:center;margin:0px 0; font:normal 14px/24px 'MicroSoft YaHei';"> 
				<iframe id="ifrPage" name="ifrPage" width="100%" height="100%" src="drone!qureyCompanyDroneInfo.action?droneInfo.pagerBean.newPage=1">
				
				</iframe>
				<p >适用浏览器：360、FireFox、Chrome、Safari、Opera、傲游、搜狗、世界之窗. 不支持IE8及以下浏览器。</p>
				<p>关于我们：<a href="http://www.szuavia.org/" target="_blank">深圳无人机协会</a></p>
			</div>
  </body>
</html>
