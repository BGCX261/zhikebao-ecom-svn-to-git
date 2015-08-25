<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<%@include file="/commons/meta.jsp"%>
		<%@include file="/js/ext/extInclude.jsp"%>
		<script type="text/javascript">
	var track;
	var trackList;
	var grid;
	var trackXML;
	window.onload = function() {
		var xg = Ext.grid;
		Ext.QuickTips.init();
		Ext.form.Field.prototype.msgTarget = 'side';
		//数据
		track = Ext.data.Record.create( [ {
			name : 'id'
		}, {
			name : 'buyer_nick'
		}, {
			name : 'status'
		}, {
			name : 'shipping_type'
		}, {
			name : 'payment',
			type : 'float'
		}, {
			name : 'post_fee',
			type : 'float'
		}, {
			name : 'cost',
			type : 'float'
		}, {
			name : 'pay_time'
		} ]);

		trackXML = new Ext.data.XmlReader( {
			totalRecords : "total",
			record : "record",
			id : "id"
		}, track);

		trackList = new Ext.data.GroupingStore( {
			proxy : new Ext.data.HttpProxy( {
				url : 'sendorder.do?par=data'
			}),
			sortInfo : {
				field : 'status',
				direction : "ASC"
			},
			groupField : 'buyer_nick',
			reader : trackXML
		});
		var setCount = 0
		babyStore = new Ext.data.Store( {
			reader : trackXML
		});
		trackList.on('load', function(store, r, options) {
			for ( var i = 0; i < babyStore.getCount(); i++) {
				var rr = babyStore.getAt(i);
				for ( var j = 0; j < r.length; j++) {
					if (r[j].id == rr.id) {
						sm.selectRow(store.indexOf(r[j]), true);
					}
				}
			}
		});
		trackList.on('loadexception', function() {
			Ext.Msg.alert('loadexception', '请与官方网站联系或稍后再试');
		});

		function renderTopic(value, p, record, rowIndex, colIndex) {
			var headerName = grid.getColumnModel().getDataIndex(colIndex);
			if (headerName == 'status') {
				if (value == 'WAIT_BUYER_PAY') {
					return String.format('{0}', "等待买家付款");
				}
				if (value == 'WAIT_BUYER_CONFIRM_GOODS') {
					return String.format('{0}', "卖家已发货");
				}
				if (value == 'WAIT_SELLER_SEND_GOODS') {
					return String.format('<font color=red>{0}</font>', "等待发货");
				}
				if (value == 'TRADE_FINISHED') {
					return String.format('{0}', "交易成功");
				}
				if (value == 'TRADE_CLOSED'
						|| value == 'TRADE_CLOSED_BY_TAOBAO') {
					return String.format('{0}', "交易关闭");
				}
				return String.format('{0}', "其它状态");
			}
			if (headerName == 'shipping_type') {
				if (value == 'express') {
					return String.format('{0}', "快递");
				}
				if (value == 'ems') {
					return String.format('{0}', "EMS");
				}
			}
		}

		var sm = new xg.CheckboxSelectionModel( {
			singleSelect : false
		});//设置行可选
		sm.on('rowselect', function(el, rowIndex, r) {
			fp.buttons[0].setDisabled(false);
			fp.buttons[1].setDisabled(false);
			fp.buttons[2].setDisabled(false);
			fp.buttons[3].setDisabled(false);
			babyStore.add(r);
			tbar.setText("已选订单数: " + babyStore.getCount());
		});
		sm.on('rowdeselect', function(el, rowIndex, r) {
			fp.buttons[0].setDisabled(true);
			fp.buttons[1].setDisabled(true);
			fp.buttons[2].setDisabled(true);
			fp.buttons[3].setDisabled(true);
			babyStore.remove(r);
			tbar.setText("已选订单数: " + babyStore.getCount());
		})

		Ext.ux.grid.GroupSummary.Calculations['totalCost'] = function(v,
				record, field) {
			return v + (record.data.payment - record.data.post_fee);
		};
		var cm = new xg.ColumnModel( [
				sm,
				{
					id : 'id',
					header : "订单编号",
					dataIndex : 'id',
					align : 'center',
					editor : new Ext.form.TextField( {
						allowBlank : false
					})
				},
				{
					header : "买家昵称",
					sortable : true,
					align : 'center',
					dataIndex : 'buyer_nick'
				},
				{
					header : "订单状态",
					sortable : true,
					align : 'center',
					dataIndex : 'status',
					renderer : renderTopic
				},
				{
					header : "运送方式",
					align : 'center',
					dataIndex : 'shipping_type',
					renderer : renderTopic
				},
				{
					header : "付款时间",
					sortable : true,
					align : 'center',
					dataIndex : 'pay_time'
				},
				{
					header : "实付金额(含邮费)",
					align : 'center',
					summaryType : 'sum',
					dataIndex : 'payment',
					renderer : 'usMoney'
				},
				{
					header : "邮费",
					align : 'center',
					summaryType : 'sum',
					dataIndex : 'post_fee',
					renderer : 'usMoney'
				},
				{
					header : '不含邮费',
					id : 'cost',
					dataIndex : 'cost',
					align : 'center',
					sortable : false,
					groupable : false,
					renderer : function(v, params, record) {
						return Ext.util.Format.usMoney(record.data.payment
								- record.data.post_fee);
					},
					summaryType : 'totalCost',
					summaryRenderer : Ext.util.Format.usMoney
				} ]);
		cm.defaultSortable = true;

		//页面显示数量组合框
		var gridPageSize = Ext.Common.createGridPageSize();
		var bbar = Ext.Common.createGridPagingToolbar(gridPageSize);

		var tbar = new Ext.ux.StatusBar( {
			statusAlign : 'right',
			items : [ {
				xtype : 'checkbox',
				name : 'autoRefresh',
				boxLabel : '自动刷新交易订单'
			}, {
				text : '订单统计',
				tooltip : '显示或隐藏统计行',
				handler : function() {
					summary.toggleSummaries();
				}
			} ]
		});

		var summary = new Ext.ux.grid.GroupSummary();

		grid = new xg.EditorGridPanel(
				{
					id : 'grid',
					store : trackList,
					loadMask : {
						msg : '正在加载数据...'
					},
					border : false,
					cm : cm,
					sm : sm,
					autoExpandColumn : 'id',
					tbar : tbar,
					bbar : bbar,
					viewConfig : {
						forceFit : true,
						enableRowBody : true,
						showPreview : true,
						getRowClass : function(record, rowIndex, p, store) {
						}
					},
					view : new Ext.grid.GroupingView(
							{
								emptyText : '暂时没有订单',
								deferEmptyText : true,
								enableRowBody : true,
								showGroupName : true,
								autoFill : false,
								forceFit : true,
								groupTextTpl : '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "订单" : "订单"]})'
							}),
					plugins : [ new Ext.ux.PanelResizer( {
						minHeight : 200
					}), summary ],
					height : 380
				});
		//选择是否橱窗推荐组合框
		var comboShippingType = new Ext.form.ComboBox( {
			store : new Ext.data.SimpleStore(
					{
						fields : [ 'value', 'text' ],
						data : [ [ 'all', '全部' ], [ 'express', '快递' ],
								[ 'ems', 'EMS' ] ]
					}),
			displayField : "text",
			valueField : 'value',
			editable : false,
			mode : 'local',
			triggerAction : 'all',
			hiddenName : 'shipping_type',
			value : 'all',
			name : 'shipping_type',
			typeAhead : true,
			width : 140,
			forceSelection : true,
			fieldLabel : '运送方式',
			hideLabel : false
		});
		var searchForm = {
			xtype : 'fieldset',
			title : '条件查询',
			collapsed : false,
			collapsible : true,
			autoHeight : true,
			labelAlign : 'right',
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
								fieldLabel : '订单编号',
								name : 'tid',
								anchor : '98%'
							}, comboShippingType ]
						},
						{
							columnWidth : .2,
							layout : 'form',
							border : false,
							items : [
									{
										xtype : 'textfield',
										fieldLabel : '买家昵称',
										name : 'buyer_nick',
										anchor : '98%'
									},
									{
										xtype : 'button',
										text : '查&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;询',
										handler : searchFun
									} ]
						}, {
							columnWidth : .2,
							layout : 'form',
							border : false,
							items : [ {
								xtype : 'datefield',
								fieldLabel : '起始日期',
								format : 'Y-m-d',
								id : 'startdt',
								vtype : 'daterange',
								endDateField : 'enddt',
								name : 'start_created',
								anchor : '90%'
							} ]
						}, {
							columnWidth : .2,
							layout : 'form',
							border : false,
							items : [ {
								xtype : 'datefield',
								fieldLabel : '结束日期',
								id : 'enddt',
								vtype : 'daterange',
								name : 'end_created',
								startDateField : 'startdt',
								format : 'Y-m-d',
								anchor : '90%'
							} ]
						} ]
			} ]
		};
		grid.addListener('rowdblclick', openDetail);
		var fp = new Ext.FormPanel( {
			frame : true,
			labelWidth : 60,
			buttonAlign : 'center',
			bodyStyle : 'width:100%;',
			renderTo : Ext.getBody(),
			items : [ searchForm, grid ],
			buttons : [ {
				text : '查看订单详情',
				disabled : true,
				tooltip : '双击表格行可查看订单详情',
				handler : openDetail
			}, {
				text : '批量发货',
				disabled : true,
				tooltip : '',
				handler : function() {
					orderWin = Ext.getCmp('orderWin');
					if (!orderWin) {
						var taskIframe = new Ext.ux.ManagedIFrame( {
							autoCreate : {
								id : 'taskIframe',
								scripts : true,
								loadMask : true,
								width : '100%',
								height : '100%'
							}
						});
						taskIframe.setSrc('sendorder.do?par=phipping');
						orderWin = new Ext.Window( {
							id : 'babyWin',
							title : '批量发货',
							width : 900,
							shim : false,
							height : 450,
							closable : true,
							body : taskIframe
						});
					}
					orderWin.show();
				}
			}, {
				text : '导出部份销售清单',
				disabled : true,
				tooltip : '导出对已勾选的订单',
				handler : function() {
					var ids = '';
					if (Ext.Common.validateGridRow(sm)) {
						ids = Ext.Common.getGridRowIds(sm);
					} else {
						return;
					}
					document.location.href = 'execl.do?par=shopPickingByTid&ids=' + ids;
				}
			}, {
				text : '导出部份物流清单',
				disabled : true,
				tooltip : '导出对已勾选的订单',
				handler : function() {
					var ids = '';
					if (Ext.Common.validateGridRow(sm)) {
						ids = Ext.Common.getGridRowIds(sm);
					} else {
						return;
					}
					document.location.href = 'execl.do?par=expPrinterByTid&ids=' + ids;
				}
			}, {
				text : '导出全部销售清单',
				tooltip : '导出全部查询结果的订单',
				handler : function() {
					var formValues = fp.getForm().getValues();
					var fields = [ 'tid', 'buyer_nick', 'shipping_type','start_created', 'end_created' ];
					var values = [ formValues.tid, formValues.buyer_nick,formValues.shipping_type, formValues.start_created,formValues.end_created ];
					document.location.href = 'execl.do?par=shopPickingAll&fields=' + Ext.encode(fields) + '&query=' + Ext.encode(values);
				}
			}, {
				text : '导出全部物流清单',
				tooltip : '导出全部查询结果的订单',
				handler : function() {
					var formValues = fp.getForm().getValues();
					var fields = [ 'tid', 'buyer_nick', 'shipping_type','start_created', 'end_created' ];
					var values = [ formValues.tid, formValues.buyer_nick,formValues.shipping_type, formValues.start_created,formValues.end_created ];
					document.location.href = 'execl.do?par=expPrinterByAll&fields=' + Ext.encode(fields) + '&query=' + Ext.encode(values);
				}
			} ]
		});

		searchFun();

		var task = {
			run : function() {
				var formValues = fp.getForm().getValues();
				if (formValues.autoRefresh) {
					searchFun();
				}
			},
			interval : 1000 * 60 * 4
		}
		Ext.TaskMgr.start(task);

		function searchFun() {
			var formValues = fp.getForm().getValues();
			var fields = [ 'tid', 'buyer_nick', 'shipping_type','start_created', 'end_created' ];
			var values = [ formValues.tid, formValues.buyer_nick,formValues.shipping_type, formValues.start_created,formValues.end_created ];
			trackList.baseParams['fields'] = Ext.encode(fields);
			trackList.baseParams['query'] = Ext.encode(values);
			trackList.baseParams['start'] = Ext.encode(0);
			trackList.baseParams['limit'] = Ext.encode(gridPageSize.getValue());
			trackList.reload();
			babyStore.removeAll();
			tbar.setText("已选订单数: " + babyStore.getCount());
		}

		function openDetail() {
			Ext.Common.createOrderWin(sm.getSelected().id, 'select');
		}
	}
</script>
		<style type="text/css">
.x-grid3-body .x-grid3-td-cost {
	background-color: #f1f2f4;
}

.x-grid3-summary-row .x-grid3-td-cost {
	background-color: #e1e2e4;
}
</style>
	</head>
	<body>
	</body>
</html>
