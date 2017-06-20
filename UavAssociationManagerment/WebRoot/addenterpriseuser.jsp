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
    
    <title>My JSP 'addenterpriseuser.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
			<style type="text/css">
		/*分别定义HTML中和标记之的距离样式*/
		html, body, h1, form, fieldset, legend, ol, li {
		    margin: 0;
		    padding: 0;
		}
		/*定义<body>标记样式*/
		body {
		    background: #ffffff;
		    color: #111111;
		    font-family: Georgia, "Times New Roman", Times, serif;
		    padding-left: 20px;
		    
		}
		/*定义付费内容的样式*/
		form#payment {
		    background: #9cbc2c;
		    -webkit-border-radius: 5px;
		    border-radius: 5px;
		    padding: 20px;
		    width: 400px;
		    margin:auto;
		}
		form#payment fieldset {
		    border: none;
		    margin-bottom: 10px;
		}
		form#payment fieldset:last-of-type { margin-bottom: 0; }
		form#payment legend {
		    color: #384313;
		    font-size: 16px;
		    font-weight: bold;
		    padding-bottom: 10px;
		    text-shadow: 0 1px 1px #c0d576;
		}
		form#payment > fieldset > legend:before {
		    content: "Step " counter(fieldsets) ": ";
		    counter-increment: fieldsets;
		}
		form#payment fieldset fieldset legend {
		    color: #111111;
		    font-size: 13px;
		    font-weight: normal;
		    padding-bottom: 0;
		}
		form#payment ol li {
		    background: #b9cf6a;
		    background: rgba(255, 255, 255, .3);
		    border-color: #e3ebc3;
		    border-color: rgba(255, 255, 255, .6);
		    border-style: solid;
		    border-width: 2px;
		    -webkit-border-radius: 5px;
		    line-height: 30px;
		    list-style: none;
		    padding: 5px 10px;
		    margin-bottom: 2px;
		}
		form#payment ol ol li {
		    background: none;
		    border: none;
		    float: left;
		}
		form#payment label {
		    float: left;
		    font-size: 13px;
		    width: 110px;
		}
		form#payment fieldset fieldset label {
		    background: none no-repeat left 50%;
		    line-height: 20px;
		    padding: 0 0 0 30px;
		    width: auto;
		}
		form#payment fieldset fieldset label:hover { cursor: pointer; }
		form#payment input:not([type=radio]), form#payment textarea {
		    background: #ffffff;
		    border: #FC3 solid 1px;
		    -webkit-border-radius: 3px;
		    font: italic 13px Georgia, "Times New Roman", Times, serif;
		    outline: none;
		    padding: 5px;
		    width: 200px;
		}
		form#payment input:not([type=submit]):focus, form#payment textarea:focus {
		    background: #eaeaea;
		    border: #F00 solid 1px;
		}
		form#payment input[type=radio] {
		    float: left;
		    margin-right: 5px;
		}
		</style>

  </head>
  
  <body>
  			<center>
  				<h1>企业用户账号注册</h1>
  			</center>
		  <form id="payment" action="login!registeredAccount.action"  method="post">
		    <fieldset>
		        <legend>用户详细资料</legend>
		        <ol>
		            <li>
		                <label for="name">企业账户：</label>
		                <s:textfield id="name" name="enterpriseUser.accountName"   placeholder="请输入用户名"  required="true" />
		            </li>
		            <li>
		                <label for="email">密码：</label>
		                <s:password id="email" name="enterpriseUser.euPassword"  placeholder="*****" required="true"  />
		            </li>
		             <li>
		                <label for="email">再次输入密码：</label>
		                <input id="email" name="password"  type="password" placeholder="*****" required />
		            </li>
		             <li>
		                <label for="address">公司名称：</label>
		                <s:textarea id="address" name="enterpriseUser.companyInformation.companyName" rows="1" required="true"  />
		            </li>
		             <li>
		                <label for="address">详细地址：</label>
		                <s:textarea id="address" name="enterpriseUser.companyInformation.companyAddress" rows="1"  placeholder="北京市朝阳区xxxx大厦xxx栋xxx号" required="true" />
		            </li>
		            <li>
		                <label for="phone">联系电话：</label>
		                <s:textfield id="phone" name="enterpriseUser.companyInformation.companytel"  placeholder="010-/87564321"  required="true" />
		            </li>
		            <li>
		                <label for="postcode">所属行业：</label>
		                <s:textfield id="postcode" name="enterpriseUser.companyInformation.subordinateIndustry"  placeholder="农业/工业/服务/电力"  required="true"/>
		            </li>
		        </ol>
		    </fieldset>
		    
		        <button type="submit">注册</button>
		    </fieldset>
	</form>
  		
  </body>
</html>
