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
			name : 'companyCode'
		}, {
			name : 'outSid'
		}, {
			name : 'consignTime'
		} ]);

		trackXML = new Ext.data.XmlReader( {
			totalRecords : "total",
			record : "record",
			id : "id"
		}, track);

		trackList = new Ext.data.GroupingStore( {
			proxy : new Ext.data.HttpProxy( {
				url : 'alreadyOrder.do?par=data'
			}),
			sortInfo : {
				field : 'status',
				direction : "ASC"
			},
			groupField : 'buyer_nick',
			reader : trackXML
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
		}

		var sm = new xg.CheckboxSelectionModel( {
			singleSelect : true
		});//设置行可选
		sm.on('rowselect', function(el, rowIndex, r) {
			fp.buttons[0].setDisabled(false);
			fp.buttons[1].setDisabled(false);
		});
		sm.on('rowdeselect', function(el, rowIndex, r) {
			fp.buttons[0].setDisabled(true);
			fp.buttons[1].setDisabled(true);
		})

		var cm = new xg.ColumnModel( [
				sm,
				{
					id : 'id',
					header : "订单编号",
					dataIndex : 'id',
					align : 'center'
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
				},{
					header : "发货时间",
					sortable : true,
					align : 'center',
					dataIndex : 'consignTime'
				},
				{
					header : "物流公司",
					align : 'center',
					dataIndex : 'companyCode'
				},
				{
					header : "物流单号",
					align : 'center',
					dataIndex : 'outSid'
				} ]);
		cm.defaultSortable = true;

		//页面显示数量组合框
		var gridPageSize = Ext.Common.createGridPageSize();
		var bbar = Ext.Common.createGridPagingToolbar(gridPageSize);

		var tbar = new Ext.ux.StatusBar( {
			statusAlign : 'right',
			items : []
		});
		grid = new xg.GridPanel(
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
					})],
					height : 380
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
							columnWidth : .2,
							layout : 'form',
							border : false,
							items : [ {
								xtype : 'textfield',
								fieldLabel : '订单编号',
								name : 'tid',
								anchor : '98%'
							} ]
						},
						{
							columnWidth : .2,
							layout : 'form',
							border : false,
							items : [ {
								xtype : 'textfield',
								fieldLabel : '买家昵称',
								name : 'buyer_nick',
								anchor : '98%'
							} ]
						},
						{
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
						},
						{
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
						},
						{
							columnWidth : .2,
							layout : 'form',
							border : false,
							items : [ {
								xtype : 'button',
								text : '查&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;询',
								handler : searchFun
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
			items : [ searchForm,grid ],
			buttons : [ {
				text : '查看订单详情',
				disabled : true,
				tooltip : '双击表格行可查看订单详情',
				handler : openDetail
			},{
				text : '订单退货处理',
				disabled : true,
				tooltip : '修改订单的退货情况,可针对该订单的某件商品退货',
				handler : function (){
					Ext.Common.createOrderWin(sm.getSelected().id, 'refund');
				}
			}]
		});

		searchFun();

		function searchFun() {
			var fields = [ 'tid', 'buyer_nick', 'start_created', 'end_created' ];
			var formValues = fp.getForm().getValues();
			var values = [ formValues.tid, formValues.buyer_nick,
					formValues.start_created, formValues.end_created ];
			trackList.baseParams['fields'] = Ext.encode(fields);
			trackList.baseParams['query'] = Ext.encode(values);
			trackList.baseParams['start'] = Ext.encode(0);
			trackList.baseParams['limit'] = Ext.encode(gridPageSize.getValue());
			trackList.reload();
		}

		function openDetail() {
			Ext.Common.createOrderWin(sm.getSelected().id, 'select');
		}
	}
</script>
	</head>
	<body>
	</body>
</html>
