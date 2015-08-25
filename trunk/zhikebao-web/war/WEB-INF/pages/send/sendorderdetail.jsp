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
				url : 'sendorder.do?par=order&tid=' + '${trade.tid}'
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
					return String.format('{0}', "正常");
				}
			}
		}
		var sm = new xg.CheckboxSelectionModel( {
			singleSelect : true
		});

		sm.on('rowselect', function(el, rowIndex, r) {
			viewForm.getForm().loadRecord(r);
		});

		sm.on('rowdeselect', function(el, rowIndex, r) {
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
			height : 345
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
				readOnly : true,
				minLength : 14,
				maxLength : 14,
				anchor : '80%'
			} ],
			buttons : []
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
						readOnly : true,
						allowBlank : false,
						value : '${trade.receiverName}',
						name : 'receiverName',
						anchor : '98%'
					}, {
						xtype : 'textfield',
						fieldLabel : '省份',
						readOnly : true,
						allowBlank : false,
						value : '${trade.receiverState}',
						name : 'receiverState',
						anchor : '98%'
					}, {
						xtype : 'textfield',
						fieldLabel : '详细地址',
						readOnly : true,
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
						readOnly : true,
						value : '${trade.receiverMobile}',
						name : 'receiverMobile',
						anchor : '98%'
					}, {
						xtype : 'textfield',
						fieldLabel : '城市',
						readOnly : true,
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
						readOnly : true,
						value : '${trade.receiverPhone}',
						name : 'receiverPhone',
						anchor : '98%'
					}, {
						xtype : 'textfield',
						fieldLabel : '城区',
						readOnly : true,
						value : '${trade.receiverDistrict}',
						name : 'receiverDistrict',
						anchor : '98%'
					}, {
						xtype : 'textfield',
						fieldLabel : '邮编',
						readOnly : true,
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
			buttons : [ ]
		});
		
		var shippingForm = new Ext.form.FormPanel( {
			labelAlign : 'right',
			bodyStyle : 'padding:3px',
			labelWidth : 90,
			buttonAlign : 'center',
			autoHeight : true,
			border : false,
			items : [{
				xtype : 'textfield',
				fieldLabel : '物流公司',
				name : 'companyName',
				value : '${shipping.companyCode}',
				readOnly : true,
				anchor : '50%'
			}, {
				xtype : 'textfield',
				fieldLabel : '物流单号',
				readOnly : true,
				name : 'outSid',
				value : '${shipping.outSid}',
				anchor : '50%'
			}, {
				xtype : 'textfield',
				fieldLabel : '商家发货时间',
				readOnly : true,
				name : 'deliveryTime',
				value : '${shipping.deliveryTime}',
				anchor : '50%'
			} ]
		});
		
		var rowLayout = {
			id : 'row-panel',
			layout : 'ux.row',
			border : false,
			items : [ {
				rowHeight : .10,
				width : '100%',
				layout : 'fit',
				items : [viewForm]
			}, {
				rowHeight : .90,
				width : '100%',
				layout : 'fit',
				defaults : {
					autoScroll : true
				},
				items : [ grid ]
			} ]
		};
		
		var tabs2 = new Ext.TabPanel( {
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
			}, {
				title : '物流信息',
				bodyStyle : 'width:100%;',
				layout : 'fit',
				frame : true,
				items : [ shippingForm ]
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