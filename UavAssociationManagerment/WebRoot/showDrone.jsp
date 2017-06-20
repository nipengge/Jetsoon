<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showDrone.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="http://webapi.amap.com/maps?v=1.3&amp;key=4b2dca9b3ecf3f4018e5d59a24600f05&amp;callback=init"></script>
	<script  type="text/javascript">
		function init(){
			var map = new AMap.Map('container', {
			center: [113.31954,23.123366],
			zoom: 0
		 	});
			map.plugin(["AMap.ToolBar"], function() {
			map.addControl(new AMap.ToolBar());
			});
		}
		init();
	</script>
  </head>
  
  <body>
  	
		<div id="container" style="width:100%; height:100%"></div>
  </body>
</html>
