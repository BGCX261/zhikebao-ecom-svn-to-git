<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<%@include file="/commons/meta.jsp"%>
		<%@include file="/js/ext/extInclude.jsp"%>
		<script type="text/javascript">
	window.onload = function() {
		var xg = Ext.grid;
		var track;
		var trackList;
		var grid;
		var trackXML;
		Ext.QuickTips.init();
		Ext.form.Field.prototype.msgTarget = 'side';

		track = Ext.data.Record.create( [ {
			name : 'id'
		}, {
			name : 'picPath'
		}, {
			name : 'title'
		}, {
			name : 'outerIid'
		}, {
			name : 'colorName'
		}, {
			name : 'sizeName'
		}, {
			name : 'num',
			type : 'int'
		}, {
			name : 'price',
			type : 'float'
		}, {
			name : 'payment',
			type : 'float'
		}, {
			name : 'totalFee',
			type : 'float'
		}, {
			name : 'discountFee',
			type : 'float'
		}, {
			name : 'adjustFee',
			type : 'float'
		}, {
			name : 'refundStatus'
		}, {
			name : 'refundDesc'
		} ]);

		trackXML = new Ext.data.XmlReader( {
			totalRecords : "total",
			record : "record",
			id : "id"
		}, track);

		trackList = new Ext.data.Store( {
			proxy : new Ext.data.HttpProxy( {
				url : 'order.do?par=order&tid=' + '${trade.tid}'
			}),
			autoLoad : true,
			reader : trackXML
		});

		trackList.on('loadexception', function() {
			Ext.Msg.alert('loadexception', '请与官方网站联系或稍后再试');
		});

		function renderTopic(value, p, record, rowIndex, colIndex) {
			var headerName = grid.getColumnModel().getDataIndex(colIndex);
			if (headerName == 'picPath') {
				return String.format('<img src="{0}" height="30"/>', value,
						record.id);
			}
			if (headerName == 'refundStatus') {
				if (value == 'CLOSED') {
					return String.format('<font color=red>{0}</font>', "买家已退款");
				}
				if (value == 'NO_REFUND') {
					return String.format('{0}', "没有退款");
				}
			}
		}
		var sm = new xg.CheckboxSelectionModel( {
			singleSelect : true
		});

		sm.on('rowselect', function(el, rowIndex, r) {
			viewForm.getForm().loadRecord(r);
			if (r.get('refundStatus') != 'CLOSED') {
				Ext.getCmp('updataButton').setDisabled(false);
			} else {
				el.deselectRow(rowIndex);
			}
		});

		sm.on('rowdeselect', function(el, rowIndex, r) {
			Ext.getCmp('updataButton').setDisabled(true);
		})

		var cm = new xg.ColumnModel( [ sm, {
			id : 'id',
			header : "商品",
			dataIndex : 'picPath',
			renderer : renderTopic,
			width : 60,
			align : 'center'
		}, {
			header : "款号",
			width : 150,
			align : 'center',
			dataIndex : 'outerIid'
		}, {
			header : "商品名称",
			dataIndex : 'title',
			width : 150
		}, {
			header : "颜色",
			dataIndex : 'colorName',
			width : 80
		}, {
			header : "尺寸",
			dataIndex : 'sizeName',
			width : 80
		}, {
			header : "退款状态",
			dataIndex : 'refundStatus',
			renderer : renderTopic,
			width : 100
		}, {
			header : "单价",
			dataIndex : 'price',
			width : 100,
			renderer : 'usMoney'
		}, {
			header : "数量",
			dataIndex : 'num',
			width : 100
		}, {
			header : "商品总价",
			dataIndex : 'totalFee',
			renderer : 'usMoney',
			width : 100
		}, {
			header : "系统优惠",
			dataIndex : 'discountFee',
			renderer : 'usMoney',
			width : 100
		}, {
			header : "卖家优惠",
			dataIndex : 'adjustFee',
			renderer : 'usMoney',
			width : 100
		} ]);
		cm.defaultSortable = true;
		grid = new xg.GridPanel( {
			id : 'grid',
			store : trackList,
			autoWidth : true,
			loadMask : {
				msg : '正在加载数据...'
			},
			border : false,
			cm : cm,
			sm : sm,
			autoScroll : true,
			viewConfig : {
				forceFit : true,
				getRowClass : function(record, rowIndex, p, store) {
					if (record.get('refundStatus') == 'CLOSED') {
						return 'x-grid-row-lock'
					}
				}
			},
			plugins : new Ext.ux.PanelResizer( {
				minHeight : 200
			}),
			height : 220
		});

		//选择是否橱窗推荐组合框
		var refundStatus = new Ext.form.ComboBox( {
			store : new Ext.data.SimpleStore( {
				fields : [ 'value', 'text' ],
				data : [ [ 'CLOSED', '未发货已退款' ], [ 'NO_REFUND', '没有退款' ] ]
			}),
			displayField : "text",
			valueField : 'value',
			editable : false,
			mode : 'local',
			triggerAction : 'all',
			hiddenName : 'refundStatus',
			name : 'refundStatus',
			typeAhead : true,
			width : 140,
			forceSelection : true,
			fieldLabel : '退款状态',
			hideLabel : false
		});

		var viewForm = new Ext.form.FormPanel( {
			id : 'viewForm',
			labelAlign : 'right',
			bodyStyle : 'padding:3px',
			labelWidth : 90,
			buttonAlign : 'center',
			autoHeight : true,
			border : false,
			items : [ {
				xtype : 'hidden',
				name : 'id'
			}, {
				xtype : 'numberfield',
				fieldLabel : '商品款号',
				name : 'outerIid',
				minLength : 14,
				maxLength : 14,
				anchor : '80%'
			}, refundStatus, {
				xtype : 'textarea',
				fieldLabel : '退款原因',
				name : 'refundDesc',
				anchor : '100%',
				grow : true,
				maxLength : 1000,
				growMin : 50,
				growMax : 50
			} ],
			buttons : [ {
				id : 'updataButton',
				disabled : true,
				text : '修改',
				handler : function() {
					params = {
						par : 'updateOrder'
					}
					viewForm.getForm().submit(
							{
								clientValidation : false,
								timeout : 1000 * 60 * 10,
								url : 'order.do',
								waitMsg : '修改商品属性中,请稍候...',
								params : params,
								success : function(form, action) {
									Ext.example.single(Ext.SubmitMsg.dealorder,action.result.msg);
									trackList.load();
								},
								failure : function(form, action) {
									switch (action.failureType) {
									case Ext.form.Action.CLIENT_INVALID:
										Ext.example.single(Ext.SubmitMsg.dealorder,Ext.SubmitMsg.clientInvalid);
										break;
									case Ext.form.Action.CONNECT_FAILURE:
										Ext.example.single(Ext.SubmitMsg.dealorder,Ext.SubmitMsg.connectFailure);
										break;
									case Ext.form.Action.SERVER_INVALID:
										Ext.example.single(Ext.SubmitMsg.dealorder,action.result.errorMsg);
									}
								}
							});
				}
			} ]
		});

		var receiverForm = new Ext.form.FormPanel( {
			labelAlign : 'right',
			labelWidth : 80,
			bodyStyle : 'width:100%;',
			buttonAlign : 'center',
			border : false,
			items : [ {
				layout : 'column',
				border : false,
				items : [ {
					columnWidth : .4,
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'hidden',
						value : '${trade.tid}',
						name : 'tid'
					}, {
						xtype : 'textfield',
						fieldLabel : '收货人姓名',
						allowBlank : false,
						value : '${trade.receiverName}',
						name : 'receiverName',
						anchor : '98%'
					}, {
						xtype : 'textfield',
						fieldLabel : '省份',
						allowBlank : false,
						value : '${trade.receiverState}',
						name : 'receiverState',
						anchor : '98%'
					}, {
						xtype : 'textfield',
						fieldLabel : '详细地址',
						allowBlank : false,
						value : '${trade.receiverAddress}',
						name : 'receiverAddress',
						anchor : '98%'
					} ]
				}, {
					columnWidth : .3,
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'textfield',
						fieldLabel : '手机号码',
						value : '${trade.receiverMobile}',
						name : 'receiverMobile',
						anchor : '98%'
					}, {
						xtype : 'textfield',
						fieldLabel : '城市',
						allowBlank : false,
						value : '${trade.receiverCity}',
						name : 'receiverCity',
						anchor : '98%'
					} ]
				}, {
					columnWidth : .3,
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'textfield',
						fieldLabel : '电话号码',
						value : '${trade.receiverPhone}',
						name : 'receiverPhone',
						anchor : '98%'
					}, {
						xtype : 'textfield',
						fieldLabel : '城区',
						value : '${trade.receiverDistrict}',
						name : 'receiverDistrict',
						anchor : '98%'
					}, {
						xtype : 'textfield',
						fieldLabel : '邮编',
						value : '${trade.receiverZip}',
						name : 'receiverZip',
						anchor : '98%'
					} ]
				}, {
					columnWidth : .95,
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'textarea',
						fieldLabel : '留言',
						value : '${trade.buyerMessage}',
						name : 'buyerMessage',
						anchor : '100%',
						readOnly : true,
						grow : true,
						maxLength : 1000,
						growMin : 150,
						growMax : 150
					} ]
				} ]
			} ],
			buttons : [ {
				text : '保存收货人信息',
				handler : function() {
					params = {
						par : 'receiver'
					}
					receiverForm.getForm().submit(
							{
								clientValidation : true,
								timeout : 1000 * 60 * 10,
								url : 'order.do',
								waitMsg : '保存收货人信息中,请稍候...',
								params : params,
								success : function(form, action) {
									Ext.example.single(Ext.SubmitMsg.dealorder,
											action.result.msg);
									trackList.load();
								},
								failure : function(form, action) {
									switch (action.failureType) {
									case Ext.form.Action.CLIENT_INVALID:
										Ext.example.single(
												Ext.SubmitMsg.dealorder,
												Ext.SubmitMsg.clientInvalid);
										break;
									case Ext.form.Action.CONNECT_FAILURE:
										Ext.example.single(
												Ext.SubmitMsg.dealorder,
												Ext.SubmitMsg.connectFailure);
										break;
									case Ext.form.Action.SERVER_INVALID:
										Ext.example.single(
												Ext.SubmitMsg.dealorder,
												action.result.errorMsg);
									}
								}
							});
				}
			} ]
		});
		 
		var rowLayout = {
			id : 'row-panel',
			layout : 'ux.row',
			border : false,
			items : [ {
				rowHeight : .46,
				width : '100%',
				layout : 'fit',
				items : [viewForm]
			}, {
				rowHeight : .54,
				width : '100%',
				layout : 'fit',
				defaults : {
					autoScroll : true
				},
				items : [ grid ]
			} ]
		};
		
		var tabs2 = new Ext.TabPanel({
			renderTo : document.body,
			activeTab : 0,
			height : '100%',
			plain : true,
			items : [ {
				title : '订单信息',
				frame : true,
				layout : 'fit',
				autoHeight : true,
				items : [ rowLayout ]
			}, {
				title : '收货人信息',
				bodyStyle : 'width:100%;',
				layout : 'fit',
				frame : true,
				items : [ receiverForm ]
			} ]
		});
	}
</script>
		<style type="text/css">
.x-grid-row-lock {
	filter: Alpha(opacity =         20)
}
</style>
	</head>
	<body>

	</body>
</html>