Ext.define('Appmob.view.smartdevice.searchengine.ChartListView',{
	extend : 'Ext.grid.Panel',
	xtype: 'chart-list-view',
	itemId:'chart-list-view',
	chartJson : undefined,
	hideHeaders:true,
	scrollable:true,
	loadingText:'Loading...',
	width:'100%',
	 	    	bodyStyle:{
		    		background: '#ffffff'
		    	},
		    	//cls:'report-list-view-cls',
		    	rowLines:true,
	 	    	emptyText: '<div align="center"><img src="resources/appicons/ic_no_result.png"/><div align="center" style="align:middle;font-size:14px;font-weight:bold;color:#838b8b;">No chart found!</div></div>',
	 	    	columns:[{
	 				xtype:'templatecolumn',
	 				tpl: new Ext.XTemplate(
	 			    '<tpl for=".">',
	 			        '<div style="vertical-align:middle;padding:8px;font-size:14px;font-weight:bold;">',
	 			          '{chartJson.chartTitle}',
	 			        '</div>',
	 			    '</tpl>'
	 				),
	 				flex:1
	 			}],
    emptyText: '<div align="center"><img src="resources/images/shared/icon-info.png"/><div align="center" style="align:middle;font-size:14px;font-weight:bold;color:#157fcc;">No result found!</div></div>',
	listeners :{
		  itemclick :function( dataview, record, item, index, e, eOpts ){
				var chartPanel = dataview.up('#chartPanel');
				var chartDetailView = chartPanel.down('chart-detail-view');
				var chartDetailView = chartPanel.down('chart-detail-view');
				var chartJson  = record.data.chartJson;

				chartDetailView['chartJson'] = chartJson;
				this.setHidden(true);
				chartDetailView.setTitle(chartJson.chartTitle);
				var fusionchart = new FusionCharts(chartJson);
				 fusionchart.width = '100%';
				 fusionchart.height ='100%';
				 fusionchart.render(chartDetailView.body.id);

			 }
		  }
	
});