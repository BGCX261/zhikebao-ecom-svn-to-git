<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<%@include file="/js/ext/extInclude.jsp"%>
		<title>SaSa淘宝应用系统</title>
		<script type="text/javascript">
	window.onload = function() {
		Ext.QuickTips.init();
		Ext.form.Field.prototype.msgTarget = 'side';
		var loginForm = new Ext.form.FormPanel(
				{
					renderTo : Ext.getBody(),
					monitorValid: true,
					draggable:true,
					labelAlign : 'right',
					buttonAlign : 'center',
					labelWidth : 80,
					frame : true,
					title : '取发货地址',
					border : false,
					width : '100%',
					waitMsgTarget : true,
					items : [ {
						layout : 'column',
						border : false,
						items : [
								{
									columnWidth : .3,
									layout : 'form',
									border : false,
									items : [ {
										xtype : 'textfield',
										fieldLabel : '姓名',
										allowBlank: false,
										value:'${adb.name}',
										name : 'name',
										anchor : '90%'
									}, {
										xtype : 'textfield',
										fieldLabel : '省',
										allowBlank:false,
										value : '${adb.state}',
										name : 'state',
										anchor : '90%'
									}, {
										xtype : 'textfield',
										fieldLabel : '邮政编码',
										value : '${adb.zip}',
										allowBlank:false,
										name : 'zip',
										anchor : '90%'
									} ]
								},
								{
									columnWidth : .3,
									layout : 'form',
									border : false,
									items : [ {
										xtype : 'textfield',
										fieldLabel : '手机号码',
										value : '${adb.mobile}',
										allowBlank:false,
										name : 'mobile',
										anchor : '90%'
									}, {
										xtype : 'textfield',
										fieldLabel : '城市',
										value : '${adb.city}',
										allowBlank:false,
										name : 'city',
										anchor : '90%'
									}, {
										xtype : 'textfield',
										fieldLabel : '标准地区码',
										allowBlank:false,
										value : '${adb.areaid}',
										name : 'areaid',
										anchor : '90%'
									} ]
								},
								{
									columnWidth : .3,
									layout : 'form',
									border : false,
									items : [
											{
												xtype : 'textfield',
												fieldLabel : '电话号码',
												value : '${adb.phone}',
												allowBlank:false,
												name : 'phone',
												anchor : '90%'
											},
											{
												xtype : 'textfield',
												fieldLabel : '地区',
												allowBlank:false,
												value : '${adb.district}',
												name : 'district',
												anchor : '90%'
											},
											{
												xtype : 'label',
												html : '<a href="http://www.stats.gov.cn/tjbz/xzqhdm/t20080215_402462675.htm" target="_blank">国家公布的标准地区码</a>',
												anchor : '50%'
											} ]
								}, {
									columnWidth : .90,
									layout : 'form',
									border : false,
									items : [ {
										xtype : 'textfield',
										allowBlank:false,
										value : '${adb.address}',
										fieldLabel : '街道地址',
										name : 'address',
										anchor : '90%'
									} ]
								} ]
					} ],
					buttons : [ {
						text : '保存',
						handler : function() {
							loginForm.getForm().submit( {
								clientValidation : true,
								timeout : 1000 * 60 * 10,
								url : 'sendAddress.do?par=save',
								waitMsg : '正在保存发货地址中,请稍候.....',
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
			Ext.example.single("发货地址", action.result.errorMsg);
		}
	}
</script>
	</head>
	<body BGCOLOR='#FFFFFF'>
	</body>
</html>

