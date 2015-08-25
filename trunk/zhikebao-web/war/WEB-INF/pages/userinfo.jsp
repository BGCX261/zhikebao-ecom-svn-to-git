<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
	    <%@include file="/commons/meta.jsp"%>
		<%@include file="/js/ext/extInclude.jsp"%>
		<title>SaSa淘宝应用系统</title>
		<script type="text/javascript">
	window.onload = function() {
		Ext.QuickTips.init();
		Ext.form.Field.prototype.msgTarget = 'side';
		var loginForm = new Ext.form.FormPanel( {
			defaultType : 'textfield',
			renderTo : Ext.getBody(),
			labelAlign : 'right',
			buttonAlign : 'center',
			labelWidth : 120,
			frame : true,
			title : '用户信息',
			border : false,
			width : 500,
			autoHeight : true,
			waitMsgTarget : true,
			defaults : {
				width : 200
			},
			items : [ {
				fieldLabel : '用户名称',
				name : 'name',
				value : '${sessionUser.name}',
				id : 'name',
				allowBlank : false
			}, {
				fieldLabel : '密码',
				name : 'pwd',
				inputType : 'password',
				value : '${sessionUser.pwd}',
				id : 'pwd'
			}, {
				fieldLabel : '确认密码',
				name : 'pass-cfrm',
				inputType : 'password',
				value : '${sessionUser.pwd}',
				vtype : 'password',
				initialPassField : 'pwd' // id of the initial password field
			} ],
			buttons : [ {
				text : '保存',
				handler : function() {
					loginForm.getForm().submit( {
						clientValidation : true,
						timeout : 1000 * 60 * 10,
						url : 'user.do?par=save',
						waitMsg : '正在保存用户信息中,请稍候.....',
						success : onLoginSuccess,
						failure : onFailure
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
			Ext.example.single("个人信息", action.result.errorMsg);
		}
	}
</script>
	</head>
	<body BGCOLOR='#FFFFFF'>
	</body>
</html>

