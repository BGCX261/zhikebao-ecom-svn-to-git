<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
  	<%@include file="/js/ext/extInclude.jsp"%>
	<%@include file="/commons/meta.jsp"%>
	<link rel="stylesheet" href="<%=ctx %>>/css/taobao.css">
	<script type="text/javascript">
		window.onload = function(){
		    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
		    var tools = [{
		        id:'refresh',
		        handler: function(e, target, panel){
		            var mgr = panel.getUpdater();
					mgr.update(panel.autoLoad);
		        }
		    }];
		
		    var viewport = new Ext.Viewport({
		        layout:'fit',
		        items:[{
		            xtype:'portal',
		            region:Ext.getBody(),
		            margins:'35 5 5 0',
		            items:[{
		                columnWidth:.50,
		                style:'padding:10px 0 10px 10px',
		                items:[{
		                	id:'authorize',
		                    title: '自动上架信息',
		                    tools: tools,
		                    autoLoad:{url:'shelplan.do?par=info',scripts:true,nocache:true}
		                }]
		            }]
		        }]
		    });
		    Ext.getCmp('authorize').getUpdater().startAutoRefresh(60, Ext.getCmp('authorize').autoLoad);
		}
	</script>
  </head>
  <body>
  </body>
</html>
