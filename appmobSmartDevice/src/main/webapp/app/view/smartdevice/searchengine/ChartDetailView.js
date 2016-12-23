Ext.define('Appmob.view.smartdevice.searchengine.ChartDetailView',{
	extend : 'Ext.panel.Panel',
	xtype: 'chart-detail-view',
	//layout:{type:'vbox',align:'stretch',pack:'stretch'},
	layout:'fit',
	bodyPadding:10,
	itemId:'chart-detail-view',
	title:'',
	chartJson:undefined,
	selectedItemCls:'dataViewSelected',
	header : {
		titlePosition : 1,
		style:{
			background:"#ffffff"
		},
		items : [{ 
               xtype: 'button',
               icon:'resources/appicons/ic_back.png',
	               style:{
             	   borderRadius:'50%',
             	  borderWidth:'0px'
                },
	               scale:'medium',
	               margin:'0 5 0 0',
               listeners:{
                  click:function(){
                	  var chartPanel = this.up('#chartPanel');
                	  var chartListPanel = chartPanel.down('#chart-list-view');

                	  var chartDetailPanel = this.up('#chart-detail-view');
					  chartListPanel.setHidden(false);
					  chartDetailPanel.chartJson = undefined;
                  }
               }
   }]
	}
	

});