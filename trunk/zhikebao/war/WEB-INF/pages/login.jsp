<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter"%>
<%
    Date date = new Date();
    SimpleDateFormat  sdf = new SimpleDateFormat("yyyy年MM月dd日");
    String today = sdf.format(date);
%>

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>欢迎使用知客宝服务系统</title>
<meta name="gwt:property" content="locale=zh">
<!-- CSS -->
<link rel="stylesheet" href="/themes/default/images/login/dynamic.css" type="text/css">
<link rel="stylesheet" href="/themes/default/images/login/login.css" type="text/css">
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow: hidden;
}
-->
</style>
<script>
  function checkForm()
  {
	  var un = document.getElementById("emailLogin");
		var pwd = document.getElementById("passwordLogin");
		if(un.value=='')
		{
            alert("请输入用户名");
            un.focus();
            return false;
		}
		if(pwd.value=='')
		{
            alert("请输入密码");
            pwd.focus();
            return false;
		}
		//alert(un.value);
		//alert(pwd.value);
		return true;
  }
	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0"
	height="100%">
	<tr>
		<td height="245" background="/themes/default/images/login/top.jpg">
		&nbsp;</td>
		<td height="245" background="/themes/default/images/login/top2.jpg">
		&nbsp;</td>
	</tr>
	<tr>
		<td width="450"
			background="/themes/default/images/login/middleleft.jpg">&nbsp;</td>
		<td height="245" width=""
			background="/themes/default/images/login/middleright.jpg">
		<div style="margin-top: -256px; visibility: visible;" id="lightbox"
			class="done">
		<div id="lbContent" class="clearfix">
		<form id="loginForm" name="f"
			action="/zhikebao/j_spring_security_check" method="post" onsubmit="return checkForm();">
		<div class="info">
		<h2><%=today %></h2>
		<div>欢迎登录系统!</div>
		</div>
		<ul>
			<%
			Object err = (Object)session.getAttribute(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY);
				if ( err!= null) {
			%>
			<li>
			<div><span style="color: red">登录不成功，请重试</span></div>
			</li>
			<%
				}
			%>

			<li>
			<div><label class="desc" for="emailLogin">用户名</label> 
			  <c:if test="${users!=null}">
			    <form:select
				id="emailLogin" path="j_username" name="j_username" items="${users}"
				itemValue="username" itemLabel="username"
				cssClass="field select large" />
				</c:if>
				<c:if test="${users==null}">
			       <input id="emailLogin" name="j_username"  class="field text large" />
				 </c:if>
				</div>
			</li>

			<li>
			<div><label class="desc" for="passwordLogin">密码</label> 
			   <input
				id="passwordLogin" name="j_password" class="field text large" value="${j_password}" type="password"></div>
			</li>
			<li><span> <input id="remember"
				name="_spring_security_remember_me" class="checkbox field" value="1"
				type="checkbox"> <label class="choice" for="remember">两周内记住我</label>
			</span></li>
			<li>
			<button type="submit" id="saveForm" class="button"><img
				src="/themes/default/images/login/door_in.png" alt=""> 登录</button>
			<a href="www.taobao.com" class="negative button"> <img
				src="/themes/default/images/login/cross.png" alt=""> 取消</a></li>
		</ul>

		<br>

		<a href="https://secure.wufoo.com/login/forgot/"
			title="Don't worry, we'll get you a new one."> 忘记密码?</a> · <a
			href="http://www.zhikebao.com" title="Read our Login FAQ"
			target="_blank"> 用户手册</a></form>
		</div>
		</div>
		</td>
	</tr>
	<tr height="30">
		<td colspan="3" bgcolor="#00427F">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="3"
			style="font-size: 12px; color: #615555; font-family: 微软雅黑, Arial;"
			background="/themes/default/images/login/bottom.jpg">&copy;
		2009&nbsp; 杭州良格软件科技有限公司 &nbsp;版权所有</td>
	</tr>
</table>
<script src='zkb/sc/modules/ISC_Foundation.js'></script>
<script src='zkb/sc/modules/ISC_Containers.js'></script>
<script src='zkb/sc/modules/ISC_Grids.js'></script>
<script src='zkb/sc/modules/ISC_Forms.js'></script>
<script src='zkb/sc/modules/ISC_RichTextEditor.js'></script>
<script src='zkb/sc/modules/ISC_Calendar.js'></script>
</body>
</html>
