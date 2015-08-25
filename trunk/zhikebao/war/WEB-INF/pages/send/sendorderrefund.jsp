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
			name : 'skuPropertiesName'
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
				url : 'sendorder.do?par=refundData&tid=' + '${trade.tid}'
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
				if (value == 'WAIT_SELLER_CONFIRM_GOODS5') {
					return String.format('<font color=red>{0}</font>', "买家已退货");
				}
				if (value == 'CLOSED') {
					return String.format('{0}', "买家已退款");
				}
				if (value == 'NO_REFUND') {
					return String.format('{0}', "没有退款");
				}
			}
		}
		var sm = new xg.CheckboxSelectionModel( {
			singleSelect : false
		});

		sm.on('rowselect', function(el, rowIndex, r) {
			if (r.get('refundStatus') != 'CLOSED' && r.get('refundStatus') != 'WAIT_SELLER_CONFIRM_GOODS5') {
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
			header : "商品属性",
			dataIndex : 'skuPropertiesName',
			width : 80
		}, {
			header : "退款状态",
			dataIndex : 'refundStatus',
			renderer : renderTopic,
			width : 100
		}, {
			header : "商品名称",
			dataIndex : 'title',
			width : 200
		} ]);
		cm.defaultSortable = true;
		grid = new xg.GridPanel(
				{
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
						getRowClass : function(r, rowIndex, p, store) {
							if (r.get('refundStatus') == 'CLOSED'
									|| r.get('refundStatus') == 'WAIT_SELLER_CONFIRM_GOODS5') {
								return 'x-grid-row-lock'
							}
						}
					},
					plugins : new Ext.ux.PanelResizer( {
						minHeight : 200
					}),
					height : 310
				});

		var viewForm = new Ext.form.FormPanel(
				{
					id : 'viewForm',
					labelAlign : 'right',
					bodyStyle : 'padding:3px',
					labelWidth : 60,
					buttonAlign : 'center',
					autoHeight : true,
					border : false,
					items : [ {
						xtype : 'textarea',
						fieldLabel : '退款原因',
						allowBlank : false,
						name : 'refundDesc',
						anchor : '90%',
						grow : true,
						maxLength : 1000,
						growMin : 50,
						growMax : 50
					} ],
					buttons : [ {
						id : 'updataButton',
						disabled : true,
						text : '确认退货',
						handler : function() {
						if(Ext.Common.validateGridRow(sm)){
							Ext.MessageBox.confirm('提示信息','是否确定该商品已退货?',
							function deleteRecord(btn) {
								if (btn == 'yes') {
									params = {par: 'refundSave',ids: Ext.Common.getGridRowIds(sm)}
									viewForm.getForm().submit({
										clientValidation : true,
										timeout : 1000 * 60 * 10,
										url : 'sendorder.do',
										waitMsg : '订单退货中,请稍候...',
										params : params,
										success : function(form,action) {
											Ext.example.single(Ext.SubmitMsg.dealorder,action.result.msg);
											trackList.reload();
										},
										failure : function(form,action) {
											switch (action.failureType) {
												case Ext.form.Action.CLIENT_INVALID:
														Ext.example.single(Ext.SubmitMsg.refundMessage,Ext.SubmitMsg.clientInvalid);
														break;
												case Ext.form.Action.CONNECT_FAILURE:
														Ext.example.single(Ext.SubmitMsg.refundMessage,Ext.SubmitMsg.connectFailure);
														break;
												case Ext.form.Action.SERVER_INVALID:
														Ext.example.single(Ext.SubmitMsg.refundMessage,action.result.errorMsg);
											}
										}
									});
								}
							});
						  }
						}
					} ]
				});

		var rowLayout = {
			id : 'row-panel',
			layout : 'ux.row',
			border : false,
			items : [ {
				rowHeight : .75,
				width : '100%',
				defaults : {
					autoScroll : true
				},
				items : [ grid ]
			}, {
				rowHeight : .25,
				width : '100%',
				items : [ viewForm ]
			} ]
		};

		var tabs2 = new Ext.Panel( {
			renderTo : document.body,
			activeTab : 0,
			height : '100%',
			plain : true,
			items : [ {
				frame : true,
				layout : 'fit',
				autoHeight : true,
				items : [ rowLayout ]
			} ]
		});
	}
</script>
		<style type="text/css">
.x-grid-row-lock {
	filter: Alpha(opacity =           20)
}
</style>
	</head>
	<body>

	</body>
</html>