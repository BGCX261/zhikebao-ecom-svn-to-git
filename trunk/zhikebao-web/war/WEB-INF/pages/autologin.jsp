<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  String nick = (String)request.getAttribute("nick");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自动登录</title>
<%@include file="/commons/meta.jsp"%>
<%@include file="/js/ext/extInclude.jsp"%>
<script type="text/javascript">
    Ext.onReady(function(){
    	Ext.Ajax.request({
    		   url: '<%=ctx%>/j_spring_security_check',
    		   method : 'post',
    		   params: { j_username: '<%=nick%>',
        		   j_password: '<%=nick%>' },
               success: function(){
        		   window.location.href = '<%=ctx%>/zhikebao/main'
               }   
    	});	     
    })
</script>
</head>
<body>

</body>
</html>