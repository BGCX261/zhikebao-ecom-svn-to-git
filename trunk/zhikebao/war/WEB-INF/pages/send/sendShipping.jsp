<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<%@include file="/commons/meta.jsp"%>
		<%@include file="/js/ext/extInclude.jsp"%>
		<script type="text/javascript">
	window.onload = function() {
		Ext.QuickTips.init();
		Ext.form.Field.prototype.msgTarget = 'side';
		var xg = Ext.grid;

		//数据
		track = Ext.data.Record.create( [ {
			name : 'id'
		}, {
			name : 'buyer_nick'
		}, {
			name : 'companyCode'
		}, {
			name : 'outSid'
		}, {
			name : 'result'
		} ]);

		trackXML = new Ext.data.XmlReader( {
			totalRecords : "total",
			record : "record",
			id : "id"
		}, track);
		trackList = new Ext.data.Store( {
			reader : trackXML
		});

		//选择是否橱窗推荐组合框
		var comboShippingCode = new Ext.form.ComboBox( {
			store : new Ext.data.SimpleStore( {
				fields : [ 'value', 'text' ],
				data : [ [ 'YTO', '圆通速递' ], [ 'EMS', 'EMS' ], [ 'ZTO', '中通速递' ], [ 'STO', '申通E物流' ]]
			}),
			displayField : "text",
			valueField : 'value',
			editable : false,
			mode : 'local',
			value: 'EMS',
			triggerAction : 'all',
			hiddenName : 'companyCode',
			name : 'companyCode',
			typeAhead : true,
			width : 140,
			forceSelection : true
		});

		var cm = new xg.ColumnModel( [ {
			id : 'id',
			header : "订单编号",
			dataIndex : 'id',
			width : 100,
			align : 'center'
		}, {
			header : "买家昵称",
			dataIndex : 'buyer_nick',
			width : 100,
			sortable : true
		}, {
			header : "物流公司",
			dataIndex : 'companyCode',
			editor : comboShippingCode,
			width : 100
		}, {
			header : "物流单号",
			dataIndex : 'outSid',
			align : 'center',
			editor : new Ext.form.TextField( {
				allowBlank : false
			}),
			width : 100
		}, {
			header : "发货结果",
			dataIndex : 'result',
			align : 'center',
			width : 100
		} ]);
		cm.defaultSortable = true;

		var grid = new xg.EditorGridPanel( {
			store : trackList,
			clicksToEdit : 1,
			frame : true,
			buttonAlign : 'center',
			id : 'grid',
			border : false,
			bodyStyle : 'width:100%;',
			cm : cm,
			autoExpandColumn : 'id',
			viewConfig : {
				forceFit : true
			},
			plugins : [ new Ext.ux.PanelResizer( {
				minHeight : 200
			}) ],
			buttons : [ {
				text : '开始批量发货',
				tooltip : '',
				handler : function() {
					var records = trackList.getModifiedRecords();
					if (records.length <= 0
							|| records.length < trackList.getCount()) {
						Ext.Msg.alert('出错啦!', '尚有交易订单未填入物流单号,请查证');
						return;
					}
					var rowsData = [];
					for ( var i = 0; i < records.length; i++) {
						//记录是否被修改
						if (record.dirty) {
							rowsData.push(records[i].data);
						}
					}
					params = {
						par : 'sendPhipping',
						json : Ext.encode(rowsData)
					}
					fp.getForm().submit({
						clientValidation : false,
						timeout : 1000 * 60 * 10,
						url : 'sendorder.do',
						waitMsg : '订单发货中,请稍候...',
						params : params,
						success : function(form, action) {
							Ext.example.msg(Ext.SubmitMsg.shipping,
									Ext.SubmitMsg.success + ':{0} '+ Ext.SubmitMsg.failure + ': {1}',
									action.result.successSize,
									action.result.failureSize);
							var resultList = action.result.resultList;
							for ( var i = 0; i < resultList.length; i++) {
								if (resultList[i].success) {
									trackList.getById(resultList[i].id).set("result", Ext.SubmitMsg.success);
								} else {
									trackList.getById(resultList[i].id).set("result", resultList[i].errorMsg);
								}
							}
							//刷新父数据
							parent.babyStore.removeAll();
							parent.trackList.reload();
						},
						failure : function(form, action) {
							switch (action.failureType) {
							case Ext.form.Action.CLIENT_INVALID:
								Ext.example.single(Ext.SubmitMsg.shipping,
										Ext.SubmitMsg.clientInvalid);
								break;
							case Ext.form.Action.CONNECT_FAILURE:
								Ext.example.single(Ext.SubmitMsg.shipping,
										Ext.SubmitMsg.connectFailure);
								break;
							case Ext.form.Action.SERVER_INVALID:
								Ext.example.single(Ext.SubmitMsg.shipping,action.result.errorMsg);
							}
						}
					});
				}
			} ],
			height : 420
		});
		var fp = new Ext.FormPanel( {
			items : [ grid ]
		})
		new Ext.Viewport( {
			layout : 'fit',
			border : false,
			items : [ fp ],
			renderTo : Ext.getBody()
		});
		var parentStore = parent.babyStore;
		for ( var i = 0; i < parentStore.getCount(); i++) {
			var r = parentStore.getAt(i);
			var companyCode = '';
			if(r.get("shipping_type") != 'ems'){
				companyCode = 'YTO';
			}else{
				companyCode = 'EMS';
			}
			var record = new track( {
				id : r.id,
				buyer_nick : r.get("buyer_nick"),
				companyCode : companyCode,
				outSid : '',
				result : ''
			}, r.id);
			trackList.add(record);
		}
	}
</script>
	</head>
	<body>
	</body>
</html>