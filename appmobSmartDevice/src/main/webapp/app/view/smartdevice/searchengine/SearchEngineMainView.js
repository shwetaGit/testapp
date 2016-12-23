Ext.define('Appmob.view.smartdevice.searchengine.SearchEngineMainView', {
	extend : 'Ext.tab.Panel',
	xtype : 'search-engine-main-view',
	requires : ['Appmob.view.smartdevice.searchengine.ChartDetailView','Appmob.view.smartdevice.searchengine.ChartListView','Ext.view.View','Appmob.view.smartdevice.searchengine.SearchEngineMainViewController'],//'Appmob.view.searchengine.search.NorthPanel', 'Appmob.view.searchengine.search.SearchResult'],
	controller : 'search-engine-main-view',
	title:'',
	tabPosition :'bottom',
	tabBar: {
        layout: { type:'hbox',pack: 'center' },
        defaults:{
        	height:40,
        	flex:1
        }
    },
    dockedItems:[],
    tbar:[],
	items:[{
		xtype:'panel',
		itemId:'chartPanel',
		layout:'border',
		title:"Chart",
		iconAlign:'top',
		//icon:'images/rpticon/chart.png',
		items:[
		       {
		    	   xtype:'chart-list-view',
		    	   region:'west',
		    	   width:'100%'
		       },
		       {
		    	   xtype:'chart-detail-view',
		    	   region:'center'
		       }
		]
	},
	,{
		xtype:'panel',
		itemId:'mapHolderPanel',
		title:'Map',
		autoScroll:true,
		layout:'fit',
		iconAlign:'top'
	}         
	],
	
	listeners:{
		scope:'controller',
		afterrender:'onAfterRender'
	}
});
