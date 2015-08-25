<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<html>
<head>
	<title>知客宝商城应用系统</title>
	<%@include file="/commons/meta.jsp"%>  
	<%@include file="/js/ext/extInclude.jsp"%>
	<script type="text/javascript">
			window.onload =function(){
			Ext.QuickTips.init();
			Ext.form.Field.prototype.msgTarget = 'side';
		    var loginForm = new Ext.form.FormPanel({
		        defaultType: 'textarea',
		        id: 'loginForm',
		    	buttonAlign: 'center',
		        labelWidth: 45,
		        frame: true,
		        border:false,
		        width: 500,
		        autoHeight: true,
		        waitMsgTarget: true,
		    	defaults: {width: 200},
		        items: [{
		        	xtype:'label',
		        	labelAlign: 'left',
		        	style: 'padding-top:10px;padding-bottom:10px',                  
		        	html:  '"知客宝商城应用系统" 需要您的淘宝TOP授权码.<em><a href="http://open.taobao.com/isv/authorize.php?appkey=12012509" target="_blank">获取淘宝授权码</a></em>',
		        	anchor:'100%'
		        },{
		        	xtype:'textarea',
		        	labelAlign: 'right',
		        	fieldLabel: '授权码',
		        	name: 'authcode',
		        	anchor:'100%',
		        	grow: true,
		        	maxLength:100,
		        	growMin: 80,
		        	growMax:80
		        }],
		        buttons: [{
		            text: '确定授权',
		            handler: function(){
		            	window.location.href = "http://container.api.tbsandbox.com/container?authcode=" + loginForm.getForm().getValues().authcode;
		            }
		        }]
		    });
			/* 登录是需要使用的表单放在这个弹出窗口中  */
		    var win = new Ext.Window({
		        el:'window-win',
		        title: '用户授权',
		        layout:'fit',
		        closable : false,
		        width:600,
		        height:200,
		        items: [loginForm],
		        buttons: []
		    });
			win.show();
		}
	</script>
</head>
<body style="margin:0px">
		<div id="window-win"></div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" height="100%">
			<tr>
				<td height="245" background="${pageContext.request.contextPath}/themes/default/images/login/top.jpg">
					&nbsp;
				</td>
				<td height="245" background="${pageContext.request.contextPath}/themes/default/images/login/top2.jpg">
				</td>
			</tr>
			<tr>
				<td width="450" height="199" background="${pageContext.request.contextPath}/themes/default/images/login/middleleft.jpg">
				</td>
				<td height="199" width="" background="${pageContext.request.contextPath}/themes/default/images/login/middleright.jpg">
					&nbsp;
				</td>
			</tr>
			<tr height="30">
				<td colspan="3" bgcolor="#00427F">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td colspan="3" style="font-size:12px; color:#615555; font-family:微软雅黑,Arial;" background="${pageContext.request.contextPath}/img/login/bottom.jpg">
					&copy; 2009&nbsp; 良格软件科技有限公司 &nbsp;版权所有
				</td>
			</tr>
		</table>
	</body>
</html>

