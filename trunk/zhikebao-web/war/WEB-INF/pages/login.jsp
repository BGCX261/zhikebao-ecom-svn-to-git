<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
	    <%@include file="/commons/meta.jsp"%>
		<%@include file="/js/ext/extInclude.jsp"%>
		<title>知客宝商城应用系统</title>
		<script type="text/javascript">
	window.onload = function() {
		Ext.QuickTips.init();
		Ext.form.Field.prototype.msgTarget = 'side';
		
		var loginForm = new Ext.form.FormPanel( {
			defaultType : 'textfield',
			id : 'loginForm',
			labelAlign : 'right',
			buttonAlign : 'center',
			labelWidth : 120,
			frame : true,
			border : false,
			width : 500,
			autoHeight : true,
			waitMsgTarget : true,
			standardSubmit : true,
			url : '<%=ctx%>/j_spring_security_check',
			defaults : {
				width : 200
			},
			items : [ {
				fieldLabel : '用户帐号',
				name : 'j_username',
				id : 'account',
				allowBlank : false
			}, {
				fieldLabel : '密码',
				name : 'j_password',
				id : 'password',
				inputType : 'password',
				allowBlank : false
			},{
				xtype:'checkbox',
                fieldLabel: '',
                boxLabel: '两周内记住我',
                name: '_spring_security_remember_me'
            }],
			buttons : [ {
				text : '登陆',
				handler : function() {
					loginForm.getForm().submit( {
						clientValidation : true,
						waitMsg : '正在验证用户中,请稍候.....'
					});
				}
			} ]
		});
		/*  在用户校验失败的时候判断是否是服务器连接失败  */
		var onFailure = function(form, action) {
			switch (action.failureType) {
			case Ext.form.Action.CLIENT_INVALID:
				Ext.Msg.show( {
					title : Ext.SubmitMsg.userLoginMessage,
					msg : Ext.SubmitMsg.clientInvalid,
					buttons : Ext.MessageBox.OK,
					icon : Ext.MessageBox.ERROR
				});
				break;
			case Ext.form.Action.CONNECT_FAILURE:
				Ext.Msg.show( {
					title : Ext.SubmitMsg.userLoginMessage,
					msg : Ext.SubmitMsg.connectFailure,
					buttons : Ext.MessageBox.OK,
					icon : Ext.MessageBox.ERROR
				});
				break;
			case Ext.form.Action.SERVER_INVALID:
				Ext.Msg.show( {
					title : Ext.SubmitMsg.userLoginMessage,
					msg : action.result.errorMsg,
					buttons : Ext.MessageBox.OK,
					icon : Ext.MessageBox.ERROR
				});
			}
		}
		/*用户校验成功之后的动作 */
		var onLoginSuccess = function(form, action) {
			win.close();
			document.location.href = action.result.errorMsg;
		}
		/* 登录是需要使用的表单放在这个弹出窗口中  */
		var win = new Ext.Window( {
			el : 'window-win',
			title : '用户登陆',
			layout : 'fit',
			width : 400,
			height : 180,
			closable : false,
			items : [ loginForm ],
			buttons : []
		});
		win.show();
	}
</script>
	</head>
	<body style="margin:0px">
		<div id="window-win"></div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" height="100%">
			<tr>
				<td height="245" background="<%=ctx%>/themes/default/images/login/top.jpg">
					&nbsp;
				</td>
				<td height="245" background="<%=ctx%>/themes/default/images/login/top2.jpg">
				</td>
			</tr>
			<tr>
				<td width="450" height="199" background="<%=ctx%>/themes/default/images/login/middleleft.jpg">
				</td>
				<td height="199" width="" background="<%=ctx%>/themes/default/images/login/middleright.jpg">
					&nbsp;
				</td>
			</tr>
			<tr height="30">
				<td colspan="3" bgcolor="#00427F">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td colspan="3"  style="font-size:12px; color:#615555; font-family:微软雅黑,Arial;" background="<%=ctx%>/themes/default/images/login/bottom.jpg">
					&copy; 2009&nbsp; 杭州良格软件科技有限公司 &nbsp;版权所有
				</td>
			</tr>
		</table>
	</body>
</html>

